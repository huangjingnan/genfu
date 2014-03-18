package com.genfu.reform.ejb;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
@Remote({ com.genfu.reform.ejb.Account.class })
@Interceptors({ com.genfu.reform.ejb.AuditInterceptor.class })
public class AccountBean implements Account {

	private int balance = 0;

	@EJB(beanName = "ServiceBean")
	private Service service;

	public void deposit(int amount) {
		balance += amount;
		System.out.println("deposited: " + amount + " balance: " + balance);
	}

	public void withdraw(int amount) {
		balance -= amount;
		System.out.println("withdrew: " + amount + " balance: " + balance);
	}

	@ExcludeClassInterceptors
	public void sayHelloFromAccountBean() {
		service.sayHelloFromServiceBean();
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Invoking method: preDestroy()");
	}

}
