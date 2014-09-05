package com.genfu.reform.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.genfu.reform.jpa.GenfuAuthenticationDaoImpl;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.model.UserSession;

public class GenfuAuthenticationServiceImpl implements
		GenfuAuthenticationService {

	private GenfuAuthenticationDaoImpl genfuAuthenticationDaoImpl;

	public void setGenfuAuthenticationDaoImpl(
			GenfuAuthenticationDaoImpl genfuAuthenticationDaoImpl) {
		this.genfuAuthenticationDaoImpl = genfuAuthenticationDaoImpl;
	}

	@Override
	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId) {
		return this.genfuAuthenticationDaoImpl.verify(actionName, nameSpace,
				method, operate, userId);
	}

	@Override
	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session) {

		JSONObject user = new JSONObject();

		user.put("userId", 1L);
		UserSession ui = new UserSession();
		ui.setUserId(1L);
		user.put("userInfo", ui);

		return user;
	}

}
