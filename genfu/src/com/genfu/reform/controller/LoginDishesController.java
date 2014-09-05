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
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("defaultStack") })
@ResultPath("/")
@Results({
		@Result(name = "login", location = "loginDishes.jsp"),
		@Result(name = "success", location = "index.jsp"),
		@Result(name = "dishes", type = "redirect", params = { "location",
				"/genfu/dishes.jsp" }) })
public class LoginDishesController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ParameterAware,
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GenfuCommonService genfuCommonService;
	private UserInfo userInfo = new UserInfo();
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private HttpServletRequest request;
	private JSONObject jsonObject;

	public LoginDishesController() {
	}

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
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
				return "dishes";
			}
		}

		return "login";

	}

	// @Action(interceptorRefs = @InterceptorRef("servletConfig"))
	public String create() {
		// jsonObject = genfuCommonService.authentication(null, request,
		// userInfo,
		// session);
		//
		// String result = "login";
		// if (jsonObject.getBoolean("validResult")) {
		session.put(GenfuAuthenticationInterceptor.USER_SESSION_KEY,
				jsonObject.get("userInfo"));
		session.put("userNavis", jsonObject.get("userNavis"));
		session.put("sessionId", request.getRequestedSessionId());
		String result = "dishes";
		// } else {
		// addActionMessage("login failure");
		// }

		return result;
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
