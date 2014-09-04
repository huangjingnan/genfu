package com.genfu.reform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
		@Result(name = "login_", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=order" }),
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "order" }) })
public class OrderController extends ValidationAwareSupport implements
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
	private GenfuCommonService orderService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;

	// public OrderController(GenfuCommonService theService) {
	// orderService = theService;
	// }

	public GenfuCommonService getGenfuCommonService() {
		return orderService;
	}

	@Resource(name = "orderService")
	public void setGenfuCommonService(GenfuCommonService orderService) {
		this.orderService = orderService;
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
				jsonObject = orderService.searchJsonJqGridFilter(Order.class,
						parameters);
			}
		} else {
			// list = orderService.searchList(Order.class,
			// parameters);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		orderService.update(model);
		// addActionMessage("Object updated successfully");
		// jsonObject = null;
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Order) orderService.find(id, Order.class);
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
		// orderService.save(model);
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// public void prepareDestroy() throws Exception {
	//
	// }

	public String destroy() {
		if (null != parameters.get("id")) {

			Map<String, Object> tempPara = new HashMap<String, Object>();

			String[] ids = parameters.get("id")[0].split(",");
			List<Long> longOrderIds = new ArrayList<Long>();
			for (int i = 0; i < ids.length; i++) {
				longOrderIds.add(Long.parseLong(ids[i]));
			}

			tempPara.put("orderIds", longOrderIds);

			orderService
					.excuseNativeQuery(
							"delete From OrderItem t WHERE t.orderId IN(:orderIds)#delete From Order t WHERE t.status='OPEN' AND t.payFlag='000' AND t.id IN(:orderIds)",
							tempPara);
		}
		jsonObject = new JSONObject();
		return "json";
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
