package com.genfu.reform.service;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.genfu.reform.jpa.GenfuCommonDAO;
import com.genfu.reform.model.GenfuConfig;
import com.genfu.reform.model.GenfuResultIdCell;
import com.genfu.reform.util.FabricationFilterSQL;
import com.genfu.reform.util.FabricationFilterSQLImpl;

@Transactional
public class CartServiceImpl implements CartService {
	private EntityManager em;

	@PersistenceContext
	public void SetEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public <T> List<T> findAll() {
		return null;
	}

	@Override
	public <T> List<T> findAll(Map<String, Object> mapCondition) {
		return null;
	}

	@Override
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
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
		// return genfuCommonDao.searchList(jpql, parameters, entity);
	}

	@Override
	public int save(Object object) {
		em.persist(object);
		return 0;
	}

	@Override
	public int remove(long id, String tableName) {
		// em.remove(id, tableName);
		return 0;
	}

	@Override
	public int remove(Object object) {
		em.remove(object);
		return 0;
	}

	@Override
	public <T> Object remove(List<T> list) {
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			for (Object object : list) {
				em.remove(em.merge(object));
			}
			entityTransaction.commit();
			// em.flush();
			// em.close();
			return 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public int update(Object object) {
		em.merge(object);
		return 0;
	}

	@Override
	public <T> Object update(List<T> list) {

		return em.merge(list);
	}

	@Override
	public Object find(long id) {
		return null;
	}

	@Override
	public <T> Object find(Long id, Class<T> entity) {
		try {
			return em.find(entity, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity) {
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
	public <T> Map<String, Object> fabricationSQL(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if ("statusCode".equalsIgnoreCase(tempKey)) {
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey);
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey);
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey);
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey);
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey);
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("page".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey);
				tempValue = (Integer.parseInt(tempValue.toString()) - 1)
						* Integer.parseInt(arg0.get("MAX_RESULTS").toString());
				mapCondition.put("FIRST_RESULT", tempValue.toString());
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("MAX_RESULTS".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey);
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}

		Map<String, Object> returnFabricationSQL = new Hashtable<String, Object>();
		returnFabricationSQL.put("strBuffJPQL", strBuffJPQL);
		returnFabricationSQL.put("mapCondition", mapCondition);
		return returnFabricationSQL;
	}

	@Override
	public int batchDelete(String className, String strSQL) {
		StringBuffer sqlStringBuffer = new StringBuffer();
		try {
			Class<?> theClass = Class.forName(className);
			Table myTable = theClass
					.getAnnotation(javax.persistence.Table.class);

			sqlStringBuffer.append("DELETE FROM " + myTable.name() + " WHERE ");

			Field fd;
			try {
				fd = theClass.getField("id");
				javax.persistence.Column myColumn = fd
						.getAnnotation(javax.persistence.Column.class);
				sqlStringBuffer.append(myColumn.name());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			// myTable.name();
			// Field[] fds = theClass.getDeclaredFields();
			// for (int i = 0; i < fds.length; i++) {
			// // System.out.print(fds[i].getName());
			// // System.out.println("：" + fds[i].getGenericType());
			// Id id = fds[i].getAnnotation(javax.persistence.Id.class);
			// if (id != null) {
			// javax.persistence.Column myColumn = fds[i]
			// .getAnnotation(javax.persistence.Column.class);
			// sqlStringBuffer.append(myColumn.name());
			// // System.out.println(myColumn.name());
			// break;
			// }
			// }
			sqlStringBuffer.append(" IN(" + strSQL + ")");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			int effectCount = 0;
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			Query myQuery = em.createNativeQuery(sqlStringBuffer.toString());
			effectCount = myQuery.executeUpdate();
			entityTransaction.commit();
			em.clear();
			return effectCount;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		// return genfuCommonDao.batchDelete(sqlStringBuffer.toString());
		// return genfuCommonDao.batchDelete(strSQL);
	}

	@Override
	public <T> Object findModel(Long id, Class<T> entity) {
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
	public int batchDeleteJPQL(String className, String strSQL) {
		// StringBuffer sqlStringBuffer = new StringBuffer();
		// sqlStringBuffer.append("from " + className + " WHERE id IN (" +
		// strSQL
		// + ")");
		// Class<?> theClass = null;
		// try {
		// theClass = Class.forName(className);
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		return 0;
	}

	@Override
	public <T> List<T> searchList(Class<T> entity, Map<String, String[]> arg0) {

		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if ("statusCode".equalsIgnoreCase(tempKey)) {
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey)[0];
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		// int total = 1;
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			}
			// else if (page > total) {
			// page = total;
			// }
		}

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		// return genfuCommonDao.searchList(strBuffJPQL.toString(),
		// mapCondition,
		// entity, (page - 1) * limit, limit);

		try {
			TypedQuery<T> query = em
					.createQuery(strBuffJPQL.toString(), entity);
			for (Parameter<?> sqlParam : query.getParameters()) {
				query.setParameter(sqlParam.getName(),
						mapCondition.get(sqlParam.getName()));
			}
			if ((page - 1) * limit > -1) {
				query.setFirstResult((page - 1) * limit);
			}
			if (limit > 0) {
				query.setMaxResults(limit);
			}

			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public <T> JSONObject searchJson(Class<T> entity, Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if ("statusCode".equalsIgnoreCase(tempKey)) {
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey)[0];
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}

		JSONObject jsonObject = new JSONObject();
		String jpql = strBuffJPQL.toString();
		int records = 0;

		try {
			int idxOrder = jpql.indexOf("ORDER BY");
			if (idxOrder > 0) {
				jpql = jpql.substring(0, idxOrder);
			}
			TypedQuery<T> query = em.createQuery(jpql, entity);
			for (Parameter<?> sqlParam : query.getParameters()) {
				String paramName = sqlParam.getName();
				query.setParameter(paramName, mapCondition.get(paramName));
				paramName = null;
			}
			records = query.getResultList().size();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		int limit = 10;
		if (null != arg0.get("MAX_RESULTS")) {
			limit = Integer.parseInt(arg0.get("MAX_RESULTS")[0]);
		} else if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
		}

		mapCondition.put("MAX_RESULTS", limit);

		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}

		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
		}
		if (page > total) {
			page = total;
		}
		mapCondition.put("FIRST_RESULT", (limit * page - limit) < 0 ? 0 : limit
				* page - limit);

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		List<GenfuResultIdCell> rows = new ArrayList<GenfuResultIdCell>();

		List<T> resultList = searchListPrivate(strBuffJPQL.toString(),
				mapCondition, entity);

		if (resultList.size() > 0) {
			GenfuResultIdCell tempResult;
			for (limit = 0; limit < resultList.size(); limit++) {
				tempResult = new GenfuResultIdCell(limit);
				tempResult.setCell(resultList.get(limit));
				rows.add(tempResult);
			}
		}

		jsonObject.put("rows", rows);

		return jsonObject;
	}

	private <T> List<T> searchListPrivate(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		// EntityManager em = this.emf.createEntityManager();
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
	public <T> JSONObject searchJsonJTable(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey)[0];
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}

		JSONObject jsonObject = new JSONObject();

		int jtStartIndex = 1;
		if (null != arg0.get("jtStartIndex")) {
			jtStartIndex = Integer.parseInt(arg0.get("jtStartIndex")[0]);
		}
		jtStartIndex = jtStartIndex - 1;
		if (jtStartIndex < 0) {
			jtStartIndex = 0;
		}
		mapCondition.put("FIRST_RESULT", jtStartIndex);

		int limit = 10;
		if (null != arg0.get("jtPageSize")) {
			limit = Integer.parseInt(arg0.get("jtPageSize")[0]);
		} else if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
		}

		mapCondition.put("MAX_RESULTS", limit + jtStartIndex);

		// jsonObject.put("page", page);
		// jsonObject.put("total", total);
		// jsonObject.put("records", records);

		jsonObject.put("Result", "OK");
		jsonObject
				.put("Records",
						searchListPrivate(strBuffJPQL.toString(), mapCondition,
								entity));
		jsonObject.put("TotalRecordCount",
				getTotalRecords(strBuffJPQL.toString(), mapCondition, entity));

		return jsonObject;
	}

	@Override
	public <T> Map<String, Object> searchJsonRows(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if ("statusCode".equalsIgnoreCase(tempKey)) {
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey)[0];
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}

		JSONObject jsonObject = new JSONObject();
		int records = getTotalRecords(strBuffJPQL.toString(), mapCondition,
				entity);
		// int records = 0;
		int limit = 10;
		if (null != arg0.get("MAX_RESULTS")) {
			limit = Integer.parseInt(arg0.get("MAX_RESULTS")[0]);
		} else if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
		}

		mapCondition.put("MAX_RESULTS", limit);

		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}

		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
		}
		if (page > total) {
			page = total;
		}
		mapCondition.put("FIRST_RESULT", (limit * page - limit) < 0 ? 0 : limit
				* page - limit);

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		// List<GenfuResultIdCell> rows = new ArrayList<GenfuResultIdCell>();

		List<T> resultList = searchListPrivate(strBuffJPQL.toString(),
				mapCondition, entity);

		// if (resultList.size() > 0) {
		// GenfuResultIdCell tempResult;
		// for (limit = 0; limit < resultList.size(); limit++) {
		// tempResult = new GenfuResultIdCell(limit);
		// tempResult.setCell(resultList.get(limit));
		// rows.add(tempResult);
		// }
		// }

		// jsonObject.put("rows", rows);

		Map<String, Object> tempMap = new Hashtable<String, Object>();
		tempMap.put("jsonObject", jsonObject);
		tempMap.put("rows", resultList);

		return tempMap;
	}

	@Override
	public <T> JSONObject searchJsonJqGrid(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new Hashtable<String, Object>();
		StringBuffer strBuffJPQL = new StringBuffer();
		strBuffJPQL.append("from " + entity.getName() + " WHERE 1=1 ");
		while (it.hasNext()) {
			tempKey = it.next();
			if ("statusCode".equalsIgnoreCase(tempKey)) {
				mapCondition.put(tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
			if (tempKey.toUpperCase().endsWith("_LIKE")) {

				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LIKE"));
				strBuffJPQL
						.append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
				mapCondition.put("_LIKE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_EQ")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_EQ"));
				strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
				mapCondition.put("_EQ" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_GE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_GE"));
				strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
				mapCondition.put("_GE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if (tempKey.toUpperCase().endsWith("_LE")) {
				tempValue = arg0.get(tempKey)[0];
				tempKey = tempKey.toUpperCase().substring(0,
						tempKey.toUpperCase().lastIndexOf("_LE"));
				strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
				mapCondition.put("_LE" + tempKey, tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			} else if ("ORDER_BY".equalsIgnoreCase(tempKey)) {
				tempValue = arg0.get(tempKey)[0];
				strBuffJPQL.append(" ORDER BY " + tempValue);
				// arg0.getParameterMap().remove(tempKey);
				continue;
			}
		}
		Map<String, Object> result = new Hashtable<String, Object>();

		int records = getTotalRecords(strBuffJPQL.toString(), mapCondition,
				entity);

		int limit = 10;
		if (null != arg0.get("MAX_RESULTS")) {
			limit = Integer.parseInt(arg0.get("MAX_RESULTS")[0]);
		} else if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
		}

		mapCondition.put("MAX_RESULTS", limit);

		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}

		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
		}
		if (page > total) {
			page = total;
		}
		mapCondition.put("FIRST_RESULT", (limit * page - limit) < 0 ? 0 : limit
				* page - limit);

		result.put("page", page);
		result.put("total", total);
		result.put("records", records);

		JsonConfig cfg = new JsonConfig();
		// 过滤关联，避免死循环net.sf.json.JSONException:
		// java.lang.reflect.InvocationTargetException
		cfg.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("userCreateDate") || name.equals("userEffDate")
						|| name.equals("userExpDate")
						|| name.equals("userUpdateDate")
						|| name.equals("rememberTokenExpiresAt")) {
					return true;
				} else {
					return false;
				}
			}
		});
		// net.sf.json.JSONException:
		// java.lang.reflect.InvocationTargetException异常
		cfg.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });

		result.put("rows",
				searchListPrivate(strBuffJPQL.toString(), mapCondition, entity));
		JSONObject jsonObject = JSONObject.fromObject(result, cfg);
		return jsonObject;
	}

	@Override
	public <T> Object save(List<T> list) {
		for (Object object : list) {
			em.persist(object);
		}
		return 0;
	}

	@Override
	public <T> Object merge(List<T> list) {
		for (Object object : list) {
			em.merge(object);
		}
		return 0;
	}

	@Override
	public int batchDeleteByNativeQuery(String strSQL) {
		return em.createNativeQuery(strSQL).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonJqGridFilter(Class<T> entity,
			Map<String, String[]> arg0) {
		// [{"groupOp":"AND","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}]}]
		// [{"groupOp":"OR","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}],"groups":[{"groupOp":"AND","rules":[{"field":"dishFlag","op":"eq","data":"5"},{"field":"price","op":"eq","data":"6"}],"groups":[]}]}]
		// ((dishFlag = "5" AND price = "6") OR dishName <> "1" OR publishedAt =
		// "3" OR createdAt = "2" OR publishedId = "3")
		FabricationFilterSQL fabricationFilterSQL = new FabricationFilterSQLImpl();
		Map<String, Object> filterSQL = fabricationFilterSQL.fabricationJPQL(
				entity, arg0);

		// JSONObject jsonObject = new JSONObject();
		int records = getTotalRecords(filterSQL.get("jpql").toString(),
				(Map<String, Object>) filterSQL.get("parameters"), entity);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		// jsonObject.put("page", page);
		// jsonObject.put("total", total);
		// jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}
		Map<String, Object> result = new Hashtable<String, Object>();

		result.put("page", page);
		result.put("total", total);
		result.put("records", records);

		JsonConfig cfg = new JsonConfig();
		// 过滤关联，避免死循环net.sf.json.JSONException:
		// java.lang.reflect.InvocationTargetException
		cfg.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("orderItems") || name.equals("userPassword")) {
					return true;
				} else {
					return false;
				}
			}
		});
		/*
		 * cfg.registerJsonValueProcessor(java.util.Date.class, new
		 * JsonValueProcessor() { public Object processObjectValue(String key,
		 * Object value, JsonConfig jsonConfig) { if ("createdAt".equals(key) &&
		 * value instanceof java.util.Date) { String str = new
		 * SimpleDateFormat("yyyy-MM-dd") .format((java.util.Date) value);
		 * return str; } return null == value ? "" : value.toString(); }
		 * 
		 * @Override public Object processArrayValue(Object value, JsonConfig
		 * arg1) { String[] obj = {}; if (value instanceof Date[]) {
		 * SimpleDateFormat sf = new SimpleDateFormat( "yyyy-MM-dd"); Date[]
		 * dates = (Date[]) value; obj = new String[dates.length]; for (int i =
		 * 0; i < dates.length; i++) { obj[i] = sf.format(dates[i]); } } return
		 * obj; }
		 * 
		 * });
		 */
		// cfg.setJavaPropertyNameProcessorMatcher(null);
		// net.sf.json.JSONException:
		// java.lang.reflect.InvocationTargetException异常
		cfg.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });

		result.put(
				"rows",
				searchListPrivate(filterSQL.get("jpql").toString(),
						(Map<String, Object>) filterSQL.get("parameters"),
						entity, (page - 1) * limit, page * limit));
		JSONObject jsonObject = JSONObject.fromObject(result, cfg);

		return jsonObject;
	}

	private <T> List<T> searchListPrivate(String jpql,
			Map<String, Object> parameters, Class<T> entity, int FIRST_RESULT,
			int MAX_RESULTS) {
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonJqGridFilter(String sql, Class<T> entity,
			Map<String, String[]> arg0) {
		// [{"groupOp":"AND","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}]}]
		// [{"groupOp":"OR","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}],"groups":[{"groupOp":"AND","rules":[{"field":"dishFlag","op":"eq","data":"5"},{"field":"price","op":"eq","data":"6"}],"groups":[]}]}]
		// ((dishFlag = "5" AND price = "6") OR dishName <> "1" OR publishedAt =
		// "3" OR createdAt = "2" OR publishedId = "3")

		FabricationFilterSQL fabricationFilterSQL = new FabricationFilterSQLImpl();
		Map<String, Object> filterSQL = fabricationFilterSQL.fabricationJPQL(
				sql, entity, arg0);

		JSONObject jsonObject = new JSONObject();
		int records = getTotalRecords(filterSQL.get("jpql").toString(),
				(Map<String, Object>) filterSQL.get("parameters"), entity);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		jsonObject.put(
				"rows",
				searchListPrivate(filterSQL.get("jpql").toString(),
						(Map<String, Object>) filterSQL.get("parameters"),
						entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity) {

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
		// return genfuCommonDao.searchNativeQuery(jpql, parameters, entity);
	}

	@Override
	public int batchExcuseNativeQuery(String strSQL) {
		try {
			String[] exec = strSQL.split("#");
			// EntityTransaction entityTransaction = em.getTransaction();
			// entityTransaction.begin();
			int ret = 0;
			for (int i = 0; i < exec.length; i++) {
				ret += em.createNativeQuery(exec[i]).executeUpdate();

				// logger.info("" + ret);
			}
			// entityTransaction.commit();
			// em.clear();
			return ret;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		// return genfuCommonDao.batchExcuseNativeQuery(strSQL);
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
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

	@Override
	public <T> JSONObject searchJsonJqGrid(String sql, Class<T> entity,
			Map<String, String[]> arg0) {
		JSONObject jsonObject = new JSONObject();
		int records = getTotalRecords(sql, null, entity);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		jsonObject.put(
				"rows",
				searchListPrivate(sql, null, entity, (page - 1) * limit, page
						* limit));

		return jsonObject;
	}

	@Override
	public Path getGenfuPath(String arg0) {

		Map<String, Object> parameters = new Hashtable<String, Object>();
		parameters.put("configFlag", "OPEN");
		parameters.put("configKey", arg0);

		List<GenfuConfig> configlist = searchListPrivate(
				"SELECT t FROM GenfuConfig t WHERE t.configFlag = :configFlag AND t.configKey = :configKey",
				parameters, GenfuConfig.class, 0, 0);
		if (configlist.size() > 0) {
			GenfuConfig gc = configlist.get(0);

			return Paths.get(gc.getConfigValue());
		}

		return Paths.get("files");
	}

	@Override
	public String getGenfuConfig(String arg0) {

		Map<String, Object> parameters = new Hashtable<String, Object>();
		parameters.put("configFlag", "OPEN");
		parameters.put("configKey", arg0);

		List<GenfuConfig> configlist = searchListPrivate(
				"SELECT t FROM GenfuConfig t WHERE t.configFlag = :configFlag AND t.configKey = :configKey",
				parameters, GenfuConfig.class, 0, 0);
		if (configlist.size() > 0) {
			GenfuConfig gc = configlist.get(0);
			return gc.getConfigValue();
		}

		return "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonJqGridFilter(String sql,
			Map<String, Object> parameters, Class<T> entity,
			Map<String, String[]> arg0) {
		FabricationFilterSQL fabricationFilterSQL = new FabricationFilterSQLImpl();
		Map<String, Object> filterSQL = fabricationFilterSQL.fabricationJPQL(
				sql, entity, arg0);
		Map<String, Object> myPara;
		if (null != parameters) {
			myPara = parameters;
		} else {
			myPara = new Hashtable<String, Object>();
		}
		if (null != filterSQL.get("parameters")) {
			myPara.putAll((Map<? extends String, ? extends Object>) filterSQL
					.get("parameters"));
		}

		JSONObject jsonObject = new JSONObject();
		int records = getTotalRecords(filterSQL.get("jpql").toString(), myPara,
				entity);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		jsonObject.put(
				"rows",
				searchListPrivate(filterSQL.get("jpql").toString(), myPara,
						entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonNativeQuery(String sql,
			Map<String, Object> parameters, Class<T> entity,
			Map<String, String[]> arg0) {
		FabricationFilterSQL fabricationFilterSQL = new FabricationFilterSQLImpl();
		Map<String, Object> filterSQL = fabricationFilterSQL
				.fabricationNativeSQL(sql, entity, arg0);
		Map<String, Object> myPara;
		if (null != parameters) {
			myPara = parameters;
		} else {
			myPara = new Hashtable<String, Object>();
		}
		if (null != filterSQL.get("parameters")) {
			myPara.putAll((Map<? extends String, ? extends Object>) filterSQL
					.get("parameters"));
		}

		JSONObject jsonObject = new JSONObject();
		int records = searchNativeQueryRecords(
				filterSQL.get("jpql").toString(), myPara, entity);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		jsonObject.put(
				"rows",
				searchNativeQuery(filterSQL.get("jpql").toString(), myPara,
						entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity, int FIRST_RESULT,
			int MAX_RESULTS) {
		// EntityManager em = this.emf.createEntityManager();
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
			// logger.info("searchNativeQuery");
			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public <T> Object saveUpdate(Object object, List<T> list) {
		try {
			// EntityTransaction entityTransaction = em.getTransaction();
			// entityTransaction.begin();
			em.persist(object);

			for (Object mObject : list) {
				em.merge(mObject);
			}
			// entityTransaction.commit();
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
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, int FIRST_RESULT, int MAX_RESULTS) {
		return searchNativeQuery(jpql, parameters, FIRST_RESULT, MAX_RESULTS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonNativeQuery(String jpql,
			Map<String, Object> parameters, Map<String, String[]> arg0) {
		FabricationFilterSQL fabricationFilterSQL = new FabricationFilterSQLImpl();
		Map<String, Object> filterSQL = fabricationFilterSQL
				.fabricationNativeSQL(jpql, null, arg0);
		Map<String, Object> myPara;
		if (null != parameters) {
			myPara = parameters;
		} else {
			myPara = new Hashtable<String, Object>();
		}
		if (null != filterSQL.get("parameters")) {
			myPara.putAll((Map<? extends String, ? extends Object>) filterSQL
					.get("parameters"));
		}

		JSONObject jsonObject = new JSONObject();
		int records = searchNativeQueryRecords(
				filterSQL.get("jpql").toString(), myPara);

		int limit = 20;
		if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
			if (limit < 0) {
				limit = 20;
			} else if (limit > 65535) {
				limit = 65535;
			}
		}
		// page count
		int total = 0;
		if (records > 0) {
			total = (int) Math.ceil((records + 0.0) / limit);
		}
		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
			if (page < 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
		}

		jsonObject.put("page", page);
		jsonObject.put("total", total);
		jsonObject.put("records", records);

		if (arg0.containsKey("NO_PAGE")) {
			// if pager
			limit = 0;
		}

		jsonObject.put(
				"rows",
				searchNativeQuery(filterSQL.get("jpql").toString(), myPara,
						(page - 1) * limit, page * limit));

		return jsonObject;
	}

	private <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters) {
		// EntityManager em = this.emf.createEntityManager();
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
		return 0;
	}
}
