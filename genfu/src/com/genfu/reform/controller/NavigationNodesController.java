package com.genfu.reform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				"actionName", "navigation-nodes" }) })
public class NavigationNodesController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationNode model = new NavigationNode();// 此处必须实例化一个对象,用来新建对象
	private Long id;
	private List<NavigationNode> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private JSONObject jsonObject;

	// public NavigationNodesController(GenfuCommonService theService) {
	// this.genfuCommonService = theService;
	// }

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

	public void setModel(NavigationNode model) {
		this.model = model;
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	public HttpHeaders index() {

		if (this.parameters.containsKey("style")) {
			if (null != this.parameters.get("roleId")) {
				Map<String, Object> para = new HashMap<String, Object>();
				para.put("roleId", Long.parseLong(parameters.get("roleId")[0]));

				jsonObject = genfuCommonService
						.searchJsonNativeQuery(
								"SELECT * FROM NAVIGATION_NODES X WHERE X.NAVI_ID IN (SELECT NODE_NAVI_ID FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID=:roleId)",
								para, NavigationNode.class, parameters);
				// jsonObject = new JSONObject();
				// jsonObject.put("page", 1);
				// jsonObject.put("total", 1);
				// // jsonObject.put("records", 2000);
				// List<NavigationNode> rows = genfuCommonService
				// .searchNativeQuery(
				// "SELECT * FROM NAVIGATION_NODES X WHERE X.NAVI_ID IN (SELECT NODE_NAVI_ID FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID=:roleId)",
				// para, NavigationNode.class);
				// jsonObject.put("records", rows.size());
				// jsonObject.put("rows", rows);
			} else if (null != this.parameters.get("style")
					&& "jqGrid"
							.equalsIgnoreCase(this.parameters.get("style")[0])) {
				jsonObject = genfuCommonService.searchJsonJqGridFilter(
						NavigationNode.class, parameters);
			}
		} else {
			list = genfuCommonService.searchList(NavigationNode.class,
					parameters);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = (NavigationNode) genfuCommonService.find(id,
					NavigationNode.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		return "editNew";
	}

	public HttpHeaders create() {
		genfuCommonService.save(model);
		addActionMessage("New Object created successfully");
		return new DefaultHttpHeaders("index").setLocationId(model.getId());
		// return new
		// DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {

		if (null != parameters.get("roleId") && null != parameters.get("id")) {

			String roleId = parameters.get("roleId")[0];
			String naviIds = parameters.get("id")[0];

			if (roleId.length() > 0 && naviIds.length() > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();

				strBuffJPQL
						.append("DELETE FROM ROLE_INFO_NAVIGATION_NODES WHERE ROLE_ROLE_ID IN (");
				strBuffJPQL.append(roleId);
				strBuffJPQL.append(") AND NODE_NAVI_ID IN (");
				strBuffJPQL.append(naviIds);
				strBuffJPQL.append(")");
				genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
						.toString());

			}

		}

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
