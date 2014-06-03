package com.genfu.reform.util;

public class TestAny {

	public static void main(String[] args) {

		try {
			String strAnyS = "WebContent#4544.zip";//5gUhZ1KF8vlr4Nn+sXgV7W04GPXziP0c
			//WebContent#1234.zip

			DES fileNameDES = new DES("huge-stream");

			strAnyS = fileNameDES.getEncString(strAnyS);
			System.out.println(strAnyS);
			strAnyS = fileNameDES.getDesString(strAnyS);
			System.out.println(strAnyS);

			System.out.println(strAnyS.indexOf("#"));
			System.out.println(strAnyS.indexOf("."));
			System.out.println(strAnyS.substring(0, 10));
			System.out.println(strAnyS.substring(11, strAnyS.indexOf(".")));//size
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e");
		}

	}

}
