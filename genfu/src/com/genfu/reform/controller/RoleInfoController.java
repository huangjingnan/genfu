package com.genfu.reform.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "role-info" }) })
public class RoleInfoController extends ValidationAwareSupport implements
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
	private List<NavigationNode> listNavi;
	private String navigationNodeIds;
	private JSONArray listNaviJson;

	public String getNavigationNodeIds() {
		return navigationNodeIds;
	}

	public void setNavigationNodeIds(String navigationNodeIds) {
		this.navigationNodeIds = navigationNodeIds;
	}

	public List<NavigationNode> getListNavi() {
		return listNavi;
	}

	public void setListNavi(List<NavigationNode> listNavi) {
		this.listNavi = listNavi;
	}

	// public RoleInfoController(GenfuCommonService theService) {
	// genfuCommonService = theService;
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
		list = genfuCommonService.searchList(RoleInfo.class, parameters);
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
		listNavi = genfuCommonService.searchList("from NavigationNode ", null,
				NavigationNode.class);
		return "edit";
	}

	public String editNew() {

		return "editNew";
	}

	@SuppressWarnings("unchecked")
	public String create() {
		if (null != navigationNodeIds && navigationNodeIds.length() > 0) {
			// listNavi = genfuCommonService.searchList(
			// "from NavigationNode where NavigationNode.id in("
			// + navigationNodeIds + ") ", null,
			// NavigationNode.class);
			model.setNavigationNodes((Set<NavigationNode>) genfuCommonService
					.searchList("from NavigationNode where NAVI_ID in("
							+ navigationNodeIds + ") ", null,
							NavigationNode.class));
		}
		genfuCommonService.save(model);
		// if ("1".equals(model.getRoleDescription())) {
		// for (int i = 2; i < 101; i++) {
		// model = new RoleInfo();
		// model.setId(i);
		// model.setRoleDescription("RoleDescription");
		// genfuCommonService.save(model);
		// }
		// }
		return "createOver";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	@Override
	public Object getModel() {
		return (list != null ? list : model);
	}

	@Override
	public void prepare() throws Exception {
		listNavi = genfuCommonService.searchList("from NavigationNode ", null,
				NavigationNode.class);
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

	public JSONArray getListNaviJson() {
		return listNaviJson;
	}

	public void setListNaviJson(JSONArray listNaviJson) {
		this.listNaviJson = listNaviJson;
	}
}
