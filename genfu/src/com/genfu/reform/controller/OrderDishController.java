package com.genfu.reform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
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
import com.genfu.reform.model.Order;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=order" }),
		@Result(name = "login_", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "order-dish" }) })
public class OrderDishController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order model = new Order();
	private Long id;
	private List<Order> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;

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
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Order model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (this.parameters.containsKey("style")) {
			if (null != this.parameters.get("style")
					&& "jqGrid"
							.equalsIgnoreCase(this.parameters.get("style")[0])) {
				jsonObject = genfuCommonService.searchJsonJqGridFilter(
						Order.class, parameters);
			}
		} else {
			// list = genfuCommonService.searchList(Order.class,
			// parameters);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		if (!"CLOSED".equalsIgnoreCase(model.getStatus())
				&& parameters.containsKey("dishIds[]")) {

			Map<String, Object> tempPara = new Hashtable<String, Object>();

			String[] dishIds = parameters.get("dishIds[]");
			List<Long> longDishIds = new ArrayList<Long>();

			for (int i = 0; i < dishIds.length; i++) {
				longDishIds.add(Long.parseLong(dishIds[i]));
			}

			tempPara.put("dishIds", longDishIds);

			List<Dish> tempDishes = genfuCommonService.searchList(
					"SELECT x FROM Dish x WHERE x.id IN(:dishIds)", tempPara,
					Dish.class);

			if (tempDishes.size() > 0) {
				model.PlaceOrder(tempDishes,
						Long.parseLong(parameters.get("orderAmount")[0]));
				model.setUpdatedAt(new Date());
				genfuCommonService.update(model);
				addActionMessage("Object updated successfully");
			}
		}
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Order) genfuCommonService.find(id, Order.class);
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
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		// genfuCommonService.remove(model);
		// addActionMessage("Object removed successfully");
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
