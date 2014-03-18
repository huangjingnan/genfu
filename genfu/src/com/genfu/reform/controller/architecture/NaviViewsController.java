package com.genfu.reform.controller.architecture;

import com.genfu.reform.service.NavigationNodeService;
import com.genfu.reform.util.TreeUtil;
import com.opensymphony.xwork2.ActionSupport;

public class NaviViewsController extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String treeString = "";
	private NavigationNodeService navigationNodeService;
	private TreeUtil treeUtilService;

	public NavigationNodeService getNavigationNodeService() {
		return navigationNodeService;
	}

	public void setNavigationNodeService(
			NavigationNodeService navigationNodeService) {
		this.navigationNodeService = navigationNodeService;
	}

	public TreeUtil getTreeUtilService() {
		return treeUtilService;
	}

	public void setTreeUtilService(TreeUtil treeUtilService) {
		this.treeUtilService = treeUtilService;
	}

	public NaviViewsController(NavigationNodeService theService,
			TreeUtil theTreeService) {
		this.navigationNodeService = theService;
		this.treeUtilService = theTreeService;
	}

	public String getTreeString() {
		return treeString;
	}

	public void setTreeString(String treeString) {
		this.treeString = treeString;
	}

	public String index() {
		treeString = treeUtilService.structureNaviTree(navigationNodeService
				.findAll());
		return SUCCESS;

	}
}
