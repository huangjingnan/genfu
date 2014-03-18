package com.genfu.reform.controller;

import java.util.Date;
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

import com.genfu.reform.model.Cart;
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
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "cart-setting" }) })
public class CartSettingController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cart model;// = new Cart();
	private Long id;
	private List<Cart> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates;
	private Dish tempDish;

	// public CartController(GenfuCommonService theService) {
	// this.genfuCommonService = theService;
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
		this.session = arg0;
	}

	@Override
	public void validate() {
		// if (model.getCartName().length() < 1) {
		// addFieldError("model.title", "Title is required.");
		// }

	}

	@Override
	public Object getModel() {
		return (list != null ? list : model);
	}

	public void setModel(Cart model) {
		// this.model = model;
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	public void prepareIndex() throws Exception {
		verifyingOperates = genfuCommonService.verifyingOperates(parameters,
				session);
	}

	public HttpHeaders index() {

		if (verifyingOperates && this.parameters.containsKey("statusCode")) {
			list = genfuCommonService.searchList(Cart.class, parameters);
		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public HttpHeaders update() {
		this.model.setId(this.id);
		if (null != tempDish)
			this.model.addDish(tempDish);
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		// return "show";
		return new DefaultHttpHeaders("show").setLocationId(model.getId());
	}

	public void setId(Long id) {
		if (parameters.containsKey("dish_id")) {
			tempDish = (Dish) genfuCommonService.find(
					Long.parseLong(parameters.get("dish_id")[0]), Dish.class);
		}
		// if (id != null) {
		// this.model = (Cart) genfuCommonService.find(id, Cart.class);
		// }
		// this.id = id;
	}

	public String edit() {

		return "edit";
	}

	public String editNew() {
		return "editNew";
	}

	// @Actions({ @Action("cart/clear"), @Action("clear") })
	public String create() {

		Cart _tempCart = (Cart) genfuCommonService.find(
				Long.parseLong(parameters.get("cartID")[0]), Cart.class);
		if (null != _tempCart && _tempCart.getId() > 0) {
			session.put("cart_id", _tempCart.getId());
		}

		addActionMessage("New Object created successfully");
		// return new DefaultHttpHeaders("close");
		// return new
		// DefaultHttpHeaders("success").setLocationId(model.getId());
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		// genfuCommonService.remove(model);
		this.model.setId(this.id);
		if (null != tempDish) {
			this.model.removeDish(tempDish);
		} else if (parameters.containsKey("clear_cart")) {
			this.model.clearDishes();
		}
		genfuCommonService.update(model);
		addActionMessage("Object removed successfully");
		return "json";
	}

	@Override
	public void prepare() throws Exception {
		if (session.containsKey("cart_id")) {
			this.id = (Long) session.get("cart_id");
			this.model = (Cart) genfuCommonService.find(this.id, Cart.class);
		} else {
			this.model = new Cart();
			this.model.setCreatedAt(new Date());
			this.model.setUpdatedAt(new Date());
			genfuCommonService.save(model);
			this.id = model.getId();
			session.put("cart_id", this.id);
		}
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}
}
