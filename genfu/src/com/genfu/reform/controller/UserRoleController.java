package com.genfu.reform.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.RoleInfo;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

//		@InterceptorRef(params = { "allowedTypes", "image/jpeg,image/gif" }, value = "fileUpload"),

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "user-role" }) })
public class UserRoleController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo model = new UserInfo();//
	private Long id;
	private Collection<UserInfo> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;

	// public UserRoleController(GenfuCommonService theService) {
	// this.genfuCommonService = theService;
	// }

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

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

	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	public void setModel(UserInfo model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public HttpHeaders index() {

		if (this.parameters.containsKey("style")) {
			if (null != this.parameters.get("style")
					&& "jqGrid"
							.equalsIgnoreCase(this.parameters.get("style")[0])) {
				jsonObject = genfuCommonService.searchJsonJqGridFilter(
						UserInfo.class, parameters);
			}
		}
		// else {
		// list = genfuCommonService
		// .searchList(UserInfo.class, parameters);
		// }
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = (UserInfo) genfuCommonService.find(id, UserInfo.class);
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

		if (parameters.containsKey("roleIds")
				&& parameters.containsKey("userIds")) {

			String roleIds = parameters.get("roleIds")[0];
			String userIds = parameters.get("userIds")[0];

			if (roleIds.length() > 0 && userIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				strBuffJPQL.append("SELECT t FROM UserInfo t WHERE t.id IN (");
				strBuffJPQL.append(userIds).append(") ORDER BY t.id ASC");
				List<UserInfo> userInfoList = genfuCommonService.searchList(
						strBuffJPQL.toString(), null, UserInfo.class);

				strBuffJPQL = new StringBuffer();
				strBuffJPQL.append("SELECT t FROM RoleInfo t WHERE t.id IN (");
				strBuffJPQL.append(roleIds).append(") ORDER BY t.id ASC");
				List<RoleInfo> roleList = genfuCommonService.searchList(
						strBuffJPQL.toString(), null, RoleInfo.class);
				List<UserInfo> addList = new ArrayList<UserInfo>();
				if (userInfoList.size() > 0 && roleList.size() > 0) {
					Set<RoleInfo> tempRI;
					// boolean somethingWrong = false;
					for (UserInfo tempUI : userInfoList) {
						tempRI = new HashSet<RoleInfo>();
						tempRI = tempUI.getRoleInfos();
						if (tempRI.addAll(roleList)) {
							tempUI.setRoleInfos(tempRI);
							addList.add(tempUI);
						}
					}
					genfuCommonService.merge(addList);
				}

			}

		}

		// genfuCommonService.save(model);
		addActionMessage("New Object created successfully");
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		if (parameters.containsKey("roleIds")
				&& parameters.containsKey("userIds")) {

			String roleIds = parameters.get("roleIds")[0];
			String userIds = parameters.get("userIds")[0];

			if (roleIds.length() > 0 && userIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();

				strBuffJPQL
						.append("DELETE FROM USER_INFO_ROLE_INFOS WHERE ROLE_ROLE_ID IN (");
				strBuffJPQL.append(roleIds).append(") AND USER_USER_ID IN (");

				strBuffJPQL.append(userIds).append(")");

				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());

			}

		} else if (parameters.containsKey("roleIds")
				&& !parameters.containsKey("userIds")) {

			String roleIds = parameters.get("roleIds")[0];

			if (roleIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				strBuffJPQL
						.append("DELETE FROM USER_INFO_ROLE_INFOS WHERE ROLE_ROLE_ID IN (");
				strBuffJPQL.append(roleIds).append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());
			}

		} else if (!parameters.containsKey("roleIds")
				&& parameters.containsKey("userIds")) {

			String userIds = parameters.get("userIds")[0];

			if (userIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				strBuffJPQL
						.append("DELETE FROM USER_INFO_ROLE_INFOS WHERE USER_USER_ID IN (");
				strBuffJPQL.append(userIds).append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());
			}

		}
		jsonObject = new JSONObject();
		// genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "json";
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}

}
