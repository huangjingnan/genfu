package com.genfu.reform.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeDefaultInterceptors;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@ExcludeDefaultInterceptors

public class ServiceBean implements Service {

	@Override
	public void sayHelloFromServiceBean() {
		System.out.println("Hello From Service Bean!");
	}

}
