package com.genfu.reform.controller.architecture;

import com.opensymphony.xwork2.ActionSupport;

public class SearchController extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String targetSearch;

	public String execute() {

		return SUCCESS;

	}

	public String index() {
		return SUCCESS;

	}

	public String getTargetSearch() {
		return targetSearch;
	}

	public void setTargetSearch(String targetSearch) {
		this.targetSearch = targetSearch;
	}
}
