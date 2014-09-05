package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;

public class AccountDAOImpl implements GenfuCommonDAO {
	private static Logger logger = Logger.getLogger("AccountDAOImpl");
	public EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Object find(Long id, Class<T> entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
		// EntityTransaction entityTransaction = em.getTransaction();
		return em.find(entity, id);
	}

	@Override
	public int save(Object model) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(model);
		entityTransaction.commit();
		em.clear();
		em.close();
		return 0;
	}

	@Override
	public int merge(Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(long id, String tableName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Object remove(List<T> list) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		for (Object object : list) {
			em.remove(em.merge(object));
		}
		entityTransaction.commit();
		em.clear();
		em.close();

		return 0;
	}

	@Override
	public <T> Object merge(List<T> list) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		for (Object object : list) {
			em.merge(object);
		}
		entityTransaction.commit();
		em.clear();
		em.close();

		return 0;
	}

	@Override
	public Object find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findModel(Long id, Class<T> entity) {

		return null;
	}

	@Override
	public int batchDelete(String strSQL) {
		int effectCount = 0;
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		Query myQuery = em.createNativeQuery(strSQL);
		effectCount = myQuery.executeUpdate();
		logger.info("" + effectCount);
		entityTransaction.commit();
		em.clear();
		em.close();
		return effectCount;
	}

	@Override
	public <T> int batchDeleteJPQL(String strSQL, Class<T> entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		List<T> resultList = em.createQuery(strSQL, entity).getResultList();

		for (Object object : resultList) {
			em.remove(object);
		}
		entityTransaction.commit();
		em.clear();
		em.close();
		return 0;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity, int FIRST_RESULT, int MAX_RESULTS) {
		// TODO Auto-generated method stub
		return null;
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
	public <T> Object save(List<T> list) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		for (Object object : list) {
			em.persist(object);
		}
		entityTransaction.commit();
		em.clear();
		em.close();

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
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

		String[] exec = strSQL.split("#");

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		int ret = 0;
		for (int i = 0; i < exec.length; i++) {
			ret += em.createNativeQuery(exec[i]).executeUpdate();

			logger.info("" + ret);
		}
		entityTransaction.commit();
		em.clear();
		em.close();
		return ret;
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		int idxOrder = jpql.indexOf("ORDER BY");
		if (idxOrder > 0) {
			jpql = jpql.substring(0, idxOrder);
		}
		EntityManager em = entityManagerFactory.createEntityManager();
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
		EntityManager em = entityManagerFactory.createEntityManager();
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
	public <T> Object saveMerge(Object persistObject, List<T> mergeList) {
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
