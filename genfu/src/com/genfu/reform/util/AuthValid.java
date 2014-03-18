package com.genfu.reform.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.genfu.reform.model.UserInfo;

import net.sf.json.JSONObject;

public interface AuthValid {
	public boolean verifyingOperates(Map<String, String[]> arg0,
			Map<String, Object> session);

	public <T> JSONObject validateAndRecord(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session);

	public <T> JSONObject validateOperates(String userCode, String Passwd,
			String actionName, String operate, Map<String, Object> agr0,
			Class<T> entity, Map<String, String[]> parameters,
			Map<String, Object> session);

	public <T> void recordOperates(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session);

	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session);
}
