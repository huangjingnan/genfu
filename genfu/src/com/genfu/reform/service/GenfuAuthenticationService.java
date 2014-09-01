package com.genfu.reform.service;

public interface GenfuAuthenticationService {

	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId);
}
