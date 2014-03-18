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
import com.genfu.reform.model.Dish;
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
@InterceptorRefs({ @InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/loginDishes.jsp" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "my-order" }),
		@Result(name = "show", type = "httpheader", params = { "actionName",
				"my-order" }),
		@Result(name = "qr_code", type = "stream", params = { "contentType",
				"image/png", "inputName", "imageStream" }) })
public class MyOrderController extends ValidationAwareSupport implements
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
	private boolean verifyingOperates;
	private Cart myCart = null;
	private InputStream imageStream;
	private MultiFormatWriter barcodeWriter = new MultiFormatWriter();
	private String encode = "";

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
		if (parameters.containsKey("encode")) {
			this.id = Long.parseLong(parameters.get("orderId")[0]);
			DES des = new DES(id.toString());

			this.model = (Order) genfuCommonService.find(
					Long.parseLong(parameters.get("orderId")[0]), Order.class);
			encode = des.getEncString(model.getCreatedAt().toString());
			if (parameters.get("encode")[0].equalsIgnoreCase(encode.replace("+", " "))) {
				verifyingOperates = true;
			}

		} else {
			verifyingOperates = false;
		}
		if (verifyingOperates) {

			// QR_CODE
			if (parameters.containsKey("QR_CODE")) {
				try {

					String checkoutURL = genfuCommonService
							.getGenfuConfig("checkoutURL");
					checkoutURL = checkoutURL + id + "?orderId=" + id
							+ "&encode=";
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
		} else {

			return "fail";
		}
		// return "show";
	}

	public void prepareIndex() throws Exception {
		jsonObject = genfuCommonService.validateOperates("", "", "checkout",
				"index", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (verifyingOperates) {

			list = genfuCommonService.searchList(Order.class, parameters);
		}
		jsonObject = null;
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public HttpHeaders update() {
		jsonObject = genfuCommonService.validateOperates("",
				parameters.get("authCode")[0], "checkout", "update", null,
				Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validOperate")
				&& jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (parameters.containsKey("multiplied")) {

				model.PlaceOrder(myCart,
						Long.parseLong(parameters.get("multiplied")[0]));
			} else {

				model.PlaceOrder(myCart, 1);
			}
			model.setUpdatedAt(new Date());
			genfuCommonService.update(model);
			addActionMessage("Thanks you! Revised your order information");
		} else {
			addActionMessage("Sorry！Auth faild");
			return new DefaultHttpHeaders("thanks");
		}
		jsonObject = null;
		return new DefaultHttpHeaders("thanks").setLocationId(model.getId());
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Order) genfuCommonService.find(id, Order.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		jsonObject = (JSONObject) session
				.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		// model.setStaffNumber(jsonObject.getString("userId"));
		return "editNew";
	}

	public HttpHeaders create() {
		jsonObject = genfuCommonService.validateOperates("",
				parameters.get("authCode")[0], "checkout", "create", null,
				Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validOperate")
				&& jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			jsonObject = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			model.setStaffNumber(jsonObject.getString("userId"));
			if (parameters.containsKey("multiplied")) {

				model.PlaceOrder(myCart,
						Long.parseLong(parameters.get("multiplied")[0]));
			} else {

				model.PlaceOrder(myCart, 1);
			}
			// model.setId(myCart.getId());
			genfuCommonService.save(model);
			addActionMessage("Thanks you! This is your order information");
		} else {
			addActionMessage("Sorry！Auth faild");
			return new DefaultHttpHeaders("thanks");
		}
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

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

}
