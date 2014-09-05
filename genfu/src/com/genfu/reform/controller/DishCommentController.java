package com.genfu.reform.controller;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

import com.genfu.reform.model.DishComment;
import com.genfu.reform.model.OrderItem;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({
		@InterceptorRef(params = { "allowedTypes",
				"image/jpeg,image/gif,image/png" }, value = "fileUpload"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "code400", type = "redirect", params = { "location",
				"/400.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "index1", type = "httpheader", params = { "headers",
				"index", "status", "303" }),
		@Result(name = "json-index", type = "json", params = {
				"excludeNullProperties", "true", "contentType",
				"application/json" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "dish-comment" }) })
public class DishCommentController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DishComment model = new DishComment();
	private Long id;
	private List<DishComment> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private File fileImage;
	private String fileImageContentType;
	private String fileImageFileName;

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
		this.request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(DishComment model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public String index() {
		// jsonObject = genfuCommonService.validateAndRecord("dish-comment",
		// "index",
		// request, DishComment.class, session);
		//
		// verifyingOperates = jsonObject.getBoolean("validResult");
		// if (verifyingOperates) {
		// dishId=${orderItem.dishId}&orderItemId=${orderItem.id}&orderId=${orderId}
		String result = "";
		if (this.parameters.containsKey("dishId")
				&& this.parameters.containsKey("orderItemId")
				&& this.parameters.containsKey("orderId")) {
			if (null != this.parameters.get("dishId")
					&& null != this.parameters.get("orderItemId")
					&& null != this.parameters.get("orderId")) {

				Map<String, Object> paraMap = new Hashtable<String, Object>();
				paraMap.put("orderItemId0",
						Long.parseLong(this.parameters.get("orderItemId")[0]));
				paraMap.put("dishId0",
						Long.parseLong(this.parameters.get("dishId")[0]));
				paraMap.put("orderId0",
						Long.parseLong(this.parameters.get("orderId")[0]));
				// 为了防止灌脏水，冗余查询条件
				List<OrderItem> oItemList = genfuCommonService
						.searchList(
								"SELECT x FROM OrderItem x WHERE x.id = :orderItemId0 AND x.dishId = :dishId0 AND x.orderId = :orderId0",
								paraMap, OrderItem.class);
				if (1 != oItemList.size()) {
					result = "code400";
				} else {
					// 社交，取消条件限制
					// list = genfuCommonService
					// .searchList(
					// "SELECT x FROM DishComment x WHERE x.orderItemId = :orderItemId0 AND x.dishId = :dishId0 AND x.orderId = :orderId0",
					// paraMap, DishComment.class);
					list = genfuCommonService
							.searchList(
									"SELECT x FROM DishComment x WHERE x.dishId = :dishId0",
									paraMap, DishComment.class);
					// model.setDishId(Long.parseLong(this.parameters
					// .get("dishId")[0]));
					// model.setOrderItemId(Long.parseLong(this.parameters
					// .get("orderItemId")[0]));
					// model.setOrderId(Long.parseLong(this.parameters
					// .get("orderId")[0]));
					OrderItem tempOi = oItemList.get(0);
					request.setAttribute("dishId", tempOi.getDishId());
					request.setAttribute("orderItemId", tempOi.getId());
					request.setAttribute("orderId", tempOi.getOrderId());
					request.setAttribute("dishName", tempOi.getOrderItemName());
				}
			}
			result = "index";
		} else {
			result = "code400";
		}

		// }
		// return new DefaultHttpHeaders("index").disableCaching();

		return result;
	}

	public String update() {
		// genfuCommonService.update(model);
		// addActionMessage("Object updated successfully");
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (DishComment) genfuCommonService
					.find(id, DishComment.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {

		return "editNew";
	}

	public String create() {

		genfuCommonService.save(model);
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// public void prepareDestroy() throws Exception {
	// }

	public String destroy() {
		jsonObject = new JSONObject();
		return "json";
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

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

	public File getFileImage() {
		return fileImage;
	}

	public void setFileImage(File fileImage) {
		this.fileImage = fileImage;
	}

	public String getFileImageContentType() {
		return fileImageContentType;
	}

	public void setFileImageContentType(String fileImageContentType) {
		this.fileImageContentType = fileImageContentType;
	}

	public String getFileImageFileName() {
		return fileImageFileName;
	}

	public void setFileImageFileName(String fileImageFileName) {
		this.fileImageFileName = fileImageFileName;
	}

}
