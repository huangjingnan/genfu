package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.jpa.EventDAO;
import com.genfu.reform.model.Event;

public class EventServiceImpl implements EventService {

	private EventDAO eventDAO;

	public EventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	@Override
	public List<Event> findAll() {
		return getEventDAO().findAll();
	}

	@Override
	public void save(Event model) {
		// model.setDate(new Date());
		eventDAO.save(model);
	}

	@Override
	public Event find(Long id) {
		return eventDAO.find(id);
	}

	@Override
	public void remove(Event model) {
		eventDAO.remove(model);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Event model) {

		// model.setDate(new Date());
		eventDAO.merge(model);
		// TODO Auto-generated method stub

	}

	@Override
	public List<Event> findAll(Map<String, Object> mapCondition) {
		return getEventDAO().searchList(mapCondition);
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		return eventDAO.searchList(jpql, parameters, entity);
	}

}
