package com.genfu.reform.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class GenfuAuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory
			.getLogger(GenfuAuthenticationInterceptor.class);
	public static final String USER_SESSION_KEY = "genfuUserSessionKey";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		LOG.debug("Authenticating chat user");

		SessionMap<?, ?> session = (SessionMap<?, ?>) ActionContext
				.getContext().get(ActionContext.SESSION);
		JSONObject user = (JSONObject) session.get(USER_SESSION_KEY);
		Map<String,Object> m = invocation.getInvocationContext().getContextMap();
		Object o = m.get("struts.actionMapping");
		o = m.get("parameters");
		String ret = Action.LOGIN;
		if (user == null) {
			// return Action.LOGIN;
		} else {

			// logout if
			ret = invocation.invoke();
		}
		return ret;
	}

}
