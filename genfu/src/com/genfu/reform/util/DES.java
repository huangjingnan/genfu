package com.genfu.reform.util;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES {

	private Cipher cipher;
	private SecretKey secretKey;
	private SecureRandom secureRandom;

	public DES() {
		setKey("0123456709ABCDEF");// 生成密匙
	}

	public DES(String str) {
		int len = 0;
		if (null != str) {
			len = 8 - str.length();
			if (len >= 0) {
				for (int i = 0; i < len; i++) {
					str = str + "0";
				}

			} else {
				len = 8 - str.length() % 8;
				for (int i = 0; i < len; i++) {
					str = str + "0";
				}
			}
			setKey(str);// 生成密匙
		} else {
			// str = "0123456789ABCDEF";
			setKey("0123456709ABCDEF");// 生成密匙
		}
		// setKey(str);// 生成密匙
	}

	/**
	 * 根据参数生成KEY
	 */
	public void setKey(String strKey) {
		try {

			secureRandom = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(strKey.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			secretKey = keyFactory.generateSecret(dks);

			// KeyGenerator generator = KeyGenerator.getInstance("DES");
			// generator.init(new SecureRandom(strKey.getBytes("UTF8")));
			// this.key = generator.generateKey();
			// generator = null;
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 */
	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes();
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");

		} catch (IOException e) {
			e.printStackTrace();
		}
		// throw new RuntimeException(
		// "Error initializing SqlMap class. Cause: " + e);
		// base64De = null;
		// byteMing = null;
		// byteMi = null;
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;

		try {

			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {

		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

//	public static void main(String args[]) {
//		DES des = new DES("1234567890ABCDEF");
//		// 设置密钥
//		// DES des = new DES();
//		// des.setKey("1234567890ABCDEF");
//
//		String str1 = "我的";
//		// DES加密
//		String str2 = des.getEncString(str1);
//		String deStr = "";
//		try {
//			deStr = des.getDesString(str2);
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
//		System.out.println("密文:" + str2);
//		// DES解密
//		System.out.println("明文:" + deStr);
//	}

}
