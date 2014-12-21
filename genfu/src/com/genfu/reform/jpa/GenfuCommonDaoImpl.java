package com.genfu.reform.jpa;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

//@Transactional
public class GenfuCommonDaoImpl implements GenfuCommonDAO {
	private static Logger logger = Logger.getLogger("GenfuCommonDaoImpl");

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {

			TypedQuery<T> query = em.createQuery(jpql, entity);
			for (Parameter<?> sqlParam : query.getParameters()) {
				query.setParameter(sqlParam.getName(),
						parameters.get(sqlParam.getName()));
			}
			if (null != parameters && parameters.containsKey("FIRST_RESULT")) {
				query.setFirstResult(Integer.parseInt(parameters.get(
						"FIRST_RESULT").toString()));
			}
			if (null != parameters && parameters.containsKey("MAX_RESULTS")) {
				query.setMaxResults(Integer.parseInt(parameters.get(
						"MAX_RESULTS").toString()));
			}

			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity, int FIRST_RESULT, int MAX_RESULTS) {
		EntityManager em = this.emf.createEntityManager();
		try {
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
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> Object find(Long id, Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {
			return em.find(entity, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int save(Object model) {
		EntityManager em = this.emf.createEntityManager();

		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(model);
			entityTransaction.commit();
			return 0;
		} finally {
			if (em != null) {
				//em.clear();
				em.close();
			}
		}
	}

	@Override
	public int merge(Object model) {
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.merge(model);
			entityTransaction.commit();
			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int remove(Object model) {
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.remove(model);
			entityTransaction.commit();
			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int remove(long id, String tableName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Object remove(List<T> list) {
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			for (Object object : list) {
				em.remove(em.merge(object));
			}
			entityTransaction.commit();
			em.clear();
			// em.close();
			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> Object merge(List<T> list) {
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			for (Object object : list) {
				em.merge(object);
			}
			entityTransaction.commit();
			em.clear();
			// em.close();

			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Object find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {
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
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public int batchDelete(String strSQL) {
		EntityManager em = this.emf.createEntityManager();
		try {
			int effectCount = 0;
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			Query myQuery = em.createNativeQuery(strSQL);
			effectCount = myQuery.executeUpdate();
			logger.info("" + effectCount);
			entityTransaction.commit();
			em.clear();
			return effectCount;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> T findModel(Long id, Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {
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
			TypedQuery<T> query = em
					.createQuery(strBuffJPQL.toString(), entity);
			return query.getSingleResult();
		} finally {
			if (em != null) {
				em.close();
			}
		}
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
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			for (Object object : list) {
				em.persist(object);
			}
			entityTransaction.commit();
			em.clear();
			// em.close();

			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {
			Query query = em.createNativeQuery(jpql, entity);
			for (Parameter<?> sqlParam : query.getParameters()) {
				String paramName = sqlParam.getName();
				query.setParameter(paramName, parameters.get(paramName));
				paramName = null;
			}
			if (null != parameters && parameters.containsKey("FIRST_RESULT")) {
				query.setFirstResult(Integer.parseInt(parameters.get(
						"FIRST_RESULT").toString()));
			}
			if (null != parameters && parameters.containsKey("MAX_RESULTS")) {
				query.setMaxResults(Integer.parseInt(parameters.get(
						"MAX_RESULTS").toString()));
			}

			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int batchExcuseNativeQuery(String strSQL) {
		EntityManager em = this.emf.createEntityManager();
		try {
			String[] exec = strSQL.split("#");
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			int ret = 0;
			for (int i = 0; i < exec.length; i++) {
				ret += em.createNativeQuery(exec[i]).executeUpdate();

				logger.info("" + ret);
			}
			entityTransaction.commit();
			em.clear();
			return ret;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		EntityManager em = this.emf.createEntityManager();
		try {
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
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity, int FIRST_RESULT,
			int MAX_RESULTS) {
		EntityManager em = this.emf.createEntityManager();
		try {
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
			logger.info("searchNativeQuery");
			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public <T> Object saveMerge(Object persistObject, List<T> mergeList) {
		EntityManager em = this.emf.createEntityManager();
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(persistObject);

			for (Object mObject : mergeList) {
				em.merge(mObject);
			}
			entityTransaction.commit();
			em.clear();
			// em.close();

			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, int FIRST_RESULT, int MAX_RESULTS) {
		EntityManager em = this.emf.createEntityManager();
		try {
			Query query = em.createNativeQuery(jpql);
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
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters) {
		EntityManager em = this.emf.createEntityManager();
		try {
			int idxOrder = jpql.indexOf("ORDER BY");
			if (idxOrder > 0) {
				jpql = jpql.substring(0, idxOrder);
			}
			Query query = em.createNativeQuery(jpql);
			for (Parameter<?> sqlParam : query.getParameters()) {
				String paramName = sqlParam.getName();
				query.setParameter(paramName, parameters.get(paramName));
				paramName = null;
			}

			return query.getResultList().size();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int excuseNativeQuery(String strSQLSplt, Map<String, Object> agr0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
