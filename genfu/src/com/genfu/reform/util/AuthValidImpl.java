package com.genfu.reform.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.genfu.reform.controller.GenfuAuthenticationInterceptor;
import com.genfu.reform.jpa.GenfuCommonDAO;
import com.genfu.reform.model.GenfuLog;
import com.genfu.reform.model.NavigationNode;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.model.UserSession;

public class AuthValidImpl implements AuthValid {
	private GenfuCommonDAO genfuCommonDao;

	public void setGenfuCommonDao(GenfuCommonDAO genfuCommonDao) {
		this.genfuCommonDao = genfuCommonDao;
	}

	@Override
	public <T> JSONObject validateAndRecord(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("validResult", false);

		GenfuLog genfuLog = new GenfuLog();

		genfuLog.setLogAction(actionName);
		genfuLog.setLogAt(new Date());
		StringBuffer strBuffPara = new StringBuffer();
		strBuffPara.append("operate=").append(operate).append("|");
		if (null != request) {
			genfuLog.setLogRequestIp(request.getRemoteAddr());
			genfuLog.setLogOthers(request.getRequestedSessionId());
			Map<String, String[]> parameters = request.getParameterMap();
			Iterator<String> it = parameters.keySet().iterator();
			String tempKey = "statusCode";
			String tempValue;
			while (it.hasNext()) {
				tempKey = it.next();
				tempValue = parameters.get(tempKey)[0].toString();
				strBuffPara.append(tempKey).append("=");
				if (null != tempValue && !"".equalsIgnoreCase(tempValue)) {
					strBuffPara.append(tempValue);
				}
				strBuffPara.append("|");
				tempValue = null;
			}
		}
		genfuLog.setLogRequestParameter(strBuffPara.toString());
		if (null != session) {
			JSONObject user = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			if (null != user) {
				genfuLog.setLogUserId(user.getLong("userId"));
			}

			// genfuCommonDao.getTotalRecords(
			// "", null, null);
			// select * from navigation_nodes where navi_action='' and
			// navi_operate='' and navi_id in(
			// SELECT node_navi_id
			// FROM role_info_navigation_nodes where role_role_id in(SELECT
			// role_role_id
			// FROM user_info_role_infos where user_user_id = ?)

			// 为减少与数据库交互从session存取
			if (session.containsKey("userNavis")) {
				JSONArray navis = (JSONArray) session.get("userNavis");
				for (int i = 0; i < navis.size(); i++) {
					JSONObject navi = null;
					navi = navis.getJSONObject(i);
					if (actionName.equalsIgnoreCase(navi
							.getString("naviAction"))
							&& operate.equalsIgnoreCase(navi
									.getString("naviOperate"))) {
						jsonObject.remove("validResult");
						jsonObject.put("validResult", true);
						break;
					}
				}
			}
			// if (!jsonObject.containsKey("validOperate")) {
			// jsonObject.put("validResult", false);
			// }

		}

		genfuCommonDao.save(genfuLog);

		return jsonObject;
	}

	@Override
	public <T> JSONObject validateOperates(String userCode, String passwd,
			String actionName, String operate, Map<String, Object> agr0,
			Class<T> entity, Map<String, String[]> parameters,
			Map<String, Object> session) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("validResult", false);
		jsonObject.put("validOperate", false);

		GenfuLog genfuLog = new GenfuLog();

		if (null != passwd && !"".equalsIgnoreCase(passwd)) {

			JSONObject user = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			if (null != user) {
				UserInfo tempUser = (UserInfo) genfuCommonDao.find(
						user.getLong("userId"), UserInfo.class);
				DES des = new DES(tempUser.getUserCode());
				if (des.getEncString(passwd).equals(tempUser.getUserPassword())) {
					jsonObject.put("validOperate", true);
				}
			}

		}

		genfuLog.setLogAction(actionName);
		genfuLog.setLogAt(new Date());
		StringBuffer strBuffPara = new StringBuffer();
		strBuffPara.append("operate=").append(operate).append("|");
		genfuLog.setLogOthers(session.get("sessionId").toString());
		if (null != parameters) {
			Iterator<String> it = parameters.keySet().iterator();
			String tempKey = "statusCode";
			String tempValue;
			while (it.hasNext()) {
				tempKey = it.next();
				tempValue = parameters.get(tempKey)[0].toString();
				strBuffPara.append(tempKey).append("=");
				if (null != tempValue && !"".equalsIgnoreCase(tempValue)) {
					strBuffPara.append(tempValue);
				}
				strBuffPara.append("|");
				tempValue = null;
			}
		}
		genfuLog.setLogRequestParameter(strBuffPara.toString());
		if (null != session) {
			JSONObject user = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			if (null != user) {
				genfuLog.setLogUserId(user.getLong("userId"));
			}

			// genfuCommonDao.getTotalRecords(
			// "", null, null);
			// select * from navigation_nodes where navi_action='' and
			// navi_operate='' and navi_id in(
			// SELECT node_navi_id
			// FROM role_info_navigation_nodes where role_role_id in(SELECT
			// role_role_id
			// FROM user_info_role_infos where user_user_id = ?)

			// 为减少与数据库交互从session存取
			if (session.containsKey("userNavis")) {
				JSONArray navis = (JSONArray) session.get("userNavis");
				for (int i = 0; i < navis.size(); i++) {
					JSONObject navi = null;
					navi = navis.getJSONObject(i);
					if (actionName.equalsIgnoreCase(navi
							.getString("naviAction"))
							&& operate.equalsIgnoreCase(navi
									.getString("naviOperate"))) {
						jsonObject.remove("validResult");
						jsonObject.put("validResult", true);
						break;
					}
				}
			}
			// if (!jsonObject.containsKey("validOperate")) {
			// jsonObject.put("validResult", false);
			// }

		}

		genfuCommonDao.save(genfuLog);

		return jsonObject;
	}

	@Override
	public <T> void recordOperates(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session) {
		GenfuLog genfuLog = new GenfuLog();

		genfuLog.setLogAction(actionName);
		genfuLog.setLogAt(new Date());
		genfuLog.setLogRequestIp(request.getRemoteAddr());
		genfuLog.setLogOthers(request.getRequestedSessionId());
		Map<String, String[]> parameters = request.getParameterMap();
		Iterator<String> it = parameters.keySet().iterator();
		String tempKey = "statusCode";
		String tempValue;
		StringBuffer strBuffPara = new StringBuffer();
		while (it.hasNext()) {
			tempKey = it.next();
			tempValue = parameters.get(tempKey)[0].toString();
			strBuffPara.append(tempKey).append("=");
			if (null != tempValue && !"".equalsIgnoreCase(tempValue)) {
				strBuffPara.append(tempValue);
			}
			strBuffPara.append("|");
			tempValue = null;
		}
		genfuLog.setLogRequestParameter(strBuffPara.toString());
		if (null != session) {
			JSONObject user = (JSONObject) session
					.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
			if (null != user) {
				genfuLog.setLogUserId(user.getLong("userId"));
			}
		}

		genfuCommonDao.save(genfuLog);
	}

	@Override
	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session) {

		JSONObject jsonObject = new JSONObject();

		GenfuLog genfuLog = new GenfuLog();

		genfuLog.setLogAction(actionName);
		genfuLog.setLogAt(new Date());
		genfuLog.setLogRequestIp(request.getRemoteAddr());
		genfuLog.setLogOthers(request.getRequestedSessionId());

		//
		Map<String, String[]> parameters = request.getParameterMap();
		Iterator<String> it = parameters.keySet().iterator();
		String tempKey = "statusCode";
		String tempValue;
		StringBuffer strBuffPara = new StringBuffer();
		while (it.hasNext()) {
			tempKey = it.next();
			if ("Password".endsWith(tempKey)) {
				continue;
			}
			tempValue = parameters.get(tempKey)[0].toString();
			strBuffPara.append(tempKey).append("=");
			if (null != tempValue && !"".equalsIgnoreCase(tempValue)) {
				strBuffPara.append(tempValue);
			}
			strBuffPara.append("|");
			tempValue = null;
		}
		//
		genfuLog.setLogRequestParameter(strBuffPara.toString());

		DES des = new DES(userInfo.getUserCode());
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userCode_EQ", userInfo.getUserCode());

		// String psss = des.getEncString(userInfo.getUserPassword());
		para.put("userPassword_EQ",
				des.getEncString(userInfo.getUserPassword()));
		// DES des2 = new DES(userInfo.getUserCode());

		List<UserInfo> listUI = genfuCommonDao
				.searchList(
						"SELECT x FROM UserInfo x WHERE x.userCode=:userCode_EQ AND x.userPassword=:userPassword_EQ",
						para, UserInfo.class);

		if (null != listUI && listUI.size() == 1) {
			// jsonObject.put("validResult", true);
			UserInfo _userInfo = listUI.get(0);

			UserSession uS = new UserSession();
			uS.setUserId(_userInfo.getId());
			uS.setUserSessionOthers(_userInfo.getUserName());
			uS.setSessionId(request.getRequestedSessionId());
			// _userInfo.setUserPassword("");
			genfuLog.setLogUserId(_userInfo.getId());
			jsonObject.put("userInfo", uS);
			jsonObject.put("validResult", true);
			//Set<NavigationNode> navis = _userInfo.getNavis();
			//jsonObject.put("userNavis", navis);
			// if (null != actionName && !"".equalsIgnoreCase(actionName)) {
			// 验证操作是否合法

			// for (NavigationNode ni : navis) {
			// if (actionName.equalsIgnoreCase(ni.getNaviAction())) {
			// jsonObject.put("validOperate", true);
			// break;
			// }
			// }
			// if (!jsonObject.containsKey("validOperate")) {
			// jsonObject.put("validOperate", false);
			// }
			// }
			_userInfo = null;
		} else {
			jsonObject.put("validResult", false);
			jsonObject.put("validMsg", "验证失败");
		}

		genfuCommonDao.save(genfuLog);
		return jsonObject;
	}

	@Override
	public boolean verifyingOperates(Map<String, String[]> arg0,
			Map<String, Object> session) {

		return true;
	}
}
