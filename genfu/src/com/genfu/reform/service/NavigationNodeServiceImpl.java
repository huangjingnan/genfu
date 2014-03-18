package com.genfu.reform.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.genfu.reform.jpa.GenfuCommonDAO;
import com.genfu.reform.model.NavigationNode;

public class NavigationNodeServiceImpl implements NavigationNodeService {

	private GenfuCommonDAO navigationNodeDAO;

	public GenfuCommonDAO getNavigationNodeDAO() {
		return navigationNodeDAO;
	}

	public void setNavigationNodeDAO(GenfuCommonDAO theDAO) {
		this.navigationNodeDAO = theDAO;
	}

	@Override
	public List<NavigationNode> findAll() {
		return navigationNodeDAO.searchList("", null, NavigationNode.class);
	}

	@Override
	public void save(NavigationNode model) {
		// model.setDate(new Date());
		model.setNaviCreateDate(new Date());
		model.setNaviUpdateDate(new Date());
		navigationNodeDAO.save(model);
	}

	@Override
	public NavigationNode find(Long id) {
		return null;
	}

	@Override
	public void remove(NavigationNode model) {
		navigationNodeDAO.remove(model);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(NavigationNode model) {

		model.setNaviUpdateDate(new Date());// model.setDate(new Date());
		navigationNodeDAO.merge(model);
		// TODO Auto-generated method stub

	}

	@Override
	public List<NavigationNode> findAll(Map<String, Object> mapCondition) {
		return null;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		return navigationNodeDAO.searchList(jpql, parameters, entity);
	}

	public long rebuild_tree(long parent, long left) {

		long right = left + 1;
		List<NavigationNode> navigationNodeList = navigationNodeDAO.searchList(
				"SELECT x FROM NavigationNode x WHERE x.naviFlag='001' AND x.naviParentId="
						+ parent + " ORDER BY x.naviOrder ASC", null,
				NavigationNode.class);

		for (NavigationNode nN : navigationNodeList) {
			right = rebuild_tree(nN.getId(), right);
		}

		navigationNodeDAO
				.batchExcuseNativeQuery("UPDATE NAVIGATION_NODES SET NAVI_LFT="
						+ left + ",NAVI_RGT=" + right + " WHERE NAVI_ID="
						+ parent);

		return right + 1;
	}
}
