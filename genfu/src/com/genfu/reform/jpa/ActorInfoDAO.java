package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.ActorInfo;

public interface ActorInfoDAO {
	public List<ActorInfo> findAll();

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public ActorInfo find(Long id);

	public void save(ActorInfo model);

	public void merge(ActorInfo model);

	public void remove(ActorInfo model);
}
