package com.genfu.reform.controller;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
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

import com.genfu.reform.model.Order;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.service.GenfuCommonService;
import com.genfu.reform.util.DES;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({
		@InterceptorRef("genfuAuthentication"),
		@InterceptorRef(params = { "allowedTypes", "image/jpeg,image/gif" }, value = "fileUpload"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "createOver", location = "createOver.jsp"),
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "user-info" }) })
public class UserInfoController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo model = new UserInfo();//
	private Long id;
	private Collection<UserInfo> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private File fileImage;
	private String fileImageContentType;
	private String fileImageFileName;
	private boolean verifyingOperates;

	// public UserInfoController(GenfuCommonService theService) {
	// this.genfuCommonService = theService;
	// }

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void validate() {

	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	public void setModel(UserInfo model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public void prepareIndex() throws Exception {
		verifyingOperates = genfuCommonService.verifyingOperates(parameters,
				session);
	}

	public HttpHeaders index() {
		if (verifyingOperates) {

			if (this.parameters.containsKey("style")) {
				if (null != this.parameters.get("style")
						&& "jqGrid".equalsIgnoreCase(this.parameters
								.get("style")[0])) {
					// jsonObject =
					// genfuCommonService.searchJsonNativeQuery("SELECT * FROM USER_INFO WHERE 1=1",
					// null, UserInfo.class, parameters);
					jsonObject = genfuCommonService.searchJsonJqGridFilter(
							UserInfo.class, parameters);
				}
			} else {
				list = genfuCommonService
						.searchList(UserInfo.class, parameters);
			}
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {

		Map<String, Object> par = new HashMap<String, Object>();
		par.put("userId0", model.getId());
		List<UserInfo> theUI = genfuCommonService.searchNativeQuery(
				"SELECT * FROM USER_INFO WHERE USER_ID=:userId0", par,
				UserInfo.class);
		if (!theUI.get(0).getUserPassword()
				.equalsIgnoreCase(model.getUserPassword())) {
			DES des = new DES(model.getUserCode());
			model.setUserPassword(des.getEncString(model.getUserPassword()));
		}
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = (UserInfo) genfuCommonService.find(id, UserInfo.class);
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
		DES des = new DES(model.getUserCode());
		model.setUserPassword(des.getEncString(model.getUserPassword()));
		genfuCommonService.save(model);
		addActionMessage("New Object created successfully");
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "json";
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
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
