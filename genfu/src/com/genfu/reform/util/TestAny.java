package com.genfu.reform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestAny {

	public static void main(String[] args) {

//		try {
//			String strAnyS = "WebContent#41155293.zip";
//			// 5gUhZ1KF8vlr4Nn+sXgV7W04GPXziP0c
//			// WebContent#41155265.zip
//
//			DES fileNameDES = new DES("www1genfucom");
//
//			strAnyS = fileNameDES.getEncString(strAnyS);
//			System.out.println(strAnyS);
//			strAnyS = fileNameDES.getDesString(strAnyS);
//			System.out.println(strAnyS);
//
//			System.out.println(strAnyS.indexOf("#"));
//			System.out.println(strAnyS.indexOf("."));
//			System.out.println(strAnyS.substring(0, 10));
//			System.out.println(strAnyS.substring(11, strAnyS.indexOf(".")));
//			// size
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("e");
//		}

		
		Object val = "2014-05-01 00:00:00";
		
		//java.util.Date tempDate = (java.util.Date) val;
		
		//System.out.println(tempDate.toString());
		
		Date today = null;
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			today = formatter.parse((String)val);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Result: " + today.toString());

//		String input = "2014-05-01";
//		try {
//		    DateTimeFormatter formatter =
//		                      DateTimeFormatter.ofPattern("MMM d yyyy");
//		    LocalDate date = LocalDate.parse(input, formatter);
//		    System.out.printf("%s%n", date);
//		}
//		catch (DateTimeParseException exc) {
//		    System.out.printf("%s is not parsable!%n", input);
//		    throw exc;      // Rethrow the exception.
//		}
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
