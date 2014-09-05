package com.genfu.reform.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.genfu.reform.model.CartItem;
import com.genfu.reform.model.Dish;
import com.genfu.reform.model.GenfuCommonResult;

public class TestJsonObject {
	public static String OPERANDS = "{\"eq\":\"=\",\"ne\":\"<>\",\"lt\":\"<\",\"le\":\"<=\",\"gt\":\">\",\"ge\":\">=\",\"bw\":\"LIKE\",\"bn\":\"NOT LIKE\",\"in\":\"IN\",\"ni\":\"NOT IN\",\"ew\":\"LIKE\",\"en\":\"NOT LIKE\",\"cn\":\"LIKE\",\"nc\":\"NOT LIKE\",\"nu\":\"IS NULL\",\"nn\":\"IS NOT NULL\"}";
	public static JSONObject JSON_OPERANDS = JSONObject.fromObject(OPERANDS);

	public static void main(String[] args) {
		List<Object> rows = new ArrayList<Object>();
		// Map<Object, Object> row = new Hashtable<Object, Object>();
		GenfuCommonResult rowTemp = new GenfuCommonResult(1, 2, 13);
		Dish myDish = new Dish(1, "1", "1", 1, null, "1", null, null, null,
				"1", "1");

		JSONObject jsonObject1 = JSONObject.fromObject(myDish);
		System.out.println(jsonObject1.toString());
		String[] test = new String[] { "1", "2", null };

		rowTemp.setModel(test);
		rows.add(rowTemp);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("page", new Integer(1));
		jsonObject.put("total", new Integer(2));
		jsonObject.put("records", new Integer(13));
		jsonObject.put("rows", rows);

		System.out.println(1 / 10);
		System.out.println(11 / 10);

		System.out.println(jsonObject.toString());

		CartItem ci = new CartItem(1, 1, 1, new BigDecimal(2.01), 1, null, null);
		BigDecimal total = new BigDecimal(0.0);
		System.out.print(new BigDecimal(ci.getAmount()).multiply(ci.getPrice())
				.doubleValue());
		BigDecimal total2 = new BigDecimal(ci.getAmount()).multiply(ci
				.getPrice());
		total = total.add(total2);

		String[] testString = new String[] { "1", "2", "3" };
		System.out.println(testString.toString());

		String myFilter = "{\"groupOp\":\"OR\",\"rules\":[{\"field\":\"dishName\",\"op\":\"ne\",\"data\":\"1\"},{\"field\":\"publishedAt\",\"op\":\"bw\",\"data\":\"3\"},{\"field\":\"createdAt\",\"op\":\"eq\",\"data\":\"2\"},{\"field\":\"publishedId\",\"op\":\"eq\",\"data\":\"3\"}],\"groups\":[{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"dishFlag\",\"op\":\"eq\",\"data\":\"5\"},{\"field\":\"price\",\"op\":\"eq\",\"data\":\"6\"}],\"groups\":[]}]}";

		JSONObject jsonObjectFilter = JSONObject.fromObject(myFilter);

		// System.out.println(jsonObjectFilter.getJSONArray("a").size());
		// System.out.println(jsonObjectFilter.getJSONArray("a").getJSONObject(0)
		// .get("groupOp"));
		// System.out.println(jsonObjectFilter.getJSONArray("a").getJSONObject(0)
		// .get("groups").toString());

		System.out.println(getStringForGroup(jsonObjectFilter));

		String testStr = "taggings#name";

		System.out.println(testStr.substring(0, testStr.indexOf("#")));
		System.out.println(testStr);

	}

	public static String getStringForGroup(JSONObject group) {
		StringBuffer s = new StringBuffer();
		s.append("(");
		int index = 0;

		JSONArray groups = group.getJSONArray("groups");
		JSONArray rules = group.getJSONArray("rules");

		if (null != groups && groups.size() > 0) {
			int glen = groups.size();
			for (index = 0; index < glen; index++) {
				if (s.length() > 1) {
					s.append(" " + group.getString("groupOp") + " ");
				}
				try {
					s.append(getStringForGroup(groups.getJSONObject(index)));
				} catch (EJBException ex) {
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
					s.append(getStringForRule(rules.getJSONObject(index)));
				}
			} catch (EJBException ex) {
				ex.printStackTrace();
			}
		}

		s.append(")");

		if ("()".equals(s.toString())) {
			return ""; // ignore groups that don't have rules
		}
		return s.toString();
	}

	public static String getStringForRule(JSONObject rule) {
		String opUF = "", opC = "", ret = "", cm = "";

		Object val = null;
		opC = rule.getString("op");
		opUF = JSON_OPERANDS.getString(opC);
		val = rule.get("data");
		cm = rule.getString("field");
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
			val = " (" + val + ")";
		}

		ret = cm + " " + opUF + " \"" + val + "\"";

		return ret;

		// operands : { "eq" :"=",
		// "ne":"<>","lt":"<","le":"<=","gt":">","ge":">=","bw":"LIKE","bn":"NOT LIKE","in":"IN","ni":"NOT IN","ew":"LIKE","en":"NOT LIKE","cn":"LIKE","nc":"NOT LIKE","nu":"IS NULL","nn":"ISNOT NULL"}
	}
}
