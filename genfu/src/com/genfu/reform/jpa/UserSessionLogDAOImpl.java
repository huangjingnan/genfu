package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.genfu.reform.model.UserSession;

@Transactional
public class UserSessionLogDAOImpl implements UserSessionLogDAO {

	private static Logger logger = Logger.getLogger("UserSessionLogDAOImpl");
	@PersistenceContext
	private EntityManager em;

	public List<UserSession> findAll() {
		logger.info("findAll...");
		List<UserSession> result = em.createQuery("from UserSessionLog",
				UserSession.class).getResultList();
		// for (UserSessionLog event : result) {
		// System.out.println("UserSessionLog (" + event.getDate() + ") : "
		// + event.getTitle());
		// }
		// em.close();

		return result;
	}

	public void save(UserSession model) {
		logger.info("save...");
		// model.setDate(new Date());
		em.persist(model);
		// em.close();
	}

	public void merge(UserSession model) {
		logger.info("merge...");
		// model.setDate(new Date());
		em.merge(model);
		// em.close();
	}

	public void remove(UserSession model) {
		logger.info("remove...");
		em.remove(model);
		// em.close();
	}

	@Override
	public UserSession find(Long id) {
		logger.info("find...");
		UserSession tmpUserSessionLog = em.find(UserSession.class, id);
		return tmpUserSessionLog;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		logger.info("searchList...3");
		TypedQuery<T> query = em.createQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			query.setParameter(sqlParam.getName(),
					parameters.get(sqlParam.getName()));
		}
		return query.getResultList();
	}

}
