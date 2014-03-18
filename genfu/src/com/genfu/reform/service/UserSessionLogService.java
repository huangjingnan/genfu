package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.UserSession;

public interface UserSessionLogService {
	public List<UserSession> findAll();

	public List<UserSession> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(UserSession model);

	public void remove(Long id);

	public void update(UserSession model);

	public void remove(UserSession model);

	public UserSession find(Long id);

}
