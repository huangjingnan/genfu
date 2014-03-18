package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.UserSession;

public interface UserSessionLogDAO {
	public List<UserSession> findAll();

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public UserSession find(Long id);

	public void save(UserSession model);

	public void merge(UserSession model);

	public void remove(UserSession model);
}
