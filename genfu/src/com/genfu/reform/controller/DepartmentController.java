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

import com.genfu.reform.model.Department;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;
import com.opensymphony.xwork2.util.logging.Logger;

//@Interceptors({ com.genfu.reform.ejb.AuditInterceptor.class })
// @Action(interceptorRefs=[com.genfu.reform.util.AuditInterceptor])
@Results({
		@Result(name = "deleteOver", location = "deleteOver.jsp"),
		@Result(name = "createOver", location = "createOver.jsp"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "department" }) })
public class DepartmentController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable, Logger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Department model = new Department();//
	private Long id;
	private List<Department> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;

	// public DepartmentController(GenfuCommonService theService) {
	// this.departmentService = theService;
	// }

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	public GenfuCommonService getDepartmentService() {
		return genfuCommonService;
	}

	public void setDepartmentService(GenfuCommonService departmentService) {
		this.genfuCommonService = departmentService;
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
		if (model.getDepartmentName().length() < 1) {
			addFieldError("model.title", "Title is required.");
		}

	}

	@Override
	public Object getModel() {
		return (list != null ? list : model);
	}

	public void setModel(Department model) {
		this.model = model;
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	public HttpHeaders index() {

		if (this.parameters.containsKey("statusCode")) {
			list = genfuCommonService.searchList(Department.class, parameters);
			// return new DefaultHttpHeaders("result").disableCaching();
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = (Department) genfuCommonService.find(id,
					Department.class);
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
		if ("1".equals(model.getDepartmentName())) {
			for (int i = 2; i < 101; i++) {
				model = new Department();
				model.setId(i);
				model.setDepartmentName("department name");
				genfuCommonService.save(model);
			}
		}
		addActionMessage("New Object created successfully");
		// return new DefaultHttpHeaders("close");
		// return new
		// DefaultHttpHeaders("success").setLocationId(model.getId());
		return "createOver";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void debug(String arg0, String... arg1) {

	}

	@Override
	public void debug(String arg0, Throwable arg1, String... arg2) {

	}

	@Override
	public void error(String arg0, String... arg1) {

	}

	@Override
	public void error(String arg0, Throwable arg1, String... arg2) {

	}

	@Override
	public void fatal(String arg0, String... arg1) {

	}

	@Override
	public void fatal(String arg0, Throwable arg1, String... arg2) {
	}

	@Override
	public void info(String arg0, String... arg1) {

	}

	@Override
	public void info(String arg0, Throwable arg1, String... arg2) {

	}

	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		return false;
	}

	@Override
	public void trace(String arg0, String... arg1) {

	}

	@Override
	public void trace(String arg0, Throwable arg1, String... arg2) {
	}

	@Override
	public void warn(String arg0, String... arg1) {

	}

	@Override
	public void warn(String arg0, Throwable arg1, String... arg2) {

	}

	@Override
	public void debug(String arg0, Object... arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0, Object... arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}
}
