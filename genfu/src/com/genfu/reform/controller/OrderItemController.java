package com.genfu.reform.controller;

import java.util.ArrayList;
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

import com.genfu.reform.model.Dish;
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
		@Result(name = "login", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=order" }),
		@Result(name = "login_", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "order-item" }) })
public class OrderItemController extends ValidationAwareSupport implements
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

	// public OrderItemController(GenfuCommonService theService) {
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

	public void setModel(OrderItem model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		jsonObject = genfuCommonService.validateOperates("", "", "order-item",
				"index", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {

			if (null != this.parameters.get("style")
					&& null != this.parameters.get("orderId")) {
				jsonObject = genfuCommonService.searchJsonJqGridFilter(
						"SELECT x FROM OrderItem x WHERE x.orderId="
								+ parameters.get("orderId")[0],
						OrderItem.class, parameters);
			} else {
				// list = genfuCommonService.searchList(OrderItem.class,
				// parameters);
			}
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		jsonObject = genfuCommonService.validateOperates("", "", "order-item",
				"update", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("orderId0", model.getOrderId());
			List<Order> theOrder = genfuCommonService.searchNativeQuery(
					"SELECT * FROM ORDERS WHERE ORDER_ID=:orderId0", par,
					Order.class);
			if (!"CLOSED".equalsIgnoreCase(theOrder.get(0).getStatus())) {
				model.setUpdatedAt(new Date());
				genfuCommonService.update(model);
				addActionMessage("Object updated successfully");
			}
		}
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
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

	public String create() {
		// genfuCommonService.save(model);
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		jsonObject = genfuCommonService.validateOperates("", "", "order-item",
				"destroy", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (null != parameters.get("id")) {

				Map<String, Object> tempPara = new HashMap<String, Object>();

				String[] ids = parameters.get("id")[0].split(",");
				List<Long> longOItemIds = new ArrayList<Long>();
				for (int i = 0; i < ids.length; i++) {
					longOItemIds.add(Long.parseLong(ids[i]));
				}
				tempPara.put("oItemIds", longOItemIds);
				List<OrderItem> tempOItems = genfuCommonService.searchList(
						"SELECT x FROM OrderItem x WHERE x.id IN(:oItemIds)",
						tempPara, OrderItem.class);
				List<OrderItem> oItemDel = new ArrayList<OrderItem>();
				Order theOrder = null;
				for (OrderItem tempI : tempOItems) {

					if (null == theOrder) {
						theOrder = (Order) genfuCommonService.find(
								tempI.getOrderId(), Order.class);
					} else if (tempI.getOrderId() != theOrder.getId()) {
						theOrder = (Order) genfuCommonService.find(
								tempI.getOrderId(), Order.class);
					}

					if (!"CLOSED".equalsIgnoreCase(theOrder.getStatus())
							&& "OPEN".equalsIgnoreCase(tempI.getStatus())) {
						oItemDel.add(tempI);
					}
				}

				genfuCommonService.remove(oItemDel);
			}
		}
		jsonObject = new JSONObject();
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
