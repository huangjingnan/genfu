package com.genfu.reform.ejb;

public interface Account {
	public void deposit(int amount);

	public void withdraw(int amount);

	public void sayHelloFromAccountBean();
}
