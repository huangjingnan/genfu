package com.genfu.reform.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "navigation-node" }) })
public class NavigationNodeController extends ValidationAwareSupport implements
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
	private boolean verifyingOperates;

	// public NavigationNodeController(GenfuCommonService navigationNodeService)
	// {
	// this.genfuCommonService = navigationNodeService;
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

	// 首先验证权限
	public void prepareIndex() throws Exception {
		verifyingOperates = genfuCommonService.verifyingOperates(parameters,
				session);
	}

	public HttpHeaders index() {

		if (verifyingOperates) {

			Map<String, Object> para = new Hashtable<String, Object>();
			if (null != parameters.get("rootId_EQ")
					&& parameters.get("rootId_EQ").length > 0) {
				para.put("rootId_EQ",
						Long.parseLong(parameters.get("rootId_EQ")[0]));
			} else {
				para.put("rootId_EQ", Long.parseLong("0"));
			}
			if (parameters.containsKey("rebuild_tree")) {
				this.rebuild_tree(-1, 0, -1);
			}
			list = genfuCommonService.searchList(
					"SELECT t FROM NavigationNode t WHERE t.id = :rootId_EQ",
					para, NavigationNode.class);

			if (list.size() > 0) {
				// 说明有此节点，获得树根对应的树
				model = list.get(0);
				para.clear();
				// para.put("MAX_RESULTS", 65536);
				para.put("NAVI_LFT_GE", this.model.getLft());
				para.put("NAVI_LFT_LE", this.model.getRgt());

				list = genfuCommonService
						.searchList(
								"SELECT t FROM NavigationNode t WHERE t.lft BETWEEN :NAVI_LFT_GE AND :NAVI_LFT_LE ORDER BY t.lft ASC",
								para, NavigationNode.class);
			}

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
		genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "success";
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}

	private long rebuild_tree(long parent, long left, long level) {

		long right = left + 1;
		List<NavigationNode> navigationNodeList = genfuCommonService
				.searchList(
						"SELECT x FROM NavigationNode x WHERE x.naviFlag='001' AND x.naviParentId="
								+ parent + " ORDER BY x.naviOrder ASC", null,
						NavigationNode.class);

		for (NavigationNode nN : navigationNodeList) {
			right = this.rebuild_tree(nN.getId(), right, level + 1);
		}

		genfuCommonService
				.batchExcuseNativeQuery("UPDATE NAVIGATION_NODES SET NAVI_LFT="
						+ left + ",NAVI_RGT=" + right + ",NAVI_LEVEL=" + level
						+ " WHERE NAVI_ID=" + parent);

		return right + 1;
	}

}
