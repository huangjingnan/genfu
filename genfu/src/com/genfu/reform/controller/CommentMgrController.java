package com.genfu.reform.controller;

import java.io.File;
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
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({
		@InterceptorRef("genfuAuthentication"),
		@InterceptorRef(params = { "allowedTypes",
				"image/jpeg,image/gif,image/png" }, value = "fileUpload"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
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
public class CommentMgrController extends ValidationAwareSupport implements
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
	private boolean verifyingOperates = false;
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
		jsonObject = genfuCommonService.validateAndRecord("dish", "index",
				request, DishComment.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {

			if (this.parameters.containsKey("style")) {
				if (null != this.parameters.get("style")
						&& "jqGrid".equalsIgnoreCase(this.parameters
								.get("style")[0])) {
					jsonObject = null;
					if (null != this.parameters.get("style")
							&& "jqGrid".equalsIgnoreCase(this.parameters
									.get("style")[0])) {
						jsonObject = genfuCommonService.searchJsonJqGridFilter(
								DishComment.class, parameters);
					}
				}
			} else {
				list = genfuCommonService.searchList(DishComment.class,
						parameters);
			}

		}
		return "index";
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
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

		//genfuCommonService.save(model);
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
