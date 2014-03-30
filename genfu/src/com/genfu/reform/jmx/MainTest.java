package com.genfu.reform.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MainTest {
	public static void main(String[] args) throws Exception {

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("com.example:type=Hello");
		ServerMonitor mbean = new ServerMonitor();
		mbs.registerMBean(mbean, name);

		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
