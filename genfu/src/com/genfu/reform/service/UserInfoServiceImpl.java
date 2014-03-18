package com.genfu.reform.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.genfu.reform.jpa.UserInfoDAO;
import com.genfu.reform.model.UserInfo;

public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDAO userInfoDAO;

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO theDAO) {
		this.userInfoDAO = theDAO;
	}

	@Override
	public List<UserInfo> findAll() {
		return getUserInfoDAO().findAll();
	}

	@Override
	public void save(UserInfo model) {
		// model.setDate(new Date());
		// model.setUserUpdateDate(new Date());
		// model.setUserCreateDate(new Date());
		userInfoDAO.save(model);
		if (1 == model.getId()) {
			for (int i = 2; i < 53; i++) {
				UserInfo modela = new UserInfo();
				modela.setId(i);
				modela.setUserUpperId(i - 1);
				modela.setUserUpdateDate(new Date());
				modela.setUserCreateDate(new Date());
				userInfoDAO.save(modela);
			}
		}
	}

	@Override
	public UserInfo find(Long id) {
		return userInfoDAO.find(id);
	}

	@Override
	public void remove(UserInfo model) {
		userInfoDAO.remove(model);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UserInfo model) {

		// model.setNaviUpdateDate(new Date());// model.setDate(new Date());
		userInfoDAO.merge(model);
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		return userInfoDAO.searchList(jpql, parameters, entity);
	}

	@Override
	public List<UserInfo> findAll(Map<String, Object> mapCondition) {
		// TODO Auto-generated method stub
		return null;
	}

}
