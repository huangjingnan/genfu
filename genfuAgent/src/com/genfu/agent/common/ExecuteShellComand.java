package com.genfu.agent.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShellComand {

	public static void main(String[] args) {
		ExecuteShellComand obj = new ExecuteShellComand();
		try {
			obj.editInterfaces(
					"C:\\Users\\xuzhenfu\\Downloads\\interfaces.txt", "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String domainName = "172.16.74.84";

		// in mac oxs
		String command = "ping " + domainName;

		// in windows
		// String command = "ping -n 3 " + domainName;

		String output = obj.executeCommand(command, "GBK");

		System.out.println(output);

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

	private String editInterfaces(String path, String command)
			throws IOException {

		// FileInputStream inp = null;
		FileOutputStream out = null;
		try {
			// inp = new
			// FileInputStream("C:\\Users\\xuzhenfu\\Downloads\\interfaces.txt");
			out = new FileOutputStream(path);

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
			context.append("address 172.16.74.84\r\n");
			context.append("netmask 255.255.255.0\r\n");
			context.append("gateway 172.16.74.2\r\n");

			out.write(context.toString().getBytes());

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

		return "0";

	}
}
