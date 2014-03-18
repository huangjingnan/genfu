package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.NavigationNode;

public interface NavigationNodeService {
	public List<NavigationNode> findAll();

	public List<NavigationNode> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(NavigationNode navigationNode);

	public void remove(Long id);

	public void update(NavigationNode navigationNode);

	public void remove(NavigationNode navigationNode);

	public NavigationNode find(Long id);

}
