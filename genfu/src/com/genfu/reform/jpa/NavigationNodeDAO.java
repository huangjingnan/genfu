package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.NavigationNode;

public interface NavigationNodeDAO {
	public List<NavigationNode> findAll();

	public List<NavigationNode> searchList(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public NavigationNode find(Long id);

	public void save(NavigationNode model);

	public void merge(NavigationNode model);

	public void remove(NavigationNode model);

}
