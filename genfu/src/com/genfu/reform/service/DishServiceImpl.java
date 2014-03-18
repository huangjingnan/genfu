package com.genfu.reform.service;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.genfu.reform.jpa.GenfuCommonDAO;
import com.genfu.reform.model.GenfuConfig;
import com.genfu.reform.model.GenfuResultIdCell;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.util.AuthValid;
import com.genfu.reform.util.FabricationFilterSQL;

public class DishServiceImpl implements GenfuCommonService {
	// private StringBuffer strBuffJPQL = new StringBuffer();
	// private Map<String, Object> mapCondition = new HashMap<String, Object>();
	// Iterator<String> it = null;
	private GenfuCommonDAO genfuCommonDAO;
	private GenfuCommonDAO genfuCommonTransaction;
	private FabricationFilterSQL fabricationFilterSQL;
	private AuthValid authValidImpl;

	public GenfuCommonDAO getGenfuCommonTransaction() {
		return genfuCommonTransaction;
	}

	public void setGenfuCommonTransaction(GenfuCommonDAO genfuCommonTransaction) {
		this.genfuCommonTransaction = genfuCommonTransaction;
	}

	public GenfuCommonDAO getGenfuCommonDAO() {
		return genfuCommonDAO;
	}

	public void setGenfuCommonDAO(GenfuCommonDAO genfuCommonDAO) {
		this.genfuCommonDAO = genfuCommonDAO;
	}

	public FabricationFilterSQL getFabricationFilterSQL() {
		return fabricationFilterSQL;
	}

	public void setFabricationFilterSQL(
			FabricationFilterSQL fabricationFilterSQL) {
		this.fabricationFilterSQL = fabricationFilterSQL;
	}

	public AuthValid getAuthValidImpl() {
		return authValidImpl;
	}

	public void setAuthValidImpl(AuthValid authValidImpl) {
		this.authValidImpl = authValidImpl;
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

		return genfuCommonDAO.searchList(jpql, parameters, entity);
	}

	@Override
	public int save(Object object) {
		genfuCommonDAO.save(object);
		return 0;
	}

	@Override
	public int remove(long id, String tableName) {
		genfuCommonDAO.remove(id, tableName);
		return 0;
	}

	@Override
	public int remove(Object object) {
		genfuCommonDAO.remove(object);
		return 0;
	}

	@Override
	public <T> Object remove(List<T> list) {
		return genfuCommonTransaction.remove(list);
	}

	@Override
	public int update(Object object) {
		genfuCommonDAO.merge(object);
		return 0;
	}

	@Override
	public <T> Object update(List<T> list) {

		return genfuCommonDAO.merge(list);
	}

	@Override
	public Object find(long id) {
		return genfuCommonDAO.find(id);
	}

	@Override
	public <T> Object find(Long id, Class<T> entity) {
		return genfuCommonDAO.find(id, entity);
	}

	@Override
	public <T> int getTotalRecords(String jpql, Map<String, Object> parameters,
			Class<T> entity) {

		// strBuffJPQL.delete(0, strBuffJPQL.length());
		// mapCondition.clear();
		//
		// strBuffJPQL.append(jpql);
		// String tempKey = "statusCode";
		// Object tempValue = "303";
		// it = arg0.getParameterMap().keySet().iterator();
		// while (arg0.getParameterMap().keySet().iterator().hasNext()) {
		// tempKey = it.next();
		// if ("statusCode".equalsIgnoreCase(tempKey)) {
		// mapCondition.put(tempKey, tempValue);
		// continue;
		// }
		// if (tempKey.toUpperCase().endsWith("_LIKE")) {
		//
		// tempValue = arg0.getParameter(tempKey);
		// tempKey = tempKey.toUpperCase().substring(0,
		// tempKey.toUpperCase().lastIndexOf("_LIKE"));
		// strBuffJPQL
		// .append(" AND " + tempKey + " LIKE :_LIKE" + tempKey);
		// mapCondition.put("_LIKE" + tempKey, tempValue);
		// continue;
		// } else if (tempKey.toUpperCase().endsWith("_EQ")) {
		// tempValue = arg0.getParameter(tempKey);
		// tempKey = tempKey.toUpperCase().substring(0,
		// tempKey.toUpperCase().lastIndexOf("_EQ"));
		// strBuffJPQL.append(" AND " + tempKey + " = :_EQ" + tempKey);
		// mapCondition.put("_EQ" + tempKey, tempValue);
		// continue;
		// } else if (tempKey.toUpperCase().endsWith("_GE")) {
		// tempValue = arg0.getParameter(tempKey);
		// tempKey = tempKey.toUpperCase().substring(0,
		// tempKey.toUpperCase().lastIndexOf("_GE"));
		// strBuffJPQL.append(" AND " + tempKey + " >= :_GE" + tempKey);
		// mapCondition.put("_GE" + tempKey, tempValue);
		// continue;
		// } else if (tempKey.toUpperCase().endsWith("_LE")) {
		// tempValue = arg0.getParameter(tempKey);
		// tempKey = tempKey.toUpperCase().substring(0,
		// tempKey.toUpperCase().lastIndexOf("_LE"));
		// strBuffJPQL.append(" AND " + tempKey + " <= :_LE" + tempKey);
		// mapCondition.put("_LE" + tempKey, tempValue);
		// continue;
		// } else if ("page".equalsIgnoreCase(tempKey)) {
		// tempValue = arg0.getParameter(tempKey);
		// tempValue = (Integer.parseInt(tempValue.toString()) - 1)
		// * Integer.parseInt(arg0.getParameter("MAX_RESULTS"));
		// mapCondition.put("FIRST_RESULT", tempValue.toString());
		// continue;
		// } else if ("MAX_RESULTS".equalsIgnoreCase(tempKey)) {
		// tempValue = arg0.getParameter(tempKey);
		// mapCondition.put(tempKey, tempValue);
		// }
		// }

		// arg0.get
		// arg0.setAttribute("totalRecords",
		// genfuCommonDAO.getTotalRecords(jpql, parameters, entity));
		// System.out.println("getTotalRecords...setServletRequest..."
		// + arg0.getParameter("totalRecords"));
		return genfuCommonDAO.getTotalRecords(jpql, parameters, entity);
	}

	@Override
	public <T> Map<String, Object> fabricationSQL(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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

		Map<String, Object> returnFabricationSQL = new HashMap<String, Object>();
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
		return genfuCommonDAO.batchDelete(sqlStringBuffer.toString());
		// return genfuCommonDAO.batchDelete(strSQL);
	}

	@Override
	public <T> Object findModel(Long id, Class<T> entity) {
		return genfuCommonDAO.findModel(id, entity);
	}

	@Override
	public int batchDeleteJPQL(String className, String strSQL) {
		StringBuffer sqlStringBuffer = new StringBuffer();
		sqlStringBuffer.append("from " + className + " WHERE id IN (" + strSQL
				+ ")");
		Class<?> theClass = null;
		try {
			theClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return genfuCommonTransaction.batchDeleteJPQL(
				sqlStringBuffer.toString(), theClass);
	}

	@Override
	public <T> List<T> searchList(Class<T> entity, Map<String, String[]> arg0) {

		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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

		int limit = 10;
		if (null != arg0.get("MAX_RESULTS")) {
			limit = Integer.parseInt(arg0.get("MAX_RESULTS")[0]);
		} else if (null != arg0.get("rows")) {
			limit = Integer.parseInt(arg0.get("rows")[0]);
		}

		mapCondition.put("MAX_RESULTS", limit);

		// int total = 0;
		// if (records > 0) {
		// total = (int) Math.ceil((records + 0.0) / limit);
		// }

		int page = 1;
		if (null != arg0.get("page")) {
			page = Integer.parseInt(arg0.get("page")[0]);
		}
		// if (page > total) {
		// page = total;
		// }
		mapCondition.put("FIRST_RESULT", (limit * page - limit) < 0 ? 0 : limit
				* page - limit);

		return genfuCommonDAO.searchList(strBuffJPQL.toString(), mapCondition,
				entity);
	}

	@Override
	public <T> JSONObject searchJson(Class<T> entity, Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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
		int records = genfuCommonDAO.getTotalRecords(strBuffJPQL.toString(),
				mapCondition, entity);

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

		List<T> resultList = genfuCommonDAO.searchList(strBuffJPQL.toString(),
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

	@Override
	public <T> JSONObject searchJsonJTable(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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
		jsonObject.put("Records", genfuCommonDAO.searchList(
				strBuffJPQL.toString(), mapCondition, entity));
		jsonObject.put("TotalRecordCount", genfuCommonDAO.getTotalRecords(
				strBuffJPQL.toString(), mapCondition, entity));

		return jsonObject;
	}

	@Override
	public <T> Map<String, Object> searchJsonRows(Class<T> entity,
			Map<String, String[]> arg0) {
		Iterator<String> it = arg0.keySet().iterator();
		String tempKey = "statusCode";
		Object tempValue = "303";
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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
		int records = genfuCommonDAO.getTotalRecords(strBuffJPQL.toString(),
				mapCondition, entity);

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

		List<T> resultList = genfuCommonDAO.searchList(strBuffJPQL.toString(),
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
		Map<String, Object> mapCondition = new HashMap<String, Object>();
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
		int records = genfuCommonDAO.getTotalRecords(strBuffJPQL.toString(),
				mapCondition, entity);

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

		jsonObject.put("rows", genfuCommonDAO.searchList(
				strBuffJPQL.toString(), mapCondition, entity));

		return jsonObject;
	}

	@Override
	public <T> Object save(List<T> list) {
		return genfuCommonTransaction.save(list);
	}

	@Override
	public <T> Object merge(List<T> list) {
		return genfuCommonTransaction.merge(list);
	}

	@Override
	public int batchDeleteByNativeQuery(String strSQL) {
		return genfuCommonTransaction.batchDelete(strSQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonJqGridFilter(Class<T> entity,
			Map<String, String[]> arg0) {
		// [{"groupOp":"AND","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}]}]
		// [{"groupOp":"OR","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}],"groups":[{"groupOp":"AND","rules":[{"field":"dishFlag","op":"eq","data":"5"},{"field":"price","op":"eq","data":"6"}],"groups":[]}]}]
		// ((dishFlag = "5" AND price = "6") OR dishName <> "1" OR publishedAt =
		// "3" OR createdAt = "2" OR publishedId = "3")

		Map<String, Object> filterSQL = fabricationFilterSQL.fabricationJPQL(
				entity, arg0);

		JSONObject jsonObject = new JSONObject();
		int records = genfuCommonDAO.getTotalRecords(filterSQL.get("jpql")
				.toString(), (Map<String, Object>) filterSQL.get("parameters"),
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

		jsonObject.put("rows", genfuCommonDAO.searchList(filterSQL.get("jpql")
				.toString(), (Map<String, Object>) filterSQL.get("parameters"),
				entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonJqGridFilter(String sql, Class<T> entity,
			Map<String, String[]> arg0) {
		// [{"groupOp":"AND","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}]}]
		// [{"groupOp":"OR","rules":[{"field":"dishName","op":"ne","data":"1"},{"field":"publishedAt","op":"eq","data":"3"},{"field":"createdAt","op":"eq","data":"2"},{"field":"publishedId","op":"eq","data":"3"}],"groups":[{"groupOp":"AND","rules":[{"field":"dishFlag","op":"eq","data":"5"},{"field":"price","op":"eq","data":"6"}],"groups":[]}]}]
		// ((dishFlag = "5" AND price = "6") OR dishName <> "1" OR publishedAt =
		// "3" OR createdAt = "2" OR publishedId = "3")

		Map<String, Object> filterSQL = fabricationFilterSQL.fabricationJPQL(
				sql, entity, arg0);

		JSONObject jsonObject = new JSONObject();
		int records = genfuCommonDAO.getTotalRecords(filterSQL.get("jpql")
				.toString(), (Map<String, Object>) filterSQL.get("parameters"),
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

		jsonObject.put("rows", genfuCommonDAO.searchList(filterSQL.get("jpql")
				.toString(), (Map<String, Object>) filterSQL.get("parameters"),
				entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@Override
	public <T> List<T> searchNativeQuery(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		return genfuCommonDAO.searchNativeQuery(jpql, parameters, entity);
	}

	@Override
	public int batchExcuseNativeQuery(String strSQL) {
		return genfuCommonTransaction.batchExcuseNativeQuery(strSQL);
	}

	@Override
	public <T> int searchNativeQueryRecords(String jpql,
			Map<String, Object> parameters, Class<T> entity) {
		return genfuCommonDAO
				.searchNativeQueryRecords(jpql, parameters, entity);
	}

	@Override
	public <T> JSONObject searchJsonJqGrid(String sql, Class<T> entity,
			Map<String, String[]> arg0) {
		JSONObject jsonObject = new JSONObject();
		int records = genfuCommonDAO.getTotalRecords(sql, null, entity);

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

		jsonObject.put("rows", genfuCommonDAO.searchList(sql, null, entity,
				(page - 1) * limit, page * limit));

		return jsonObject;
	}

	@Override
	public Path getGenfuPath(String arg0) {

		Map<String, Object> parameters = new Hashtable<String, Object>();
		parameters.put("configFlag", "OPEN");
		parameters.put("configKey", arg0);

		List<GenfuConfig> configlist = genfuCommonDAO
				.searchList(
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

		List<GenfuConfig> configlist = genfuCommonDAO
				.searchList(
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
		int records = genfuCommonDAO.getTotalRecords(filterSQL.get("jpql")
				.toString(), myPara, entity);

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

		jsonObject.put("rows", genfuCommonDAO.searchList(filterSQL.get("jpql")
				.toString(), myPara, entity, (page - 1) * limit, page * limit));

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> JSONObject searchJsonNativeQuery(String sql,
			Map<String, Object> parameters, Class<T> entity,
			Map<String, String[]> arg0) {
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
		int records = genfuCommonDAO.searchNativeQueryRecords(
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

		jsonObject.put("rows", genfuCommonDAO.searchNativeQuery(
				filterSQL.get("jpql").toString(), myPara, entity, (page - 1)
						* limit, page * limit));

		return jsonObject;
	}

	@Override
	public <T> JSONObject validateAndRecord(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session) {

		return authValidImpl.validateAndRecord(actionName, operate, request,
				entity, session);
	}

	@Override
	public <T> void recordOperates(String actionName, String operate,
			HttpServletRequest request, Class<T> entity,
			Map<String, Object> session) {
		authValidImpl.recordOperates(actionName, operate, request, entity,
				session);
	}

	@Override
	public JSONObject authentication(String actionName,
			HttpServletRequest request, UserInfo userInfo,
			Map<String, Object> session) {
		return authValidImpl.authentication(actionName, request, userInfo,
				session);
	}

	@Override
	public <T> JSONObject validateOperates(String userCode, String Passwd,
			String actionName, String operate, Map<String, Object> agr0,
			Class<T> entity, Map<String, String[]> parameters,
			Map<String, Object> session) {
		return authValidImpl.validateOperates(userCode, Passwd, actionName,
				operate, agr0, entity, parameters, session);
	}

	@Override
	public boolean verifyingOperates(Map<String, String[]> arg0,
			Map<String, Object> session) {
		return authValidImpl.verifyingOperates(arg0, session);
	}

	@Override
	public <T> Object saveUpdate(Object object, List<T> list) {
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
	public <T> JSONObject searchJsonNativeQuery(String jpql,
			Map<String, Object> parameters, Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
