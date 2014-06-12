package com.genfu.reform.jpa;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.genfu.reform.model.NavigationNode;

@Transactional
public class NavigationNodeDAOImpl implements NavigationNodeDAO {

	// private static Logger logger = Logger.getLogger("NavigationNodeDAOImpl");
	@PersistenceContext
	private EntityManager em;

	public List<NavigationNode> findAll() {
//		logger.info("findAll...");
		List<NavigationNode> result = em.createQuery(
				"from NavigationNode ORDER BY NAVI_PARENT_ID, NAVI_ID ASC",
				NavigationNode.class).getResultList();
		// for (NavigationNode event : result) {
		// System.out.println("NavigationNode (" + event.getDate() + ") : "
		// + event.getTitle());
		// }
		// em.close();

		return result;
	}

	public void save(NavigationNode model) {
//		logger.info("save...");
		// model.setDate(new Date());
		em.persist(model);
		// em.close();
	}

	public void merge(NavigationNode model) {
//		logger.info("merge...");
		// model.setDate(new Date());
		em.merge(model);
		// em.close();
	}

	public void remove(NavigationNode model) {
//		logger.info("remove...");
		em.remove(model);
		// em.close();
	}

	@Override
	public NavigationNode find(Long id) {
//		logger.info("find...");
		NavigationNode tmpNavigationNode = em.find(NavigationNode.class, id);
		return tmpNavigationNode;
	}

	@Override
	public List<NavigationNode> searchList(Map<String, Object> mapCondition) {
//		logger.info("searchList...");
		StringBuffer jpql = new StringBuffer();//
		jpql.append("from NavigationNode WHERE STATEDATE<=:_STATEDATE");
		TypedQuery<NavigationNode> TypedQuery = em.createQuery(jpql.toString(),
				NavigationNode.class);
		TypedQuery.setParameter("_STATEDATE", new Date());
		List<NavigationNode> result = TypedQuery.getResultList();
		// em.close();

		return result;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
//		logger.info("searchList...3");
		TypedQuery<T> query = em.createQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			query.setParameter(sqlParam.getName(),
					parameters.get(sqlParam.getName()));
		}
		return query.getResultList();
	}

}
