package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.Event;

public interface EventService {
	public List<Event> findAll();

	public List<Event> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(Event event);

	public void remove(Long id);

	public void update(Event event);

	public void remove(Event event);

	public Event find(Long id);

}
