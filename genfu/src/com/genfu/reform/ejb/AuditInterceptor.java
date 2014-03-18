package com.genfu.reform.ejb;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AuditInterceptor {
	public AuditInterceptor() {}

	  @AroundInvoke
	  public Object audit(InvocationContext ic) throws Exception {
	    System.out.println("Invoking method: "+ic.getMethod());
	    return ic.proceed();
	  }

	  @PostActivate
	  public void postActivate(InvocationContext ic) {
	    System.out.println("Invoking method: "+ic.getMethod());
	  }

	  @PrePassivate
	  public void prePassivate(InvocationContext ic) {
	    System.out.println("Invoking method: "+ic.getMethod());
	  }

}
