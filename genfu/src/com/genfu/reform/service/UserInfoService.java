package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.model.UserInfo;

public interface UserInfoService {
	public List<UserInfo> findAll();

	public List<UserInfo> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(UserInfo model);

	public void remove(Long id);

	public void update(UserInfo model);

	public void remove(UserInfo model);

	public UserInfo find(Long id);

}
