package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.Event;

public interface EventDAO {
	public List<Event> findAll();

	public List<Event> searchList(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public Event find(Long id);

	public void save(Event model);

	public void merge(Event model);

	public void remove(Event model);
}
