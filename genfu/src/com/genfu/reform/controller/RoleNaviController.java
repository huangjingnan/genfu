package com.genfu.reform.controller;

import java.util.ArrayList;
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

import com.genfu.reform.model.NavigationNode;
import com.genfu.reform.model.RoleInfo;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "role-navi" }) })
public class RoleNaviController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleInfo model = new RoleInfo();
	private Long id;
	private List<RoleInfo> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private JSONObject jsonObject;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(RoleInfo model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {
		// if (verifyingOperates) {
		if (null != this.parameters.get("style")
				&& null != this.parameters.get("roleId")) {
			jsonObject = genfuCommonService
					.searchJsonJqGridFilter(
							"SELECT x FROM NavigationNode x WHERE x.id IN (SELECT NODE_NAVI_ID FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID="
									+ parameters.get("roleId")[0] + ")",
							NavigationNode.class, parameters);
		}
		// else {
		// list = genfuCommonService
		// .searchList(RoleInfo.class, parameters);
		// }
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
			model = (RoleInfo) genfuCommonService.find(id, RoleInfo.class);
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

		// genfuCommonService.save(model);
		if (parameters.containsKey("roleIds")
				&& parameters.containsKey("naviIds")) {

			String roleIds = parameters.get("roleIds")[0];
			String naviIds = parameters.get("naviIds")[0];

			if (roleIds.length() > 0 && naviIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();

				strBuffJPQL.append("SELECT t FROM RoleInfo t WHERE t.id IN (");
				strBuffJPQL.append(roleIds).append(") ORDER BY t.id ASC");
				List<RoleInfo> roleInfosglist = genfuCommonService.searchList(
						strBuffJPQL.toString(), null, RoleInfo.class);

				// navi
				strBuffJPQL = new StringBuffer();
				strBuffJPQL
						.append("SELECT t FROM NavigationNode t WHERE t.id IN (");
				strBuffJPQL.append(naviIds).append(") ORDER BY t.id ASC");
				List<NavigationNode> naviList = genfuCommonService.searchList(
						strBuffJPQL.toString(), null, NavigationNode.class);
				List<RoleInfo> addList = new ArrayList<RoleInfo>();
				if (roleInfosglist.size() > 0 && naviList.size() > 0) {
					Set<NavigationNode> tempNg;
					// boolean somethingWrong = false;
					for (RoleInfo tempRI : roleInfosglist) {
						tempNg = new HashSet<NavigationNode>();
						tempNg = tempRI.getNavigationNodes();
						if (tempNg.addAll(naviList)) {
							tempRI.setNavigationNodes(tempNg);
							addList.add(tempRI);
						}
					}
					genfuCommonService.merge(addList);
				}

			}

		}
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {

		if (parameters.containsKey("roleIds")
				&& parameters.containsKey("naviIds")) {

			String roleIds = parameters.get("roleIds")[0];
			String naviIds = parameters.get("naviIds")[0];

			if (roleIds.length() > 0 && naviIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();

				strBuffJPQL
						.append("DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID IN (");
				strBuffJPQL.append(roleIds);
				strBuffJPQL.append(") AND NODE_NAVI_ID IN (");
				strBuffJPQL.append(naviIds);
				strBuffJPQL.append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());

			}

		} else if (parameters.containsKey("roleIds")
				&& !parameters.containsKey("naviIds")) {

			String roleIds = parameters.get("roleIds")[0];

			if (roleIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				strBuffJPQL
						.append("DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID IN (");
				strBuffJPQL.append(roleIds).append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());
			}

		} else if (!parameters.containsKey("roleIds")
				&& parameters.containsKey("naviIds")) {

			String naviIds = parameters.get("naviIds")[0];

			if (naviIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				strBuffJPQL
						.append("DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE NODE_NAVI_ID IN (");
				strBuffJPQL.append(naviIds).append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());
			}

		}

		// if (parameters.containsKey("roleIds[]")
		// && parameters.containsKey("naviIds[]")) {
		//
		// String roleIds[] = parameters.get("roleIds[]");
		// String naviIds[] = parameters.get("naviIds[]");
		//
		// if (roleIds.length > 0 && naviIds.length > 0) {
		// StringBuffer strBuffJPQL = new StringBuffer();
		//
		// for (int i = 0; i < roleIds.length; i++) {
		// strBuffJPQL.append("," + roleIds[i]);
		// }
		//
		// strBuffJPQL.delete(0, 1);
		// // taggable = strBuffJPQL.toString();
		// strBuffJPQL
		// .insert(0,
		// "DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID IN (");
		// strBuffJPQL.append(") AND NODE_NAVI_ID IN (");
		//
		// for (int i = 0; i < naviIds.length; i++) {
		// if (i > 0) {
		// strBuffJPQL.append(",");
		// }
		// strBuffJPQL.append(naviIds[i]);
		// }
		//
		// strBuffJPQL.append(")");
		//
		// genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
		// .toString());
		//
		// }
		//
		// // if (roleIds.length > 0 && naviIds.length > 0) {
		// // StringBuffer strBuffJPQL = new StringBuffer();
		// // int i = 0;
		// // for (i = 0; i < roleIds.length; i++) {
		// // strBuffJPQL.append("," + roleIds[i]);
		// // }
		// //
		// // strBuffJPQL.delete(0, 1);
		// // strBuffJPQL.insert(0,
		// // "SELECT t FROM RoleInfo t WHERE t.id IN (");
		// // strBuffJPQL.append(") ORDER BY t.id ASC");
		// // List<RoleInfo> roleInfosglist = genfuCommonService.searchList(
		// // strBuffJPQL.toString(), null, RoleInfo.class);
		// //
		// // // navi
		// // strBuffJPQL = new StringBuffer();
		// // for (i = 0; i < naviIds.length; i++) {
		// // strBuffJPQL.append("," + naviIds[i]);
		// // }
		// // strBuffJPQL.delete(0, 1);
		// // strBuffJPQL.insert(0,
		// // "SELECT t FROM NavigationNode t WHERE t.id IN (");
		// // strBuffJPQL.append(") ORDER BY t.id ASC");
		// // List<NavigationNode> naviList = genfuCommonService.searchList(
		// // strBuffJPQL.toString(), null, NavigationNode.class);
		// //
		// // //List<RoleInfo> addList = new ArrayList<RoleInfo>();
		// // if (roleInfosglist.size() > 0 && naviList.size() > 0) {
		// // Set<NavigationNode> tempNg = new HashSet<NavigationNode>();
		// // boolean somethingWrong = false;
		// // for (RoleInfo tempRI : roleInfosglist) {
		// // tempNg.clear();
		// // tempNg = tempRI.getNavigationNodes();
		// // if (tempNg.removeAll(naviList)) {
		// // tempRI.setNavigationNodes(tempNg);
		// // } else {
		// // somethingWrong = true;
		// // break;
		// // }
		// // }
		// // if (!somethingWrong) {
		// // genfuCommonService.merge(roleInfosglist);
		// // }
		// // }
		// // }
		//
		// } else if (parameters.containsKey("roleIds[]")
		// && !parameters.containsKey("naviIds[]")) {
		//
		// String roleIds[] = parameters.get("roleIds[]");
		//
		// if (roleIds.length > 0) {
		// StringBuffer strBuffJPQL = new StringBuffer();
		// for (int i = 0; i < roleIds.length; i++) {
		// strBuffJPQL.append("," + roleIds[i]);
		// }
		// strBuffJPQL.delete(0, 1);
		// strBuffJPQL
		// .insert(0,
		// "DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID IN (");
		// strBuffJPQL.append(")");
		// genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
		// .toString());
		// }
		//
		// // if (roleIds.length > 0) {
		// // StringBuffer strBuffJPQL = new StringBuffer();
		// // int i = 0;
		// // for (i = 0; i < roleIds.length; i++) {
		// // strBuffJPQL.append("," + roleIds[i]);
		// // }
		// //
		// // strBuffJPQL.delete(0, 1);
		// // strBuffJPQL.insert(0,
		// // "SELECT t FROM RoleInfo t WHERE t.id IN (");
		// // strBuffJPQL.append(") ORDER BY t.id ASC");
		// // List<RoleInfo> roleInfosglist = genfuCommonService.searchList(
		// // strBuffJPQL.toString(), null, RoleInfo.class);
		// //
		// // List<RoleInfo> addList = new ArrayList<RoleInfo>();
		// // if (roleInfosglist.size() > 0) {
		// // Set<NavigationNode> tempNg = new HashSet<NavigationNode>();
		// // for (RoleInfo tempRI : roleInfosglist) {
		// // tempRI.setNavigationNodes(tempNg);
		// // }
		// // genfuCommonService.merge(addList);
		// // }
		// // }
		//
		// } else if (!parameters.containsKey("roleIds[]")
		// && parameters.containsKey("naviIds[]")) {
		//
		// String naviIds[] = parameters.get("naviIds[]");
		//
		// if (naviIds.length > 0) {
		// StringBuffer strBuffJPQL = new StringBuffer();
		// for (int i = 0; i < naviIds.length; i++) {
		// strBuffJPQL.append("," + naviIds[i]);
		// }
		// strBuffJPQL.delete(0, 1);
		// strBuffJPQL
		// .insert(0,
		// "DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE NODE_NAVI_ID IN (");
		// strBuffJPQL.append(")");
		// genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
		// .toString());
		// }
		//
		// }

		// genfuCommonService.remove(model);
		jsonObject = new JSONObject();
		addActionMessage("Object removed successfully");
		return "json";
	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

}
