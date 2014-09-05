package com.genfu.reform.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.genfu.reform.model.UserInfo;

public interface GenfuAuthenticationService {

	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId);
	
	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session);
}
