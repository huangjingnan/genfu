package com.genfu.reform.jpa;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenfuCommonDAOImpl implements GenfuCommonDAO {
	private static Logger logger = Logger.getLogger("GenfuCommonDAOImpl");
	@PersistenceContext
	private EntityManager em;

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		logger.info("searchList...");
		TypedQuery<T> query = em.createQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			query.setParameter(sqlParam.getName(),
					parameters.get(sqlParam.getName()));
		}
		if (null != parameters && parameters.containsKey("FIRST_RESULT")) {
			query.setFirstResult(Integer.parseInt(parameters
					.get("FIRST_RESULT").toString()));
		}
		if (null != parameters && parameters.containsKey("MAX_RESULTS")) {
			query.setMaxResults(Integer.parseInt(parameters.get("MAX_RESULTS")
					.toString()));
		}

		return query.getResultList();
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity, int FIRST_RESULT, int MAX_RESULTS) {
		//logger.info("searchModel...");
		TypedQuery<T> query = em.createQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			query.setParameter(sqlParam.getName(),
					parameters.get(sqlParam.getName()));
		}
		if (FIRST_RESULT > -1) {
			query.setFirstResult(FIRST_RESULT);
		}
		if (MAX_RESULTS > 0) {
			query.setMaxResults(MAX_RESULTS);
		}

		return query.getResultList();
	}

	@Override
	public <T> Object find(Long id, Class<T> entity) {
		//logger.info("find...");
		// em.
		return em.find(entity, id);
	}

	@Override
	public int save(Object model) {
		//logger.info("save...");
		em.persist(model);
		return 0;
	}

	@Override
	public int merge(Object model) {
		//logger.info("merge...");
		em.merge(model);
		return 0;
	}

	@Override
	public int remove(Object model) {
		//logger.info("remove...");
		em.remove(model);
		return 0;
	}

	@Override
	public int remove(long id, String tableName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Object remove(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Object merge(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		int idxOrder = jpql.indexOf("ORDER BY");
		if (idxOrder > 0) {
			jpql = jpql.substring(0, idxOrder);
		}
		TypedQuery<T> query = em.createQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			query.setParameter(paramName, parameters.get(paramName));
			paramName = null;
		}
		return query.getResultList().size();
	}

	@Override
	public int batchDelete(String strSQL) {
		// int effectCount = 0;
		// Query myQuery = em.createNativeQuery(strSQL);
		// effectCount = myQuery.executeUpdate();
		return 0;
	}

	@Override
	public <T> T findModel(Long id, Class<T> entity) {
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE ");
		Field[] fds = entity.getDeclaredFields();
		for (int i = 0; i < fds.length; i++) {
			Id myId = fds[i].getAnnotation(javax.persistence.Id.class);
			if (myId != null) {
				javax.persistence.Column myColumn = fds[i]
						.getAnnotation(javax.persistence.Column.class);
				strBuffJPQL.append(myColumn.name() + " = ");
				break;
			}
		}
		strBuffJPQL.append(id);
		TypedQuery<T> query = em.createQuery(strBuffJPQL.toString(), entity);
		return query.getSingleResult();
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode, Map<String, Object> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey,
			Map<String, Object> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int batchDeleteJPQL(String strSQL, Class<T> entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Object save(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		Query query = em.createNativeQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			query.setParameter(paramName, parameters.get(paramName));
			paramName = null;
		}
		if (null != parameters && parameters.containsKey("FIRST_RESULT")) {
			query.setFirstResult(Integer.parseInt(parameters
					.get("FIRST_RESULT").toString()));
		}
		if (null != parameters && parameters.containsKey("MAX_RESULTS")) {
			query.setMaxResults(Integer.parseInt(parameters.get("MAX_RESULTS")
					.toString()));
		}

		return query.getResultList();
	}

	@Override
	public int batchExcuseNativeQuery(String strSQL) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		int idxOrder = jpql.indexOf("ORDER BY");
		if (idxOrder > 0) {
			jpql = jpql.substring(0, idxOrder);
		}
		Query query = em.createNativeQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			query.setParameter(paramName, parameters.get(paramName));
			paramName = null;
		}

		return query.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity, int FIRST_RESULT,
			int MAX_RESULTS) {
		Query query = em.createNativeQuery(jpql, entity);
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			query.setParameter(paramName, parameters.get(paramName));
			paramName = null;
		}
		if (FIRST_RESULT > -1) {
			query.setFirstResult(FIRST_RESULT);
		}
		if (MAX_RESULTS > 0) {
			query.setMaxResults(MAX_RESULTS);
		}
		return query.getResultList();
	}

	@Override
	public <T> Object saveMerge(Object object, List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, int FIRST_RESULT, int MAX_RESULTS) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int excuseNativeQuery(String strSQLSplt, Map<String, Object> agr0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
