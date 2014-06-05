package com.genfu.agent.mxbeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Pattern;

public class ConfigNetwork implements ConfigNetworkMXBean {

	// extends NotificationBroadcasterSupport
	// private String address = "192.168.0.222";
	// private String netmask = "255.255.255.0";
	// private String gateway = "192.168.0.1";

	public ConfigNetwork() {
		super();
		// address = "192.168.0.222";
		// netmask = "255.255.255.0";
		// gateway = "192.168.0.1";
	}

	@Override
	public ConfigResult cfgDebian(String address, String netmask, String gateway)
			throws IOException {
		synchronized (this) {

			// this.executeCommand(
			// "cp /etc/network/interfaces /etc/network/interfaces_bak",
			// "UTF-8");
			Pattern pattern = Pattern
					.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

			if (pattern.matcher(address).matches()
					&& pattern.matcher(netmask).matches()
					&& pattern.matcher(gateway).matches()) {
				// 如果 0.0.0.0 代表DHCP
				FileOutputStream out = null;
				try {
					// inp = new
					// FileInputStream("C:\\Users\\xuzhenfu\\Downloads\\interfaces.txt");

					// 修改前，变更数据库状态，禁止再上传升级包

					out = new FileOutputStream("/etc/network/interfaces");

					StringBuffer context = new StringBuffer();
					context.append("# This file describes the network interfaces available on your system\r\n");
					context.append("# and how to activate them. For more information, see interfaces(5).\r\n");
					context.append("# The loopback network interface\r\n");
					context.append("auto lo\r\n");
					context.append("iface lo inet loopback\r\n");
					context.append("# The primary network interface\r\n");
					context.append("#allow-hotplug eth0\r\n");
					context.append("#iface eth0 inet dhcp\r\n");
					context.append("auto eth0\r\n");
					context.append("iface eth0 inet static\r\n");
					context.append("address ").append(address).append("\r\n");
					context.append("netmask ").append(netmask).append("\r\n");
					context.append("gateway ").append(gateway).append("\r\n");

					out.write(context.toString().getBytes());

					this.executeCommand("/etc/init.d/networking restart",
							"UTF-8");
					// inp.read();
					// inp.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// if (in != null) {
					// in.close();
					// }
					if (out != null) {
						out.close();
					}
				}

			}

			return new ConfigResult(new Date(), 0, "");
		}
	}

	private String executeCommand(String command, String charsetName) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream(), charsetName));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

	// public MBeanNotificationInfo[] getNotificationInfo() {
	// return new MBeanNotificationInfo[] {
	// new MBeanNotificationInfo(
	// new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE },
	// AttributeChangeNotification.class.getName(),
	// "This notification is emitted when the reset() method is called.")
	// };
	// }
}
