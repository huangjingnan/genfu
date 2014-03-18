package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.genfu.reform.model.ActorInfo;

@Transactional
public class ActorInfoDAOImpl implements ActorInfoDAO {

	private static Logger logger = Logger.getLogger("ActorInfoDAOImpl");
	@PersistenceContext
	private EntityManager em;

	public List<ActorInfo> findAll() {
		logger.info("findAll...");
		List<ActorInfo> result = em.createQuery("from ActorInfo",
				ActorInfo.class).getResultList();
		// for (ActorInfo event : result) {
		// System.out.println("ActorInfo (" + event.getDate() + ") : "
		// + event.getTitle());
		// }
		// em.close();

		return result;
	}

	public void save(ActorInfo model) {
		logger.info("save...");
		// model.setDate(new Date());
		em.persist(model);
		// em.close();
	}

	public void merge(ActorInfo model) {
		logger.info("merge...");
		// model.setDate(new Date());
		em.merge(model);
		// em.close();
	}

	public void remove(ActorInfo model) {
		logger.info("remove...");
		em.remove(model);
		// em.close();
	}

	@Override
	public ActorInfo find(Long id) {
		logger.info("find...");
		ActorInfo tmpActorInfo = em.find(ActorInfo.class, id);
		return tmpActorInfo;
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
