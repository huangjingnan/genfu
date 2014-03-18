package com.genfu.reform.util;

import java.util.Map;

public interface FabricationFilterSQL {

	public <T> Map<String, Object> fabricationJPQL(Class<T> entity,
			Map<String, String[]> arg0);

	public <T> Map<String, Object> fabricationJPQL(String sql, Class<T> entity,
			Map<String, String[]> arg0);

	public <T> Map<String, Object> fabricationNativeSQL(String sql, Class<T> entity,
			Map<String, String[]> arg0);
}
