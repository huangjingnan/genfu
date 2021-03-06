package com.genfu.reform.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DishFilterSQLImpl implements FabricationFilterSQL, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String OPERANDS = "{\"eq\":\"=\",\"ne\":\"<>\",\"lt\":\"<\",\"le\":\"<=\",\"gt\":\">\",\"ge\":\">=\",\"bw\":\"LIKE\",\"bn\":\"NOT LIKE\",\"in\":\"IN\",\"ni\":\"NOT IN\",\"ew\":\"LIKE\",\"en\":\"NOT LIKE\",\"cn\":\"LIKE\",\"nc\":\"NOT LIKE\",\"nu\":\"IS NULL\",\"nn\":\"IS NOT NULL\"}";
	public static JSONObject JSON_OPERANDS = JSONObject.fromObject(OPERANDS);

	private Map<String, Object> mapParameters = new Hashtable<String, Object>();
	private StringBuffer strJpql = null;
	private StringBuffer subStrSQL = null;
	private int parametersIndex = 0;

	@Override
	public <T> Map<String, Object> fabricationJPQL(String sql, Class<T> entity,
			Map<String, String[]> arg0) {
		mapParameters.clear();
		parametersIndex = 0;
		strJpql = new StringBuffer();
		strJpql.append(sql);
		Map<String, Object> mapJPQL = new Hashtable<String, Object>();

		if (null != arg0.get("filters") && arg0.get("filters")[0].length() > 0) {

			JSONObject jsonObject = JSONObject
					.fromObject(arg0.get("filters")[0]);

			// if (!arg0.containsKey("NO_PAGE")) {
			// int jtStartIndex = 1;
			// if (null != arg0.get("page")) {
			// jtStartIndex = Integer.parseInt(arg0.get("page")[0]);
			// }
			// jtStartIndex = jtStartIndex - 1;
			// if (jtStartIndex < 0) {
			// jtStartIndex = 0;
			// }
			//
			// int limit = 20;
			// if (null != arg0.get("rows")) {
			// limit = Integer.parseInt(arg0.get("rows")[0]);
			// }
			// jtStartIndex = jtStartIndex * limit;
			// mapParameters.put("FIRST_RESULT", jtStartIndex);
			// mapParameters.put("MAX_RESULTS", limit + jtStartIndex);
			// }

			String sJPQL = getStringForGroup(entity, jsonObject);
			if (sJPQL.length() > 0) {
				strJpql.append(" AND ");
				strJpql.append(sJPQL);

				mapJPQL.put("parameters", mapParameters);
			}
		}
		if (null != arg0.get("sidx") && arg0.get("sidx")[0].length() > 0) {
			strJpql.append(" ORDER BY x.");
			strJpql.append(arg0.get("sidx")[0]);
			if (null != arg0.get("sord") && arg0.get("sord")[0].length() > 0) {
				strJpql.append(" ");
				strJpql.append(arg0.get("sord")[0]);
			}
		}
		mapJPQL.put("jpql", strJpql.toString());

		return mapJPQL;
	}

	@Override
	public <T> Map<String, Object> fabricationJPQL(Class<T> entity,
			Map<String, String[]> arg0) {
		mapParameters.clear();
		parametersIndex = 0;
		strJpql = new StringBuffer();
		strJpql.append("SELECT x FROM ");
		strJpql.append(entity.getName());
		strJpql.append(" x");
		Map<String, Object> mapJPQL = new Hashtable<String, Object>();

		if (null != arg0.get("filters") && arg0.get("filters")[0].length() > 0) {
			JSONObject jsonObject = JSONObject
					.fromObject(arg0.get("filters")[0]);

			// if (!arg0.containsKey("NO_PAGE")) {
			// int jtStartIndex = 1;
			// if (null != arg0.get("page")) {
			// jtStartIndex = Integer.parseInt(arg0.get("page")[0]);
			// }
			// jtStartIndex = jtStartIndex - 1;
			// if (jtStartIndex < 0) {
			// jtStartIndex = 0;
			// }
			//
			// int limit = 20;
			// if (null != arg0.get("rows")) {
			// limit = Integer.parseInt(arg0.get("rows")[0]);
			// }
			// jtStartIndex = jtStartIndex * limit;
			// mapParameters.put("FIRST_RESULT", jtStartIndex);
			// mapParameters.put("MAX_RESULTS", limit + jtStartIndex);
			// }

			String sJPQL = getStringForGroup(entity, jsonObject);
			if (sJPQL.length() > 0) {
				strJpql.append(" WHERE ");
				strJpql.append(sJPQL);
				mapJPQL.put("parameters", mapParameters);
			}
		}

		if (null != arg0.get("sidx") && arg0.get("sidx")[0].length() > 0) {
			strJpql.append(" ORDER BY x.");
			strJpql.append(arg0.get("sidx")[0]);
			if (null != arg0.get("sord") && arg0.get("sord")[0].length() > 0) {
				strJpql.append(" ");
				strJpql.append(arg0.get("sord")[0]);
			}
		}
		mapJPQL.put("jpql", strJpql.toString());
		return mapJPQL;
	}

	public <T> String getStringForGroup(Class<T> entity, JSONObject group) {
		StringBuffer s = new StringBuffer();
		s.append("(");
		int index = 0;

		JSONArray groups = null;
		if (group.containsKey("groups")) {
			groups = group.getJSONArray("groups");
		}
		JSONArray rules = group.getJSONArray("rules");

		if (null != groups && groups.size() > 0) {
			int glen = groups.size();
			for (index = 0; index < glen; index++) {
				if (s.length() > 1) {
					s.append(" " + group.getString("groupOp") + " ");
				}
				try {
					s.append(getStringForGroup(entity,
							groups.getJSONObject(index)));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		if (null != rules && rules.size() > 0) {
			int rlen = rules.size();
			try {
				for (index = 0; index < rlen; index++) {
					if (s.length() > 1) {
						s.append(" " + group.getString("groupOp") + " ");
					}
					s.append(getStringForRule(entity,
							rules.getJSONObject(index)));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		s.append(")");

		if ("()".equals(s.toString())) {
			mapParameters.clear();
			return "";
		}
		return s.toString();
	}

	public <T> String getStringForRule(Class<T> entity, JSONObject rule) {
		String opUF = "", opC = "", ret = "", cm = "", typeName = "";
		Object val = null;
		opC = rule.getString("op");
		opUF = JSON_OPERANDS.getString(opC);
		val = rule.get("data");
		cm = rule.getString("field");

		// if ("taggings".equalsIgnoreCase(cm)) {
		// if (subStrSQL.length() > 0) {
		// subStrSQL.append("");
		// } else {
		// subStrSQL.append("");
		// }
		// }

		try {
			Field fd = entity.getDeclaredField(cm);
			// fd.getType().cast(val);
			typeName = fd.getType().getName();
			if ("long".equals(typeName)) {
				if ("in".equals(opC) || "ni".equals(opC)) {

				} else {
					val = Long.parseLong((String) val);
				}
			} else if ("java.math.BigDecimal".equals(typeName)) {
				val = new java.math.BigDecimal((String) val);
			} else if ("java.lang.String".equals(typeName)) {
				// val = (String) val;
			} else if ("java.util.Date".equals(typeName)) {
				// val = (String) val;
			}
		} catch (NoSuchFieldException e) {
			val = 0;
			e.printStackTrace();
		}

		if ("bw".equals(opC) || "bn".equals(opC)) {
			val = val + "%";
		}
		if ("ew".equals(opC) || "en".equals(opC)) {
			val = "%" + val;
		}
		if ("cn".equals(opC) || "nc".equals(opC)) {
			val = "%" + val + "%";
		}
		if ("in".equals(opC) || "ni".equals(opC)) {
			// val = " (" + val + ")";
			// begin = " (";
			// end = ")";

			String inVal = (String) val;
			if (inVal.indexOf("ï¼") > 0 || inVal.indexOf("，") > 0) {

				if (inVal.indexOf("ï¼") > 0) {
					// this should be done in browser
					inVal = inVal.replaceAll("ï¼", ",");
				}
				if (inVal.indexOf("，") > 0) {
					// this should be done in browser
					inVal = inVal.replaceAll("，", ",");
				}

			}

			String[] oval;
			oval = inVal.split(",");
			String arg = "";
			for (int i = 0; i < oval.length; i++) {
				arg = arg + ",:" + cm + parametersIndex;
				if ("long".equals(typeName)) {
					try {
						mapParameters.put(cm + parametersIndex,
								Long.parseLong(oval[i]));
					} catch (NumberFormatException e) {
						mapParameters.put(cm + parametersIndex, 0);
						e.printStackTrace();
					}
				} else {
					mapParameters.put(cm + parametersIndex, oval[i]);
				}
				parametersIndex++;
			}

			ret = " x." + cm + " " + opUF + " (" + arg.substring(1) + ")";
			// mapParameters.put(cm + parametersIndex, val);
		}

		if (ret.length() < 1) {
			ret = " x." + cm + " " + opUF + " :" + cm + parametersIndex;
			mapParameters.put(cm + parametersIndex, val);
			parametersIndex++;
		}
		return ret;
	}

	public Map<String, Object> getMapParameters() {
		return mapParameters;
	}

	public void setMapParameters(Map<String, Object> mapParameters) {
		this.mapParameters = mapParameters;
	}

	public <T> String getStringNativeForGroup(Class<T> entity, JSONObject group) {
		StringBuffer s = new StringBuffer();
		s.append("(");
		int index = 0;

		JSONArray groups = null;
		if (group.containsKey("groups")) {
			groups = group.getJSONArray("groups");
		}
		JSONArray rules = group.getJSONArray("rules");

		if (null != groups && groups.size() > 0) {
			int glen = groups.size();
			for (index = 0; index < glen; index++) {
				if (s.length() > 1) {
					s.append(" " + group.getString("groupOp") + " ");
				}
				try {
					s.append(getStringNativeForGroup(entity,
							groups.getJSONObject(index)));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		if (null != rules && rules.size() > 0) {
			int rlen = rules.size();
			try {
				for (index = 0; index < rlen; index++) {
					if (s.length() > 1) {
						s.append(" " + group.getString("groupOp") + " ");
					}
					s.append(getStringNativeForRule(entity,
							rules.getJSONObject(index),
							group.getString("groupOp")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		s.append(")");

		if ("()".equals(s.toString())) {
			mapParameters.clear();
			return "";
		}
		return s.toString();
	}

	public <T> String getStringNativeForRule(Class<T> entity, JSONObject rule,
			String group) {
		String opUF = "", opC = "", ret = "", cm = "", typeName = "";
		Object val = null;
		opC = rule.getString("op");
		opUF = JSON_OPERANDS.getString(opC);
		val = rule.get("data");
		cm = rule.getString("field");

		if ("taggings".equalsIgnoreCase(cm)) {
			cm = "TAG_NAME";
		} else {
			try {
				Field fd = entity.getDeclaredField(cm);
				// fd.getType().cast(val);
				typeName = fd.getType().getName();
				if ("long".equals(typeName)) {
					if ("in".equals(opC) || "ni".equals(opC)) {

					} else {
						val = Long.parseLong((String) val);
					}
				} else if ("java.math.BigDecimal".equals(typeName)) {
					val = new java.math.BigDecimal((String) val);
				} else if ("java.lang.String".equals(typeName)) {
					// val = (String) val;
				} else if ("java.util.Date".equals(typeName)) {
					// val = (String) val;
				}
				javax.persistence.Column myColumn = fd
						.getAnnotation(javax.persistence.Column.class);

				cm = myColumn.name();

			} catch (NumberFormatException e) {
				val = 0;
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if ("bw".equals(opC) || "bn".equals(opC)) {
			val = val + "%";
		}
		if ("ew".equals(opC) || "en".equals(opC)) {
			val = "%" + val;
		}
		if ("cn".equals(opC) || "nc".equals(opC)) {
			val = "%" + val + "%";
		}
		if ("in".equals(opC) || "ni".equals(opC)) {
			// val = " (" + val + ")";
			// begin = " (";
			// end = ")";

			String inVal = (String) val;
			if (inVal.indexOf("ï¼") > 0 || inVal.indexOf("，") > 0) {

				if (inVal.indexOf("ï¼") > 0) {
					// this should be done in browser
					inVal = inVal.replaceAll("ï¼", ",");
				}
				if (inVal.indexOf("，") > 0) {
					// this should be done in browser
					inVal = inVal.replaceAll("，", ",");
				}

			}
			String[] oval;
			oval = inVal.split(",");
			String arg = "";
			for (int i = 0; i < oval.length; i++) {
				arg = arg + ",:" + cm + parametersIndex;
				if ("long".equals(typeName)) {
					try {
						mapParameters.put(cm + parametersIndex,
								Long.parseLong(oval[i]));
					} catch (NumberFormatException e) {
						mapParameters.put(cm + parametersIndex, 0);
						e.printStackTrace();
					}
				} else {
					mapParameters.put(cm + parametersIndex, oval[i]);
				}
				parametersIndex++;
			}
			if ("TAG_NAME".equalsIgnoreCase(cm)) {
				ret = " Z." + cm + " " + opUF + " (" + arg.substring(1) + ")";
			} else {
				ret = " X." + cm + " " + opUF + " (" + arg.substring(1) + ")";
			}
			// mapParameters.put(cm + parametersIndex, val);
		}

		if (ret.length() < 1) {
			if ("TAG_NAME".equalsIgnoreCase(cm)) {
				ret = " Z." + cm + " " + opUF + " :" + cm + parametersIndex;
				if (subStrSQL.length() > 0) {
					subStrSQL.append(" ").append(group).append(ret);
				} else {
					subStrSQL
							.append("SELECT Y.TAGGABLE_ID FROM TAGGINGS Y WHERE Y.TAG_ID IN (SELECT Z.TAG_ID FROM TAGS Z WHERE");
					subStrSQL.append(ret);
				}
				ret = " 1 = 1";
			} else {
				ret = " X." + cm + " " + opUF + " :" + cm + parametersIndex;
			}
			mapParameters.put(cm + parametersIndex, val);
			parametersIndex++;
		} else {
			if ("TAG_NAME".equalsIgnoreCase(cm)) {
				// ret = " Z." + cm + " " + opUF + " :" + cm + parametersIndex;
				if (subStrSQL.length() > 0) {
					subStrSQL.append(" ").append(group).append(ret);
				} else {
					subStrSQL
							.append("SELECT Y.TAGGABLE_ID FROM TAGGINGS Y WHERE Y.TAG_ID IN (SELECT Z.TAG_ID FROM TAGS Z WHERE");
					subStrSQL.append(ret);
				}
				ret = " 1 = 1";
			}
		}

		// if ("TAG_NAME".equalsIgnoreCase(cm)) {
		// if (ret.length() < 1) {
		// ret = " Z." + cm + " " + opUF + " :" + cm + parametersIndex;
		// mapParameters.put(cm + parametersIndex, val);
		// parametersIndex++;
		// }
		// if (subStrSQL.length() > 0) {
		// subStrSQL.append(" ").append(group).append(ret);
		// } else {
		// subStrSQL
		// .append("SELECT Y.TAGGABLE_ID FROM TAGGINGS Y WHERE Y.TAG_ID IN (SELECT Z.TAG_ID FROM TAGS Z WHERE");
		// subStrSQL.append(ret);
		// }
		// } else {
		// if (ret.length() < 1) {
		// ret = " X." + cm + " " + opUF + " :" + cm + parametersIndex;
		// mapParameters.put(cm + parametersIndex, val);
		// parametersIndex++;
		// }
		// }
		return ret;
	}

	@Override
	public <T> Map<String, Object> fabricationNativeSQL(String sql,
			Class<T> entity, Map<String, String[]> arg0) {
		mapParameters.clear();
		parametersIndex = 0;
		strJpql = new StringBuffer();
		subStrSQL = new StringBuffer();
		strJpql.append(sql);
		Map<String, Object> mapJPQL = new Hashtable<String, Object>();

		if (null != arg0.get("filters") && arg0.get("filters")[0].length() > 0) {

			JSONObject jsonObject = JSONObject
					.fromObject(arg0.get("filters")[0]);

			String sJPQL = getStringNativeForGroup(entity, jsonObject);
			if (sJPQL.length() > 0) {
				strJpql.append(" AND ");
				strJpql.append(sJPQL);

				mapJPQL.put("parameters", mapParameters);
			}
			if (subStrSQL.length() > 0) {
				strJpql.append(" AND X.DISH_ID IN (");
				strJpql.append(subStrSQL);
				strJpql.append(") )");
			}
		}
		if (null != arg0.get("sidx") && arg0.get("sidx")[0].length() > 0) {
			strJpql.append(" ORDER BY X.");

			try {
				Field fd = entity.getDeclaredField(arg0.get("sidx")[0]);
				javax.persistence.Column myColumn = fd
						.getAnnotation(javax.persistence.Column.class);

				strJpql.append(myColumn.name());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (null != arg0.get("sord") && arg0.get("sord")[0].length() > 0) {
				strJpql.append(" ");
				strJpql.append(arg0.get("sord")[0]);
			}
		}
		mapJPQL.put("jpql", strJpql.toString());

		return mapJPQL;
	}

}
