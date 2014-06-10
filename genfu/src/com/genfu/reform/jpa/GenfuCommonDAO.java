package com.genfu.reform.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

public interface GenfuCommonDAO {
	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity);

	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity, int FIRST_RESULT,
			int MAX_RESULTS);

	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, int FIRST_RESULT, int MAX_RESULTS);

	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity);

	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters);

	public <T> Object find(Long id, Class<T> entity);

	public int save(Object model);

	public int merge(Object model);

	public int remove(Object model);

	public int remove(long id, String tableName);

	public <T> Object save(List<T> list);

	public <T> Object remove(List<T> list);

	public <T> Object merge(List<T> list);

	public <T> Object saveMerge(Object persistObject, List<T> mergeList);

	public Object find(long id);

	public <T> T findModel(Long id, Class<T> entity);

	public int batchDelete(String strSQL);

	public <T> int batchDeleteJPQL(String strSQL, Class<T> entity);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity, int FIRST_RESULT, int MAX_RESULTS);

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode, Map<String, Object> properties);

	public <T> T find(Class<T> entityClass, Object primaryKey,
			LockModeType lockMode);

	public int batchExcuseNativeQuery(String strSQL);
	
	public int excuseNativeQuery(String strSQLSplt, Map<String, Object> agr0);

	/**
	 * Find by primary key, using the specified properties. Search for an entity
	 * of the specified class and primary key. If the entity instance is
	 * contained in the persistence context it is returned from there. If a
	 * vendor-specific property or hint is not recognized, it is silently
	 * ignored.
	 * 
	 * @param entityClass
	 * @param primaryKey
	 * @param properties
	 *            standard and vendor-specific properties and hints
	 * @return the found entity instance or null if the entity does not exist
	 * @throws IllegalArgumentException
	 *             if the first argument does not denote an entity type or the
	 *             second argument is is not a valid type for that entity’s
	 *             primary key or is null
	 */
	public <T> T find(Class<T> entityClass, Object primaryKey,
			Map<String, Object> properties);
}
