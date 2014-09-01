package com.genfu.reform.service;

import com.genfu.reform.jpa.GenfuAuthenticationImpl;

public class GenfuAuthenTicationServiceImpl implements
		GenfuAuthenticationService {

	private GenfuAuthenticationImpl genfuAuthenticationImpl;

	public GenfuAuthenticationImpl getGenfuAuthenticationImpl() {
		return genfuAuthenticationImpl;
	}

	public void setGenfuAuthenticationImpl(
			GenfuAuthenticationImpl genfuAuthenticationImpl) {
		this.genfuAuthenticationImpl = genfuAuthenticationImpl;
	}

	@Override
	public boolean verify(String actionName, String nameSpace, String method,
			String operate, long userId) {
		return genfuAuthenticationImpl.verify(actionName, nameSpace, method,
				operate, userId);
	}

}
