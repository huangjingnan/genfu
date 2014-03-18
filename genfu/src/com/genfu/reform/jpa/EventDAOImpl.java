package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.genfu.reform.model.Event;

@Transactional
public class EventDAOImpl implements EventDAO {

	private static Logger logger = Logger.getLogger("EventDAOImpl");
	@PersistenceContext
	private EntityManager em;

	public List<Event> findAll() {
		logger.info("findAll...");
		List<Event> result = em.createQuery("from Event", Event.class)
				.getResultList();
		// for (Event event : result) {
		// System.out.println("Event (" + event.getDate() + ") : "
		// + event.getTitle());
		// }
		// em.close();

		return result;
	}

	public void save(Event model) {
		logger.info("save...");
		// model.setDate(new Date());
		em.persist(model);
		// em.close();
	}

	public void merge(Event model) {
		logger.info("merge...");
		// model.setDate(new Date());
		em.merge(model);
		// em.close();
	}

	public void remove(Event model) {
		logger.info("remove...");
		em.remove(model);
		// em.close();
	}

	@Override
	public Event find(Long id) {
		logger.info("find...");
		Event tmpEvent = em.find(Event.class, id);
		return tmpEvent;
	}

	@Override
	public List<Event> searchList(Map<String, Object> mapCondition) {
		logger.info("searchList...");
		StringBuffer jpql = new StringBuffer();//
		jpql.append("from Event e WHERE e.title LIKE :_title");
		TypedQuery<Event> tq = em.createQuery(jpql.toString(), Event.class);
		tq.setParameter("_title", "1");
		List<Event> result = tq.getResultList();

		// List<Event> result = em
		// .createQuery("FROM Event e WHERE e.title LIKE :title_",
		// Event.class).setParameter("title_", "1%'")
		// .setMaxResults(10).getResultList();

		// CriteriaBuilder cb = em.getCriteriaBuilder();
		// CriteriaQuery<Event> cq = cb.createQuery(Event.class);
		// Root<Event> event = cq.from(Event.class);
		// cq.where(cb.equal(event.get("title"), "1"));
		// TypedQuery<Event> tq = em.createQuery(cq);
		// List<Event> result = tq.getResultList();

		// em.close();
		return result;
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
