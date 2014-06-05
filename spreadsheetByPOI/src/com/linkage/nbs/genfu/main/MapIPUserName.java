package com.linkage.nbs.genfu.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class MapIPUserName {

	public static void main(String[] args) {
		// host database user password table <file.xls> [file.xls]
		__main: try {

			if (args.length < 6) {
				System.out
						.print("Usage: host database user password table <file.xls> [file.xls]\n");
				break __main;
			}
			int fileCount = 5;

			// The newInstance() call is a work around for some
			// broken Java implementations
			Connection conn = null;
			// assume that conn is an already created JDBC connection (see
			// previous examples)
			Statement stmt = null;
			ResultSet rs = null;
			Map<String, String> ipNameMap = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://" + args[0]
						+ "/" + args[1] + "?" + "user=" + args[2]
						+ "&password=" + args[3]);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT resource_ip,resource_name from "
						+ args[4]);
				// or alternatively, if you don't know ahead of time that
				// the query will be a SELECT...
				// if (stmt.execute("SELECT foo FROM bar")) {
				// rs = stmt.getResultSet();
				// }
				// Now do something with the ResultSet ....

				if (rs.next()) {
					ipNameMap = new HashMap<String, String>();
					do {
						ipNameMap.put(rs.getString("RESOURCE_IP"),
								rs.getString("RESOURCE_NAME"));
					} while (rs.next());
				}

				rs.close();
				rs = null;
				stmt.close();
				stmt = null;
				conn.close();
				conn = null;
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				break __main;
			} finally {
				// it is a good idea to release
				// resources in a finally{} block
				// in reverse-order of their creation
				// if they are no-longer needed
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {
					} // ignore
					rs = null;
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException sqlEx) {
					} // ignore
					stmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException sqlEx) {
					} // ignore
					conn = null;
				}
			}

			if (null != ipNameMap && ipNameMap.size() > 0) {

				InputStream inp = null;
				Workbook wb = null;
				Sheet sheet = null;
				Pattern pattern = Pattern
						.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
				Iterator<Row> rit = null;
				Row row = null;
				Cell cell = null;
				String sourceIP = null;
				FileOutputStream fileOut = null;
				do {

					inp = new FileInputStream(args[fileCount]);
					// InputStream inp = new FileInputStream("workbook.xlsx");
					// "C:\\Users\\xuzf\\Downloads\\ftp_session_20130910_20130911104250131\\ftp_session_20130910_20130911104250131\\workbook1-1000.xls"
					wb = WorkbookFactory.create(inp);
					sheet = wb.getSheetAt(0);

					for (rit = sheet.rowIterator(); rit.hasNext();) {
						row = rit.next();
						cell = row.getCell(4);
						sourceIP = cell.getStringCellValue();

						cell = row.getCell(10);
						if (cell == null)
							cell = row.createCell(10);
						cell.setCellType(Cell.CELL_TYPE_STRING);

						if (pattern.matcher(sourceIP).matches()) {
							cell.setCellValue(ipNameMap.get(sourceIP));
						} else if ("源IP".equals(sourceIP)) {
							cell.setCellValue("源IP对应的用户");
						} else {
							cell.setCellValue("IP有错误");
						}
						// for (Iterator<Cell> cit = row.cellIterator();
						// cit.hasNext();)
						// {
						// Cell cell = cit.next();
						// // do something here
						// }
					}

					// Write the output to a file
					fileOut = new FileOutputStream(args[fileCount]);
					wb.write(fileOut);
					fileOut.close();
					fileCount++;
				} while (fileCount < args.length);
			}

		} catch (FileNotFoundException e) {
			System.err.print("在给定的路径下，没有找到对应的文件，或者文件正被另一程序占用。");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.print("系统异常。");
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			System.err.print("文件格式不合法（.xlsx）");
			e.printStackTrace();
		}
	}

}
