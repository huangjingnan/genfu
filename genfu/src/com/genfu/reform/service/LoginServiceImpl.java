package com.genfu.reform.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.genfu.reform.controller.GenfuAuthenticationInterceptor;
import com.genfu.reform.jpa.UserInfoDAO;
import com.genfu.reform.model.GenfuLog;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.model.UserSession;

public class LoginServiceImpl implements LoginService {

	private UserInfoDAO userInfoDAO;

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO theDAO) {
		this.userInfoDAO = theDAO;
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> findAll(Map<String, Object> mapCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserInfo model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserInfo model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(UserInfo model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInfo find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSession authentication(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authentication(HttpServletRequest request,
			UserInfo userInfo, Map<String, Object> session) {
		
		
		
		return false;
	}

	

}
