/**
 * Main.java - main class for QueueSampler example. Create the Queue Sampler
 * MXBean, register it, then wait forever (or until the program is interrupted).
 */

package com.genfu.agent.mxbeans;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {
	/*
	 * For simplicity, we declare "throws Exception". Real programs will usually
	 * want finer-grained exception handling.
	 */
	public static void main(String[] args) throws Exception {
		// Get the Platform MBean Server
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		// Construct the ObjectName for the MBean we will register
		// ObjectName name =
		// new ObjectName("com.genfu.agent.mxbeans:type=QueueSampler");

		ObjectName name = new ObjectName(
				"com.genfu.agent.mxbeans:type=ConfigNetwork");

		// Create the Queue Sampler MXBean
		// Queue<String> queue = new ArrayBlockingQueue<String>(10);
		// queue.add("Request-1");
		// queue.add("Request-2");
		// queue.add("Request-3");
		// QueueSampler mxbean = new QueueSampler(queue);

		ConfigNetwork mxbean = new ConfigNetwork();

		// Register the Queue Sampler MXBean
		mbs.registerMBean(mxbean, name);

		// Wait forever
		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
