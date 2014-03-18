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

import com.genfu.reform.model.Dish;
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
				"/loginDishes.jsp" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "catalog" }) })
public class CatalogController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dish model = new Dish();
	private Long id;
	private List<Dish> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates = false;
	private HttpServletRequest request;

	// public CatalogController(GenfuCommonService theService) {
	// genfuCommonService = theService;
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
		request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Dish model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public void prepareIndex() throws Exception {
		// verifyingOperates = genfuCommonService.verifyingOperates(parameters,
		// session);
		verifyingOperates = true;
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (verifyingOperates) {

			Map<String, Object> myparameters = new HashMap<String, Object>();
			myparameters.put("dishFlag0", "OPEN");
			int limit = 10;
			if (null != parameters.get("MAX_RESULTS")) {
				limit = Integer.parseInt(parameters.get("MAX_RESULTS")[0]);
			} else if (null != parameters.get("rows")) {
				limit = Integer.parseInt(parameters.get("rows")[0]);
			}

			int page = 1;
			if (null != parameters.get("page")) {
				page = Integer.parseInt(parameters.get("page")[0]);
			}

			myparameters.put("MAX_RESULTS", limit);
			myparameters.put("FIRST_RESULT", (limit * (page - 1)) < 0 ? 0
					: limit * page - limit);
			int records = 0;

			if (parameters.containsKey("tagId")
					&& null != parameters.get("tagId")
					&& parameters.get("tagId").length > 0) {
				myparameters.put("tagId",
						Long.parseLong(parameters.get("tagId")[0]));


				records = genfuCommonService
						.searchNativeQueryRecords(
								"SELECT * FROM DISHES X WHERE X.DISH_FLAG=:dishFlag0 AND X.DISH_ID IN (SELECT Y.TAGGABLE_ID FROM TAGGINGS Y WHERE Y.TAG_ID = :tagId)",
								myparameters, Dish.class);
				list = genfuCommonService
						.searchNativeQuery(
								"SELECT * FROM DISHES X WHERE X.DISH_FLAG=:dishFlag0 AND X.DISH_ID IN (SELECT Y.TAGGABLE_ID FROM TAGGINGS Y WHERE Y.TAG_ID = :tagId) ORDER BY DISH_ID ASC",
								myparameters, Dish.class);
			} else {
				records = genfuCommonService.getTotalRecords(
						"from Dish WHERE dishFlag=:dishFlag0 ", myparameters,
						Dish.class);
				list = genfuCommonService.searchList(
						"from Dish WHERE dishFlag=:dishFlag0 ORDER BY id ASC", myparameters,
						Dish.class);
			}

			records = (int) Math.ceil((records + 0.0) / limit);
			if (records > 1) {

				int current = 1;
				// if (page > 5) {
				// current = page - 5;
				// }
				// if (records > page + 5) {
				// records = page + 5;
				// }

				StringBuffer pager = new StringBuffer(
						"<div class=\"pagination\">");
				if (page > 1) {
					pager.append("<a href=\"javascript:void(0)\" onclick=\"pageCatalog(");
					pager.append(page - 1);
					pager.append(");\" class=\"prev_page\" rel=\"prev\">上一页</a> ");
				} else {
					pager.append("<span class=\"disabled prev_page\">上一页</span> ");
				}
				for (; current <= records; current++) {
					if (current == page) {
						pager.append("<span class=\"current\">");
						pager.append(current);
						pager.append("</span> ");
					} else {
						pager.append("<a href=\"javascript:void(0)\" onclick=\"pageCatalog(");
						pager.append(current);
						pager.append(");\">");
						pager.append(current);
						pager.append("</a> ");
					}
				}
				// 当前页是最后一页
				if (current <= page + 1) {
					pager.append("<span class=\"disabled next_page\">下一页</span></div>");
				} else {
					pager.append("<a href=\"javascript:void(0)\" onclick=\"pageCatalog(");
					pager.append(page + 1);
					pager.append(");\" class=\"next_page\" rel=\"next\">下一页</a></div>");
				}
				// parameters.put("pager", new String[] { pager.toString() });
				request.setAttribute("pager", pager.toString());
			} else {
				// parameters.put("pager", new String[] { "" });
				request.setAttribute("pager",
						"<div class=\"pagination\">&nbsp;</div>");
			}

		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		// genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Dish) genfuCommonService.find(id, Dish.class);
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
		return "createOver";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		// genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "deleteOver";
	}

	public void prepareValidate() throws Exception {
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
