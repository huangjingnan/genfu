package com.genfu.reform.controller;

import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.genfu.reform.service.GenfuAuthenticationServiceImpl;
import com.genfu.reform.service.GenfuAuthenticationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class GenfuAuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;
	private GenfuAuthenticationService auth;
	private static final Logger LOG = LoggerFactory
			.getLogger(GenfuAuthenticationInterceptor.class);
	public static final String USER_SESSION_KEY = "genfuUserSessionKey";

	@Override
	public void destroy() {
		LOG.debug("Authenticating destroy");
	}

	@Override
	public void init() {
		LOG.debug("Authenticating init");

		// auth = new GenfuAuthenTicationServiceImpl();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		LOG.debug("Authenticating user");
		String className = invocation.getAction().getClass().getName();
		long startTime = System.currentTimeMillis();
		LOG.debug("Before calling action: " + className);
		SessionMap<?, ?> session = (SessionMap<?, ?>) ActionContext
				.getContext().get(ActionContext.SESSION);
		JSONObject user = (JSONObject) session.get(USER_SESSION_KEY);
		// invocation.getInvocationContext().getSession();

		ActionContext ac = invocation.getInvocationContext();

		Map<?, ?> context = ac.getContextMap();
		ActionMapping am = (ActionMapping) context.get("struts.actionMapping");

		ServletContext servletContext = ServletActionContext
				.getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		auth = (GenfuAuthenticationServiceImpl) appContext
				.getBean("genfuAuthenticationServiceImpl");

		LOG.debug(am.getName());
		LOG.debug(am.getNamespace());
		LOG.debug(am.getMethod());
		// o = (Hashtable<String, String>) m.get("parameters");
		// 判断action是否被授权 actionName,nameSpace,method,operate,userId
		String ret = Action.LOGIN;
		if (user == null) {
			// return Action.LOGIN;
		} else {

			if (auth.verify(am.getName(), am.getNamespace(), am.getMethod(),
					"", user.getLong("userId")))
				ret = invocation.invoke();
		}

		long endTime = System.currentTimeMillis();
		LOG.debug("After calling action: " + className
				+ " Time taken: " + (endTime - startTime) + " ms");
		return ret;
	}

}
