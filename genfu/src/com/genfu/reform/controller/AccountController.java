package com.genfu.reform.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.genfu.reform.model.Account;
import com.genfu.reform.model.AccountItem;
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
		@Result(name = "login_", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=order" }),
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "account" }) })
public class AccountController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account model = new Account();
	private Long id;
	private List<Account> list;
	private List<Order> orderList;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates;

	// public AccountController(GenfuCommonService theService) {
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

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Account model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public void prepareIndex() throws Exception {
		jsonObject = genfuCommonService.validateOperates("", "", "order",
				"index", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (verifyingOperates) {

			if (this.parameters.containsKey("style")) {
				if (null != this.parameters.get("style")
						&& "jqGrid".equalsIgnoreCase(this.parameters
								.get("style")[0])) {
					jsonObject = genfuCommonService.searchJsonJqGridFilter(
							Account.class, parameters);
				}
			} else {
				// list = genfuCommonService.searchList(Account.class,
				// parameters);
			}
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// public void prepareUpdate() throws Exception {
	// jsonObject = genfuCommonService.validateOperates("", "", "order",
	// "update", null, Dish.class, parameters, session);
	//
	// verifyingOperates = jsonObject.getBoolean("validResult");
	// }

	public String update() {
		jsonObject = genfuCommonService.validateOperates("", "", "order",
				"update", null, Account.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("orderId0", model.getId());
			List<Account> theOld = genfuCommonService.searchNativeQuery(
					"SELECT * FROM ORDERS WHERE ORDER_ID=:orderId0", par,
					Account.class);
			if (!"CLOSED".equalsIgnoreCase(theOld.get(0).getStatus())
					&& !"OPEN".equalsIgnoreCase(model.getStatus())) {
				model.setUpdatedAt(new Date());
				genfuCommonService.update(model);
				addActionMessage("Object updated successfully");
			}
		}
		jsonObject = null;
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Account) genfuCommonService.find(id, Account.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		if (this.parameters.containsKey("orderId")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String[] ids = parameters.get("orderId")[0].split(",");
			List<Long> longAccountIds = new ArrayList<Long>();
			for (int i = 0; i < ids.length; i++) {
				longAccountIds.add(Long.parseLong(ids[i]));
			}

			List<String> payFlags = new ArrayList<String>();
			payFlags.add("000");
			payFlags.add("010");
			par.put("payFlag0", payFlags);
			par.put("acctOrderId0", longAccountIds);
			orderList = genfuCommonService
					.searchList(
							"SELECT x FROM Order x WHERE x.payFlag IN (:payFlag0) AND x.id IN (:acctOrderId0)",
							par, Order.class);
		}
		jsonObject = new JSONObject();
		jsonObject.put("orderList", orderList);
		return "json";
	}

	public String create() {
		jsonObject = new JSONObject();
		JSONObject userInfo = (JSONObject) session
				.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		if (this.parameters.containsKey("accOrderId")) {
			Map<String, Object> par = new HashMap<String, Object>();
			String[] ids = parameters.get("accOrderId")[0].split(",");
			if (ids.length > 0) {
				List<Long> longAccountIds = new ArrayList<Long>();
				for (int i = 0; i < ids.length; i++) {
					longAccountIds.add(Long.parseLong(ids[i]));
				}

				List<String> payFlags = new ArrayList<String>();
				payFlags.add("000");
				payFlags.add("010");
				par.put("payFlag0", payFlags);
				par.put("acctOrderId0", longAccountIds);
				orderList = genfuCommonService
						.searchList(
								"SELECT x FROM Order x WHERE x.payFlag IN (:payFlag0) AND x.id IN (:acctOrderId0)",
								par, Order.class);
				BigDecimal total = new BigDecimal(0);
				Date now = new Date();
				Set<AccountItem> tempAIs = new HashSet<AccountItem>();
				for (Order tempOrder : orderList) {
					AccountItem tempAI = new AccountItem();
					tempAI.setAccountItemName(tempOrder.getOrderName());
					tempAI.setOrderId(tempOrder.getId());
					tempAI.setSubTotal(tempOrder.getTotal());
					tempAI.setStaffNumber(userInfo.getString("userId"));
					tempAI.setCreatedAt(now);
					tempAI.setStatus(tempOrder.getPayFlag());

					total = total.add(tempAI.getSubTotal());

					tempAIs.add(tempAI);
					tempOrder.setUpdatedAt(now);
					if ("000".equalsIgnoreCase(tempOrder.getPayFlag())) {
						tempOrder.setPayFlag("001");
					} else {
						tempOrder.setPayFlag("011");
					}
				}
				if (total.equals(model.getReceivable())) {

					model.setStaffNumber(userInfo.getString("userId"));
					model.setCreatedAt(now);
					model.setAccountItems(tempAIs);
					genfuCommonService.saveUpdate(model, orderList);
					// for (Order tempOrder : orderList) {
					// if("000".equalsIgnoreCase(tempOrder.getPayFlag())){
					// tempOrder.setPayFlag("001");
					// }else{
					// tempOrder.setPayFlag("011");
					// }
					// }
				} else {
					orderList.clear();
					jsonObject.put("errorMsg", "orders has changed");
				}
			} else {
				jsonObject.put("errorMsg", "accOrderId length 0");
			}
		}
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// public void prepareDestroy() throws Exception {
	//
	// }

	public String destroy() {
		jsonObject = genfuCommonService.validateOperates("", "", "tagging",
				"destroy", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (null != parameters.get("id")) {

				Map<String, Object> tempPara = new HashMap<String, Object>();

				String[] ids = parameters.get("id")[0].split(",");
				List<Long> longAccountIds = new ArrayList<Long>();
				for (int i = 0; i < ids.length; i++) {
					longAccountIds.add(Long.parseLong(ids[i]));
				}

				tempPara.put("orderIds", longAccountIds);
				List<Account> tempAccounts = genfuCommonService.searchList(
						"SELECT x FROM Account x WHERE x.id IN(:orderIds)",
						tempPara, Account.class);
				List<Account> orderDel = new ArrayList<Account>();
				for (Account tempO : tempAccounts) {
					if ("OPEN".equalsIgnoreCase(tempO.getStatus())) {
						orderDel.add(tempO);
					}
				}

				genfuCommonService.remove(orderDel);
			}
		}

		// if ("FAILED".equalsIgnoreCase(model.getStatus())) {
		// genfuCommonService.remove(model);
		// }
		// addActionMessage("Object removed successfully");
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
		if (orderList != null) {
			return orderList;
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
