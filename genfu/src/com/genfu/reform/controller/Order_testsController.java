package com.genfu.reform.controller;

import java.util.Collection;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.Order_test;
import com.genfu.reform.service.Order_testsService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({ @Result(name = "success", type = "redirectAction", params = {
		"actionName", "order_tests" }) })
public class Order_testsController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable {

	private Order_test model = new Order_test();
	private String id;
	private Collection<Order_test> list;
	private Order_testsService ordersService = new Order_testsService();

	// ApplicationContext ctx = new
	// FileSystemXmlApplicationContext("applicationContext.xml");

	// GET /orders/1
	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// GET /orders
	public HttpHeaders index() {
		list = ordersService.getAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// GET /orders/1/edit
	public String edit() {
		return "edit";
	}

	// GET /orders/new
	public String editNew() {
		model = new Order_test();
		return "editNew";
	}

	// GET /orders/1/deleteConfirm
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// DELETE /orders/1
	public String destroy() {
		ordersService.remove(id);
		addActionMessage("Order removed successfully");
		return "success";
	}

	// POST /orders
	public HttpHeaders create() {
		ordersService.save(model);
		addActionMessage("New order created successfully");
		return new DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	// PUT /orders/1
	public String update() {
		ordersService.save(model);
		addActionMessage("Order updated successfully");
		return "success";
	}

	public void validate() {
		if (model.getClientName() == null
				|| model.getClientName().length() == 0) {
			addFieldError("clientName", "The client name is empty");
		}
	}

	public void setId(String id) {
		if (id != null) {
			this.model = ordersService.get(id);
		}
		this.id = id;
	}

	public Object getModel() {
		return (list != null ? list : model);
	}

}
