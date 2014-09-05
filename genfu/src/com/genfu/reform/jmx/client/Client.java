package com.genfu.reform.jmx.client;

import java.util.Hashtable;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.genfu.reform.jmx.mxbeans.ConfigNetworkMXBean;

public class Client {

	public static void main(String[] args) {
		try {
			// Environment map
			//
			System.out.println("\nInitialize the environment map");
			Hashtable env = new Hashtable();

			// Provide the credentials required by the server to successfully
			// perform user authentication
			//
			String[] credentials = new String[] { "username", "password" };
			env.put("jmx.remote.credentials", credentials);

			// Create an RMI connector client and
			// connect it to the RMI connector server
			//
			System.out.println("\nCreate an RMI connector client and "
					+ "connect it to the RMI connector server");
			JMXServiceURL url = new JMXServiceURL(
					"service:jmx:rmi:///jndi/rmi://192.168.56.101:9999/server");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, env);

			// Get an MBeanServerConnection
			//
			System.out.println("\nGet an MBeanServerConnection");
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			// Get domains from MBeanServer
			//
			System.out.println("\nDomains:");
			String domains[] = mbsc.getDomains();
			for (int i = 0; i < domains.length; i++) {
				System.out.println("\tDomain[" + i + "] = " + domains[i]);
			}

			// Create ConfigNetwork MBean
			//
			ObjectName mbeanName = new ObjectName(
					"com.genfu.agent.mxbeans:type=ConfigNetwork");
			System.out.println("\nCreate ConfigNetwork MBean...");
			mbsc.createMBean("com.genfu.agent.mxbeans.ConfigNetwork",
					mbeanName, null, null);

			// Get MBean count
			//
			System.out.println("\nMBean count = " + mbsc.getMBeanCount());

			// Get State attribute
			//
			// System.out.println("\nState = " +
			// mbsc.getAttribute(mbeanName, "State"));

			// Set State attribute
			//
			// mbsc.setAttribute(mbeanName,
			// new Attribute("State", "changed state"));

			// Get State attribute
			//
			// Another way of interacting with a given MBean is through a
			// dedicated proxy instead of going directly through the MBean
			// server connection
			//
			ConfigNetworkMXBean proxy = JMX.newMBeanProxy(mbsc, mbeanName,
					ConfigNetworkMXBean.class);
			// System.out.println("\nState = " + proxy.getState());

			// Add notification listener on ConfigNetwork MBean
			//
			// ClientListener listener = new ClientListener();
			// System.out.println("\nAdd notification listener...");
			// mbsc.addNotificationListener(mbeanName, listener, null, null);

			// Invoke "reset" in ConfigNetwork MBean
			//
			// Calling "reset" makes the ConfigNetwork MBean emit a
			// notification that will be received by the registered
			// ClientListener.
			//
			Object[] params = new Object[] { "192.168.56.102", "255.255.255.0",
					"192.168.56.1" };
			System.out
					.println("\nInvoke cfgDebian() in ConfigNetwork MBean...");
			mbsc.invoke(mbeanName, "cfgDebian", params, new String[] {
					String.class.getName(), String.class.getName(),
					String.class.getName() });

			// Sleep for 2 seconds in order to have time to receive the
			// notification before removing the notification listener.
			//
			System.out.println("\nWaiting for notification...");
			Thread.sleep(2000);

			// Remove notification listener on ConfigNetwork MBean
			//
			// System.out.println("\nRemove notification listener...");
			// mbsc.removeNotificationListener(mbeanName, listener);

			// Unregister ConfigNetwork MBean
			//
			System.out.println("\nUnregister ConfigNetwork MBean...");
			mbsc.unregisterMBean(mbeanName);

			// Close MBeanServer connection
			//
			System.out.println("\nClose the connection to the server");
			jmxc.close();
			System.out.println("\nBye! Bye!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
