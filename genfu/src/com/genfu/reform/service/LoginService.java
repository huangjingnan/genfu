package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.genfu.reform.model.UserInfo;
import com.genfu.reform.model.UserSession;

public interface LoginService {
	public List<UserInfo> findAll();

	public List<UserInfo> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public void save(UserInfo model);

	public void remove(Long id);

	public void update(UserInfo model);

	public void remove(UserInfo model);

	public UserInfo find(Long id);

	public UserSession authentication(UserInfo userInfo);

	public boolean authentication(HttpServletRequest request,
			UserInfo userInfo, Map<String, Object> session);

}
