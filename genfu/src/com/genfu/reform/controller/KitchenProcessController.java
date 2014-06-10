package com.genfu.reform.controller;

import java.util.Date;
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

import com.genfu.reform.model.Order;
import com.genfu.reform.model.OrderItem;
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
				"actionName", "kitchen-process" }),
		@Result(name = "show", type = "httpheader", params = { "actionName",
				"kitchen-process" }) })
public class KitchenProcessController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderItem model = new OrderItem();
	private Long id;
	private List<OrderItem> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates;

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

	public void setModel(OrderItem model) {
		this.model = model;
	}

	public String show() {
		return "show";
	}

	public void prepareIndex() throws Exception {
		// verifyingOperates = genfuCommonService.verifyingOperates(parameters,
		// session);
		verifyingOperates = true;
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (verifyingOperates) {
			if (null != this.parameters.get("style")) {
				Map<String, Object> par = new HashMap<String, Object>();
				par.put("itemStatus", "PROCESS");

				jsonObject = genfuCommonService.searchJsonJqGridFilter(
						"SELECT x FROM OrderItem x WHERE x.status=:itemStatus",
						par, OrderItem.class, parameters);
			} else {
				/*
				 * list = genfuCommonService.searchList(OrderItem.class,
				 * parameters);
				 */
			}
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		Order theOrder = new Order();
		if (parameters.containsKey("orderItemId")) {

			String orderItemId = parameters.get("orderItemId")[0];
			StringBuffer strBuffJPQL = new StringBuffer();
			strBuffJPQL
					.append("SELECT t FROM OrderItem t WHERE t.status = 'OPEN' AND t.id IN (");
			strBuffJPQL.append(orderItemId).append(") ORDER BY t.orderId ASC");

			List<OrderItem> oitemList = genfuCommonService.searchList(
					strBuffJPQL.toString(), null, OrderItem.class);

			Map<String, Object> param = new HashMap<String, Object>();

			strBuffJPQL = new StringBuffer(
					"UPDATE ORDER_ITEMS SET STATUS = :_status, UPDATED_AT = :_now WHERE STATUS = :_status_old AND ORDER_ITEM_ID IN(0");

			for (OrderItem oi : oitemList) {
				if (theOrder.getId() != oi.getOrderId()) {
					theOrder = (Order) genfuCommonService.find(oi.getOrderId(),
							Order.class);
				}
				if ("PROCESS".equalsIgnoreCase(theOrder.getStatus())) {
					strBuffJPQL.append(",");
					strBuffJPQL.append(oi.getId());
				}

			}
			strBuffJPQL.append(")");

			param.put("_status", "PROCESS");
			param.put("_now", new Date());
			param.put("_status_old", "OPEN");
			genfuCommonService.excuseNativeQuery(strBuffJPQL.toString(), param);
		} else if (model.getId() > 0
				&& "OPEN".equalsIgnoreCase(model.getStatus())) {

			theOrder = (Order) genfuCommonService.find(model.getOrderId(),
					Order.class);
			if ("PROCESS".equalsIgnoreCase(theOrder.getStatus())) {
				model.setStatus("PROCESS");
				model.setUpdatedAt(new Date());
				genfuCommonService.update(model);
			}
		}
		return "json";
	}

	public void setId(Long id) {
		if (id != null && id > 0) {
			model = (OrderItem) genfuCommonService.find(id, OrderItem.class);
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
		// genfuCommonService.save(model);
		return new DefaultHttpHeaders("thanks").setLocationId(model.getId());
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
