package com.youyisi.admin.infrastructure.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 字符串加密工具
 * 
 * @author wp
 * 
 */
public class StrUtil {

	/**
	 * 字符串md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD532Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString().toUpperCase();
	}

	public static String format(String message, String... values) {
		StringBuffer buffer = new StringBuffer(message);
		for (int i = 0; i <= buffer.lastIndexOf("{");) {
			int begin = buffer.indexOf("{", i);
			int end = buffer.indexOf("}", begin);
			String beReplace = buffer.substring(begin + 1, end);
			String Replaced = values[Integer.valueOf(beReplace)];
			buffer.replace(begin + 1, end, Replaced);
			i = ++begin;

		}
		return buffer.toString().replace('{', '【').replace('}', '】');
	}

	/***
	 * 
	 * @param pwd
	 *            db查询
	 * @param pwdStr
	 *            用户输入
	 * @return
	 */
	public static boolean checkPwdMD5(String pwd, String pwdStr) {
		boolean flag = false;
		String pwd32 = StrUtil.getMD532Str(pwdStr);
		if (pwd.equals(pwd32)) {
			flag = true;
		}
		return flag;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	public static boolean notEmpty(Object str) {
		return (str != null && !"".equals(str));
	}

	public static void main(String[] args) {
		checkPwdMD5("1", "1");
	}

}
