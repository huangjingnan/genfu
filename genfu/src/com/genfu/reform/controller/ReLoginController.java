package com.genfu.reform.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.UserInfo;
import com.genfu.reform.service.GenfuAuthenticationService;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("restDefaultStack") })
// @ResultPath("/") //加上这个，直接跳到WebContent下的resources
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "create", type = "stream", params = { "contentType",
				"text/html", "inputName", "inputStream" }),
		@Result(name = "create2json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "re-login" }),
		@Result(name = "successRedirect", type = "redirect", params = {
				"location", "/genfu/application.jsp" }),
		@Result(name = "next", type = "redirectAction", params = {
				"actionName", "${nextAction}" }),
		@Result(name = "success1", type = "redirect", params = { "location",
				"${nextAction}" }) })
public class ReLoginController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		Preparable, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GenfuAuthenticationService genfuAuthenticationService;
	private UserInfo model = new UserInfo();
	private JSONObject jsonObject;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private HttpServletRequest request;
	private String nextAction;

	public ReLoginController() {
		// this.loginService = theService;
	}

	// public String execute() {
	// String result = "login";
	// return result;
	//
	// }

	public HttpHeaders index() {
		// loginService.authentication(getUserInfo());
		// session.put(GenfuAuthenticationInterceptor.USER_SESSION_KEY,
		// userInfo);
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public void setGenfuAuthenticationService(
			GenfuAuthenticationService genfuAuthenticationService) {
		this.genfuAuthenticationService = genfuAuthenticationService;
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	// @Action(interceptorRefs = @InterceptorRef("servletConfig"))
	public String create() {
		String result = "login";
		if (null == nextAction || "".equalsIgnoreCase(nextAction)) {
			addActionMessage("操作不合法，跳转到login");
			result = "login";
		} else {

			model.setUserCode(parameters.get("userCode")[0]);
			model.setUserPassword(parameters.get("userPassword")[0]);
			jsonObject = genfuAuthenticationService.authentication(nextAction,
					request, model, session);
			if (jsonObject.getBoolean("validResult")) {
				session.put(GenfuAuthenticationInterceptor.USER_SESSION_KEY,
						jsonObject.get("userInfo"));
				if (jsonObject.getBoolean("validOperate")) {
					// jsonObject.put("validOperate", false);
					result = "next";
				} else {
					result = "successRedirect";
				}
			} else {
				addActionMessage("login failure");
				// 认证失败返回json
				result = "json";
			}

		}

		return result;
	}

	// @Override
	// public void setServletResponse(HttpServletResponse arg0) {
	//
	// }
	//
	// @Override
	// public void setServletRequest(HttpServletRequest arg0) {
	// }

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {
		// loginService.authentication(userInfo);

		addActionMessage("login failure");
	}

	@Override
	public Object getModel() {
		// if (jsonObject != null) {
		return jsonObject;
		// }
		// return (list != null ? list : model);
	}

	public void setModel(UserInfo model) {
		this.model = model;
	}

	// @Override
	// public void setParameters(Map<String, String[]> arg0) {
	// this.parameters = arg0;
	// }

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	@Override
	public void prepare() throws Exception {

	}

	public void setId(Long id) {
		if (id != null) {
			// model = (Tag) genfuCommonService.find(id, Tag.class);
		}
		// this.id = id;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}

}
