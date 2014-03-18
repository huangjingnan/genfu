package com.genfu.reform.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.Event;
import com.genfu.reform.service.EventService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@Results({ @Result(name = "success", type = "redirectAction", params = {
		"actionName", "event" }) })
public class EventController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Event model = new Event();// 此处必须实例化一个对象,用来新建对象
	private Long id;
	private Collection<Event> list;
	private EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

	}

	@Override
	public void setSession(Map<String, Object> arg0) {

	}

	@Override
	public void validate() {

	}

	@Override
	public Object getModel() {
		// if (mapCondition.size() > 0) {
		// // list = eventService.findAll(mapCondition);
		// list = eventService.searchList(strBuffJPQL.toString(),
		// mapCondition, Event.class);
		// }
		return (list != null ? list : model);
	}

	public void setModel(Event model) {
		this.model = model;
	}

	public HttpHeaders searchModels() {
		list = eventService.findAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	public HttpHeaders index() {
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// @Action("search")
	public HttpHeaders search() {
		list = eventService.findAll();
		return new DefaultHttpHeaders("search").disableCaching();
	}

	public String update() {
		eventService.save(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = eventService.find(id);
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
		eventService.save(model);
		addActionMessage("New Object created successfully");
		return new DefaultHttpHeaders("edit").setLocationId(model.getId());
		// return new
		// DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		eventService.remove(model);
		addActionMessage("Object removed successfully");
		return "success";
	}
}
