package com.genfu.reform.util;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
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
		String temp = "";
		for (int i = 0; i < 56; i++) {
			if (i % 7 == 0) {
				temp = temp + keyString[55 - i];
			} else {
				temp = temp + keyString[i];
			}
		}
		setKey(temp);// 生成密匙
	}

	public DES(String str) {
		int len = 0;
		if (null != str) {
			len = 56 - str.length();
			if (len >= 0) {
				for (int i = 0; i < len; i++) {
					if (i % 5 == 0) {
						str = str + keyString[55 - i];
					} else {
						str = str + keyString[i];
					}
				}

			} else {
				// len = str.length() % 56;
				String temp = "";
				for (int i = 0; i < 56; i++) {
					if (i % 3 == 0) {
						temp = temp + keyString[55 - i];
					} else {
						temp = temp + str.charAt(i);
					}
				}
				str = temp;
			}
			setKey(str);// 生成密匙
		} else {
			String temp = "";
			for (int i = 0; i < 56; i++) {
				if (i % 7 == 0) {
					temp = temp + keyString[55 - i];
				} else {
					temp = temp + keyString[i];
				}
			}
			setKey(temp);// 生成密匙
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
			// e.printStackTrace();
		}
	}

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
			// e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

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

	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;

		try {

			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private byte[] getDesCode(byte[] byteD) {

		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private static final byte skip1024ModulusBytes[] = { (byte) 0xF4,
			(byte) 0x88, (byte) 0xFD, (byte) 0x58, (byte) 0x4E, (byte) 0x49,
			(byte) 0xDB, (byte) 0xCD, (byte) 0x20, (byte) 0xB4, (byte) 0x9D,
			(byte) 0xE4, (byte) 0x91, (byte) 0x07, (byte) 0x36, (byte) 0x6B,
			(byte) 0x33, (byte) 0x6C, (byte) 0x38, (byte) 0x0D, (byte) 0x45,
			(byte) 0x1D, (byte) 0x0F, (byte) 0x7C, (byte) 0x88, (byte) 0xB3,
			(byte) 0x1C, (byte) 0x7C, (byte) 0x5B, (byte) 0x2D, (byte) 0x8E,
			(byte) 0xF6, (byte) 0xF3, (byte) 0xC9, (byte) 0x23, (byte) 0xC0,
			(byte) 0x43, (byte) 0xF0, (byte) 0xA5, (byte) 0x5B, (byte) 0x18,
			(byte) 0x8D, (byte) 0x8E, (byte) 0xBB, (byte) 0x55, (byte) 0x8C,
			(byte) 0xB8, (byte) 0x5A, (byte) 0x38, (byte) 0xD3, (byte) 0x34,
			(byte) 0xFD, (byte) 0x7C, (byte) 0x17, (byte) 0x57, (byte) 0x43,
			(byte) 0xA3, (byte) 0x1D, (byte) 0x18, (byte) 0x6C, (byte) 0xDE,
			(byte) 0x33, (byte) 0x21, (byte) 0x2C, (byte) 0xB5, (byte) 0x2A,
			(byte) 0xFF, (byte) 0x3C, (byte) 0xE1, (byte) 0xB1, (byte) 0x29,
			(byte) 0x40, (byte) 0x18, (byte) 0x11, (byte) 0x8D, (byte) 0x7C,
			(byte) 0x84, (byte) 0xA7, (byte) 0x0A, (byte) 0x72, (byte) 0xD6,
			(byte) 0x86, (byte) 0xC4, (byte) 0x03, (byte) 0x19, (byte) 0xC8,
			(byte) 0x07, (byte) 0x29, (byte) 0x7A, (byte) 0xCA, (byte) 0x95,
			(byte) 0x0C, (byte) 0xD9, (byte) 0x96, (byte) 0x9F, (byte) 0xAB,
			(byte) 0xD0, (byte) 0x0A, (byte) 0x50, (byte) 0x9B, (byte) 0x02,
			(byte) 0x46, (byte) 0xD3, (byte) 0x08, (byte) 0x3D, (byte) 0x66,
			(byte) 0xA4, (byte) 0x5D, (byte) 0x41, (byte) 0x9F, (byte) 0x9C,
			(byte) 0x7C, (byte) 0xBD, (byte) 0x87, (byte) 0x4B, (byte) 0x22,
			(byte) 0x19, (byte) 0x26, (byte) 0xBA, (byte) 0xAB, (byte) 0xA2,
			(byte) 0x5E, (byte) 0xC3, (byte) 0x55, (byte) 0xE9, (byte) 0x2F,
			(byte) 0x78, (byte) 0xC7 };

	private static final String keyString[] = { "G", "H", "1", "J", "k", "I",
			"M", "5", "0", "P", "6", "R", "S", "T", "U", "V", "W", "X", "A",
			"B", "C", "D", "O", "V", "W", "X", "E", "8", "Y", "Z", "0", "P",
			"Q", "3", "S", "T", "Q", "1", "S", "T", "Q", "3", "S", "T", "5",
			"3", "9", "7", "8", "2", "S", "T", "Q", "4", "X", "E", "8", "Y",
			"Z" };

	public static void main(String args[]) {
		DES des = new DES("harry");

		String str1 = "harry@321";
		str1 = des.getEncString(str1);
		System.out.println("密文:" + str1);
		str1 = des.getDesString(str1);
		System.out.println("明文:" + str1);
	}

}
