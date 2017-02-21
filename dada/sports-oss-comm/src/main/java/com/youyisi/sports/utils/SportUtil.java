package com.youyisi.sports.utils;

import java.util.Random;

/**
 * Sport工具类
 * 
 * @author hetao
 * @date 2015年4月24日 下午4:47:45
 * @version 1.0
 * @parameter
 * @return
 */
public class SportUtil {

	public static void main(String[] args) {
		System.out.println(SportUtil.numberGenerate(8));
	}

	/**
	 * 生成用户编码
	 * 
	 * @param num
	 *            位数
	 * @return Integer
	 */
	public static String numberGenerate(int num) {
		Random random = new Random();
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < num; i++) {
			if (i == 0) {
				str.append((int) (Math.random() * 9) + 1);
			} else {
				str.append(random.nextInt(10));
			}
		}
		return str.toString();
	}

	/**
	 * 获取手机验证码
	 * 
	 * @return String
	 */
	public static String getAuthCode() {
		return numberGenerate(5);
	}

	/**
	 * 获取用户编码
	 * 
	 * @return String
	 */
	public static String getUserCode() {
		return numberGenerate(8);
	}

}
