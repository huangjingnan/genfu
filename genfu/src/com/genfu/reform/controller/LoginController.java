package com.genfu.reform.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.genfu.reform.model.UserInfo;
import com.genfu.reform.service.GenfuAuthenticationService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("defaultStack") })
@ResultPath("/")
@Results({
		@Result(name = "login", location = "login.jsp"),
		@Result(name = "success", location = "index.jsp"),
		@Result(name = "application", type = "redirect", params = { "location",
				"/genfu/application.jsp" }) })
public class LoginController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ParameterAware,
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GenfuAuthenticationService genfuAuthenticationService;
	private UserInfo userInfo = new UserInfo();
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private HttpServletRequest request;
	private JSONObject jsonObject;

	public GenfuAuthenticationService getGenfuAuthenticationService() {
		return genfuAuthenticationService;
	}

	public void setGenfuAuthenticationService(
			GenfuAuthenticationService genfuAuthenticationService) {
		this.genfuAuthenticationService = genfuAuthenticationService;
	}

	public String execute() {
		String result = "login";
		return result;

	}

	public String index() {
		// loginService.authentication(getUserInfo());
		// session.put(GenfuAuthenticationInterceptor.USER_SESSION_KEY,
		// userInfo);
		if (session
				.containsKey(GenfuAuthenticationInterceptor.USER_SESSION_KEY)
				&& null != session
						.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY)) {
			jsonObject = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			// 没有注销
			if (jsonObject.getLong("userId") > -1) {
				return "application";
			}
		}

		return "login";

	}

	// @Action(interceptorRefs = @InterceptorRef("servletConfig"))
	public String create() {
		jsonObject = genfuAuthenticationService.authentication("login",
				request, userInfo, session);

		String result = "login";
		if (jsonObject.containsKey("userId")) {
			session.put(GenfuAuthenticationInterceptor.USER_SESSION_KEY,
					jsonObject.get("userInfo"));
			// session.put("userNavis", jsonObject.get("userNavis"));
			// session.put("sessionId", request.getRequestedSessionId());
			session.put("userCode", userInfo.getUserCode());
			result = "application";
		} else {
			addActionMessage("login failure");
		}

		return result;
	}

	public String destroy() {
		session.clear();
		return "login";
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {

	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}
}
