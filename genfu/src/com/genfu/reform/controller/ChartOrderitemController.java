package com.genfu.reform.controller;

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

import com.genfu.reform.model.ChartOrderItem;
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
				"actionName", "chart-orderitem" }),
		@Result(name = "show", type = "httpheader", params = { "actionName",
				"chart-orderitem" }) })
public class ChartOrderitemController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChartOrderItem model = new ChartOrderItem();
	private Long id;
	private List<ChartOrderItem> list;
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

	public void setModel(ChartOrderItem model) {
		this.model = model;
	}

	public String show() {
		return "show";
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (null != this.parameters.get("style")) {
			// Map<String, Object> par = new HashMap<String, Object>();
			// par.put("itemStatus", "FRUITION");
			//
			// jsonObject = genfuCommonService
			// .searchJsonJqGridFilter(
			// "SELECT x FROM ChartOrderItem x WHERE x.orderId IN (SELECT y.id FROM Order y WHERE y.status='PROCESS') AND x.status=:itemStatus",
			// par, ChartOrderItem.class, parameters);
		} else {

			List<Object> chartChartOrderItems = genfuCommonService
					.searchNativeQuery(
							"select dish_id,order_item_name,sum(amount) sum_amount,sum(price*amount) sum_total from order_items group by dish_id,order_item_name",
							null, 0, 10);

			System.out.println(chartChartOrderItems.size());
			/*
			 * list = genfuCommonService.searchList(ChartOrderItem.class,
			 * parameters);
			 */
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			// model = (ChartOrderItem) genfuCommonService.find(id,
			// ChartOrderItem.class);
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
		return new DefaultHttpHeaders("thanks").setLocationId(model
				.getDish_id());
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
