package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.UserInfo;

public interface UserInfoDAO {
	public List<UserInfo> findAll();

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public UserInfo find(Long id);

	public void save(UserInfo model);

	public void merge(UserInfo model);

	public void remove(UserInfo model);
}
