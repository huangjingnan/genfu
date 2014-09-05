package com.genfu.reform.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.genfu.reform.jpa.GenfuCommonDAO;

public class TestAny {

	public static void main(String[] args) throws Exception {

		// GenfuDES des = new GenfuDES("87654323");
		// String passwd = "abcd1234!@#$qwer";
		//
		// byte[] ciphertext = des.encrypt(passwd);
		//
		// for (int i = 0; i < ciphertext.length; i++) {
		// System.out.print(ciphertext[i] & 0x0f);
		// // System.out.println(((ciphertext[i] & 0xf0) >> 4));
		// // System.out.println(ciphertext[i] & 0x0f);
		// }
		// des = null;
		// System.out.println("\n");
		//
		// GenfuDES des2 = new GenfuDES("87654323");
		// if (des2.ifCorrect(ciphertext, passwd)) {
		// System.out.println("ok");
		// } else {
		// System.out.println("error");
		// }

		// byte[] arr = "1234567G".getBytes();
		//
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println(arr[i]);
		// System.out.println(((arr[i] & 0xf0) >> 4));
		// System.out.println(arr[i] & 0x0f);
		// }
		//
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ctx.getApplicationName();
		
		GenfuCommonDAO test = (GenfuCommonDAO) ctx.getBean("orderDAOImpl");
		List<Long> longOrderIds = new ArrayList<Long>();
		longOrderIds.add(30L);
		Map<String, Object> tempPara = new Hashtable<String, Object>();
		tempPara.put("orderIds", longOrderIds);
		test.excuseNativeQuery("", tempPara);

		// System.out.println("1234".substring(0, 3));

		// DES theDES = new DES("password");
		//
		// String enc = theDES
		// .getEncString("111111");
		// System.out.println(enc);
		// enc = theDES.getDesString(enc);
		// System.out.println(enc);
		// try {
		// String strAnyS = "WebContent#40838318.zip";
		// // 5gUhZ1KF8vlr4Nn+sXgV7W04GPXziP0c
		// // WebContent#41155265.zip
		//
		// DES fileNameDES = new DES("www1genfucom");
		//
		// strAnyS = fileNameDES.getEncString(strAnyS);
		// System.out.println(strAnyS);
		// strAnyS = fileNameDES.getDesString(strAnyS);
		// System.out.println(strAnyS);
		//
		// System.out.println(strAnyS.indexOf("#"));
		// System.out.println(strAnyS.indexOf("."));
		// System.out.println(strAnyS.substring(0, 10));
		// System.out.println(strAnyS.substring(11, strAnyS.indexOf(".")));
		// // size
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("e");
		// }

		// Object val = "2014-05-01 00:00:00";
		//
		// //java.util.Date tempDate = (java.util.Date) val;
		//
		// //System.out.println(tempDate.toString());
		//
		// Date today = null;
		// SimpleDateFormat formatter;
		//
		// formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// try {
		// today = formatter.parse((String)val);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("Result: " + today.toString());

		// String input = "2014-05-01";
		// try {
		// DateTimeFormatter formatter =
		// DateTimeFormatter.ofPattern("MMM d yyyy");
		// LocalDate date = LocalDate.parse(input, formatter);
		// System.out.printf("%s%n", date);
		// }
		// catch (DateTimeParseException exc) {
		// System.out.printf("%s is not parsable!%n", input);
		// throw exc; // Rethrow the exception.
		// }
		// 'date' has been successfully parsed

		// /^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\/(\d{1}|[0-2]{1}\d{1}|3[0-2])$/

		// Pattern ipPat = Pattern
		// .compile("^(^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5]))\\s-(\\s-)$");
		//
		// if (ipPat.matcher("255.0.0.0-255.0.0.0--").matches()) {
		// System.out.println("ok");
		// } else {
		// System.out.println("ko");
		// }

	}

}
