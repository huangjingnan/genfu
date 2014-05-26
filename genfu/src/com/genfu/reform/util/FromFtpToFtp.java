package com.genfu.reform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FromFtpToFtp {
	public static final String USAGE = "Usage: FromFtpToFtp source target localDir account sourceUser sourcePasswd targetUser targetPasswd\n";

	public static void main(String[] args) {

		// target source localDir account sourcePasswd targetPasswd
		if (args.length != 8) {

			System.err.println(USAGE);
			System.exit(1);
		}
		// System.exit(1);
		String serverFrom = "192.168.56.109";
		serverFrom = args[0];

		String serverTo = "192.168.56.109";
		serverTo = args[1];

		FTPClient ftpFrom = new FTPClient();

		FTPClient ftpTo = new FTPClient();

		// 连接到服务器
		try {
			ftpFrom.connect(serverFrom);
			ftpTo.connect(serverTo);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (ftpFrom.isConnected()) {
				try {
					ftpFrom.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
			if (ftpTo.isConnected()) {
				try {
					ftpTo.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
		}

		class DirectoryNode {
			private String directoryName;
			private String parentDirectoryName;

			public DirectoryNode(String name, String parentName) {
				directoryName = name;
				parentDirectoryName = parentName;
			}

			public String getDirectoryName() {
				return directoryName;
			}

			public void setDirectoryName(String directoryName) {
				this.directoryName = directoryName;
			}

			public String getParentDirectoryName() {
				return parentDirectoryName;
			}

			public void setParentDirectoryName(String parentDirectoryName) {
				this.parentDirectoryName = parentDirectoryName;
			}
		}
		List<DirectoryNode> dirList = new ArrayList<DirectoryNode>();
		String remoteRootFolder = "xuzhenfu";
		remoteRootFolder = args[3];
		String localRootFolder = args[2];
		dirList.add(new DirectoryNode(remoteRootFolder, ""));

		// 登录
		try {
			ftpFrom.login(args[4], args[5]);
			ftpTo.login(args[6], args[7]);

			Set<DirectoryNode> dirListTemp = new HashSet<DirectoryNode>();
			// 设置连接类型二进制
			ftpFrom.setFileType(FTP.BINARY_FILE_TYPE);
			ftpTo.setFileType(FTP.BINARY_FILE_TYPE);

			DirectoryNode d;
			int len = 0;
			while (len < dirList.size()) {

				d = dirList.get(len);
				len++;

				remoteRootFolder = d.parentDirectoryName + File.separator
						+ d.directoryName;

				localRootFolder = localRootFolder + File.separator
						+ d.directoryName;

				// 切换到登录用户名所在目录
				for (FTPFile f : ftpFrom.listFiles(remoteRootFolder)) {
					System.out.println(f.getRawListing());
					System.out.println(f.toFormattedString());
					File file = new File(localRootFolder);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
						ftpTo.makeDirectory(remoteRootFolder);
					}
					if (f.isFile()) {
						// 下载文件
						OutputStream output;
						output = new FileOutputStream(localRootFolder
								+ File.separator + f.getName());
						ftpFrom.retrieveFile(remoteRootFolder + File.separator
								+ f.getName(), output);
						output.close();

						InputStream input;
						input = new FileInputStream(localRootFolder
								+ File.separator + f.getName());
						ftpTo.storeFile(
								remoteRootFolder + File.separator + f.getName(),
								input);
						input.close();
					} else if (f.isDirectory()) {

						dirListTemp.add(new DirectoryNode(f.getName(),
								remoteRootFolder));
					}

				}

				if (dirListTemp.size() > 0 && len >= dirList.size()) {
					dirList.clear();
					dirList.addAll(dirListTemp);
					dirListTemp.clear();
					len = 0;
				}

			}
			ftpFrom.noop(); // check that control connection is working OK
			// ftpTo.noop(); // check that control connection is working OK

			ftpFrom.logout();
			ftpTo.logout();
			// 删除本地文件夹及文件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ftpFrom.isConnected()) {
				try {
					ftpFrom.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
			if (ftpTo.isConnected()) {
				try {
					ftpTo.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
		}

	}
}
