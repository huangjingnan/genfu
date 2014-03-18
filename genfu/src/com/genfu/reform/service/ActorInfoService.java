package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.ActorInfo;

public interface ActorInfoService {
	public List<ActorInfo> findAll();

	public List<ActorInfo> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(ActorInfo ActorInfo);

	public void remove(Long id);

	public void update(ActorInfo ActorInfo);

	public void remove(ActorInfo ActorInfo);

	public ActorInfo find(Long id);

}
