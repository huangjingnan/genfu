package com.genfu.reform.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.Cart;
import com.genfu.reform.model.Order;
import com.genfu.reform.service.GenfuCommonService;
import com.genfu.reform.util.DES;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/loginDishes.jsp" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "checkout" }),
		@Result(name = "show", type = "httpheader", params = { "actionName",
				"checkout" }),
		@Result(name = "qr_code", type = "stream", params = { "contentType",
				"image/png", "inputName", "imageStream" }) })
public class CheckoutController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order model = new Order();
	private Long id;
	private List<Order> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private Cart myCart = null;
	private InputStream imageStream;
	private MultiFormatWriter barcodeWriter = new MultiFormatWriter();

	// public CheckoutController(GenfuCommonService theService) {
	// genfuCommonService = theService;
	// }

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Order model) {
		this.model = model;
	}

	public String show() {
		// QR_CODE
		if (parameters.containsKey("QR_CODE")) {
			try {

				String checkoutURL = genfuCommonService
						.getGenfuConfig("checkoutURL");
				checkoutURL = checkoutURL + id + "?orderId=" + id + "&encode=";
				DES des = new DES(id.toString());

				checkoutURL = checkoutURL
						+ des.getEncString(model.getCreatedAt().toString());
				BitMatrix matrix = barcodeWriter.encode(checkoutURL,
						BarcodeFormat.QR_CODE, 300, 300);
				BufferedImage image = MatrixToImageWriter
						.toBufferedImage(matrix);

				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(image, "png", os);
				imageStream = new ByteArrayInputStream(os.toByteArray());
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "qr_code";
		}
		return "show";
	}

	// public void prepareIndex() throws Exception {
	// }

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		list = genfuCommonService.searchList(
				"from Order WHERE status IN ('OPEN','PROCESS')", null,
				Order.class);
		jsonObject = null;
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// public void prepareUpdate() throws Exception {
	// }

	public HttpHeaders update() {

		jsonObject = (JSONObject) session
				.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		if (parameters.containsKey("multiplied")) {

			model.PlaceOrder(myCart,
					Long.parseLong(parameters.get("multiplied")[0]),
					jsonObject.getString("userId"));
		} else {

			model.PlaceOrder(myCart, 1, jsonObject.getString("userId"));
		}
		model.setUpdatedAt(new Date());
		genfuCommonService.update(model);
		addActionMessage("Thanks you! Revised your order information");

		jsonObject = null;
		return new DefaultHttpHeaders("thanks").setLocationId(model.getId());
	}

	public void setId(Long id) {
		if (id != null && id > 0) {
			model = (Order) genfuCommonService.find(id, Order.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		return "editNew";
	}

	// public void prepareCreate() throws Exception {
	// }

	public HttpHeaders create() {
		jsonObject = (JSONObject) session
				.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		model.setStaffNumber(jsonObject.getString("userId"));
		// model.setOrderName(parameters.get("orderName")[0]);
		// model.setNumberPeople(parameters.get("numberPeople")[0]);
		// model.setAmount(Long.parseLong(parameters.get("amount")[0]));
		if (parameters.containsKey("multiplied")) {

			model.PlaceOrder(myCart,
					Long.parseLong(parameters.get("multiplied")[0]),
					model.getStaffNumber());
		} else {

			model.PlaceOrder(myCart, 1, model.getStaffNumber());
		}
		// model.setId(myCart.getId());
		genfuCommonService.save(model);
		addActionMessage("Thanks you! This is your order information");
		jsonObject = null;
		return new DefaultHttpHeaders("thanks").setLocationId(model.getId());
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		// genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	public void prepareValidate() throws Exception {
	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	@Override
	public void prepare() throws Exception {
		if (session.containsKey("cart_id")) {
			// this.id = (Long) session.get("cart_id");
			this.myCart = (Cart) genfuCommonService.find(
					(Long) session.get("cart_id"), Cart.class);
		}
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

}
