package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.genfu.reform.model.UserInfo;

@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {

//	private static Logger logger = Logger.getLogger("UserInfoDAOImpl");
	@PersistenceContext
	private EntityManager em;

	public List<UserInfo> findAll() {
//		logger.info("findAll...");
		List<UserInfo> result = em.createQuery("from UserInfo", UserInfo.class)
				.getResultList();
		// for (UserInfo event : result) {
		// System.out.println("UserInfo (" + event.getDate() + ") : "
		// + event.getTitle());
		// }
		// em.close();

		return result;
	}

	public void save(UserInfo model) {
//		logger.info("save...");
		// model.setDate(new Date());
		em.persist(model);
		// em.close();
	}

	public void merge(UserInfo model) {
//		logger.info("merge...");
		// model.setDate(new Date());
		em.merge(model);
		// em.close();
	}

	public void remove(UserInfo model) {
//		logger.info("remove...");
		em.remove(model);
		// em.close();
	}

	@Override
	public UserInfo find(Long id) {
//		logger.info("find...");
		return em.find(UserInfo.class, id);
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
