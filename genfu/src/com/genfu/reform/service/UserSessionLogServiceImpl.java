package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.jpa.UserSessionLogDAO;
import com.genfu.reform.model.UserSession;

public class UserSessionLogServiceImpl implements UserSessionLogService {

	private UserSessionLogDAO userSessionLogDAO;

	public UserSessionLogDAO getUserSessionLogDAO() {
		return userSessionLogDAO;
	}

	public void setUserSessionLogDAO(UserSessionLogDAO theDAO) {
		this.userSessionLogDAO = theDAO;
	}

	@Override
	public List<UserSession> findAll() {
		return getUserSessionLogDAO().findAll();
	}

	@Override
	public void save(UserSession model) {
		// model.setDate(new Date());
		// model.setNaviCreateDate(new Date());
		// model.setNaviUpdateDate(new Date());
		userSessionLogDAO.save(model);
	}

	@Override
	public UserSession find(Long id) {
		return userSessionLogDAO.find(id);
	}

	@Override
	public void remove(UserSession model) {
		userSessionLogDAO.remove(model);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UserSession model) {

		// model.setNaviUpdateDate(new Date());// model.setDate(new Date());
		userSessionLogDAO.merge(model);
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		return userSessionLogDAO.searchList(jpql, parameters, entity);
	}

	@Override
	public List<UserSession> findAll(Map<String, Object> mapCondition) {
		// TODO Auto-generated method stub
		return null;
	}

}
