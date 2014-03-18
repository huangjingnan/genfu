package com.genfu.reform.service;

import java.util.List;
import java.util.Map;

import com.genfu.reform.jpa.ActorInfoDAO;
import com.genfu.reform.model.ActorInfo;

public class ActorInfoServiceImpl implements ActorInfoService {

	private ActorInfoDAO actorInfoDAO;

	public ActorInfoDAO getActorInfoDAO() {
		return actorInfoDAO;
	}

	public void setActorInfoDAO(ActorInfoDAO theDAO) {
		this.actorInfoDAO = theDAO;
	}

	@Override
	public List<ActorInfo> findAll() {
		return getActorInfoDAO().findAll();
	}

	@Override
	public void save(ActorInfo model) {
		// model.setDate(new Date());
		// model.setNaviCreateDate(new Date());
		// model.setNaviUpdateDate(new Date());
		actorInfoDAO.save(model);
	}

	@Override
	public ActorInfo find(Long id) {
		return actorInfoDAO.find(id);
	}

	@Override
	public void remove(ActorInfo model) {
		actorInfoDAO.remove(model);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ActorInfo model) {

		// model.setNaviUpdateDate(new Date());// model.setDate(new Date());
		actorInfoDAO.merge(model);
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		return actorInfoDAO.searchList(jpql, parameters, entity);
	}

	@Override
	public List<ActorInfo> findAll(Map<String, Object> mapCondition) {
		// TODO Auto-generated method stub
		return null;
	}

}
