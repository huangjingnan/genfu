package com.genfu.reform.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.genfu.reform.model.UserInfo;

public interface GenfuCommonService {

	public <T> List<T> findAll();

	public <T> List<T> findAll(Map<String, Object> mapCondition);

	public <T> List<T> searchList(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity);

	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, int FIRST_RESULT, int MAX_RESULTS);

	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity);

	public <T> List<T> searchList(Class<T> entity, Map<String, String[]> arg0);

	public <T> JSONObject searchJson(Class<T> entity, Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJTable(Class<T> entity,
			Map<String, String[]> arg0);

	public int save(Object object);

	public int remove(long id, String tableName);

	public int remove(Object object);

	public <T> Object remove(List<T> list);

	public <T> Object save(List<T> list);

	public <T> Object merge(List<T> list);

	public int update(Object object);

	public <T> Object update(List<T> list);

	public <T> Object saveUpdate(Object object, List<T> list);

	public Object find(long id);

	public <T> Object find(Long id, Class<T> entity);

	public <T> Object findModel(Long id, Class<T> entity);

	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity);

	public <T> Map<String, Object> fabricationSQL(Class<T> entity,
			Map<String, String[]> arg0);

	public int batchDelete(String className, String strSQL);

	public int batchDeleteJPQL(String className, String strSQL);

	public int batchDeleteByNativeQuery(String strSQL);

	public int batchExcuseNativeQuery(String strSQLSplt);

	public int excuseNativeQuery(String strSQLSplt, Map<String, Object> agr0);

	public boolean verifyingOperates(Map<String, String[]> arg0,
			Map<String, Object> session);

	public <T> Map<String, Object> searchJsonRows(Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJqGrid(Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJqGrid(String sql, Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJqGridFilter(Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJqGridFilter(String sql, Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonJqGridFilter(String sql,
			Map<String, Object> parameters, Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity,
			Map<String, String[]> arg0);

	public <T> JSONObject searchJsonNativeQuery(String jpql,
			Map<String, Object> parameters, Map<String, String[]> arg0);

	public <T> JSONObject validateAndRecord(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session);

	public <T> void recordOperates(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session);

	public <T> JSONObject validateOperates(String userCode, String Passwd,
			String actionName, String operate, Map<String, Object> agr0,
			Class<T> entity, Map<String, String[]> parameters,
			Map<String, Object> session);

	public Path getGenfuPath(String arg0);

	public String getGenfuConfig(String arg0);

	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session);
}
