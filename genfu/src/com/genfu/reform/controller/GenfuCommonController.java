package com.genfu.reform.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({
		@Result(name = "deleteOver", location = "deleteOver.jsp"),
		@Result(name = "createOver", location = "createOver.jsp"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "genfu-common" }) })
public class GenfuCommonController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object model = new Object();//
	private String ids;
	private List<Object> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates;

	// public GenfuCommonController(GenfuCommonService theService) {
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
		return (list != null ? list : model);
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// 首先验证权限
	public void prepareIndex() throws Exception {
		verifyingOperates = genfuCommonService.verifyingOperates(parameters,
				session);
	}

	public HttpHeaders index() {
		if (verifyingOperates && this.parameters.containsKey("statusCode")) {
			list = genfuCommonService.searchList(Object.class, parameters);
			return new DefaultHttpHeaders("result").disableCaching();
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		// genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(String id) {
		if (id != null) {
			// result.model = genfuCommonService.find(id);
		}
		this.ids = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		return "editNew";
	}

	public String create() {
		// genfuCommonService.save(model);
		addActionMessage("New Object created successfully");
		return "createOver";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public void prepareDeleteConfirm() throws Exception {
		// confirmed

	}

	public String destroy() {
		// genfuCommonService.remove(model);

		if (null != parameters.get("id")) {
			this.ids = parameters.get("id")[0];
		}

		genfuCommonService.batchDeleteJPQL(parameters.get("className")[0],
				this.ids);
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}
}
