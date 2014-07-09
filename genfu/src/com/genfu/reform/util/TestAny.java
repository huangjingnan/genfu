package com.genfu.reform.util;

import java.util.regex.Pattern;

public class TestAny {

	public static void main(String[] args) {

		try {
			String strAnyS = "WebContent#39458930.zip";
			// 5gUhZ1KF8vlr4Nn+sXgV7W04GPXziP0c
			// WebContent#1234.zip

			DES fileNameDES = new DES("stream-huge");

			strAnyS = fileNameDES.getEncString(strAnyS);
			System.out.println(strAnyS);
			strAnyS = fileNameDES.getDesString(strAnyS);
			System.out.println(strAnyS);

			System.out.println(strAnyS.indexOf("#"));
			System.out.println(strAnyS.indexOf("."));
			System.out.println(strAnyS.substring(0, 10));
			System.out.println(strAnyS.substring(11, strAnyS.indexOf(".")));
			// size
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e");
		}

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
