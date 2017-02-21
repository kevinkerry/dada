package com.youyisi.sports.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期 时间 处理工具类
 * 
 * @author hetao
 * @date 2015年4月23日 下午12:09:42
 * @version 1.0
 */
public class DateUtil {

	public static final String FORMATTERTIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATTERDATE = "yyyy-MM-dd";
	public static SimpleDateFormat formatter;

	// public static Date date = new Date();

	/**
	 * 取得当前时间 例: 2015-4-23 12:12:25
	 * 
	 * @return String
	 */
	public static String currentDate() {

		return new Timestamp(new Date().getTime()).toLocaleString();
	}

	/**
	 * 取得当前时间 例: 2015-4-23 12:12:25
	 * 
	 * @return String
	 */
	public static Date getDate() {
		return new Date();
	}

	public static Long getTime() {
		return getDate().getTime();
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERTIME);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERTIME);
		String str = format.format(date);
		return str;
	}

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getTimeStr() {
		Date d = new Date();
		formatter = new SimpleDateFormat(FORMATTERTIME);
		return formatter.format(d);
	}

	/**
	 * 获取当前日期 yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getDateStr() {
		Date d = new Date();
		formatter = new SimpleDateFormat(FORMATTERDATE);
		return formatter.format(d);
	}
	
	public static String formatDateToDay(Long date) {
		Date d = new Date(date);
		formatter = new SimpleDateFormat(FORMATTERDATE);
		return formatter.format(d);
	}

	@SuppressWarnings("static-access")
	public static Date dateModified(int amount) {
		Date date = strToDate(getDateStr() + " 23:59:59");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// 把日期往后增加一天.整数往后推,负数往前移动
		calendar.add(calendar.DATE, amount);
		// 这个时间就是日期往后推一天的结果
		date = calendar.getTime();
		return date;
	}

	@SuppressWarnings("static-access")
	public static String dateModifiedToStr(int amount) {
		Date date = strToDate(getDateStr() + " 23:59:59");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// 把日期往后增加一天.整数往后推,负数往前移动
		calendar.add(calendar.DATE, amount);
		// 这个时间就是日期往后推一天的结果
		date = calendar.getTime();
		return dateToStr(date);
	}

	/**
	 * 截止日期
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean Deadline(Date date) {
		return getDate().getTime() < date.getTime();
	}

	/**
	 * 精确到秒
	 * 
	 * @return
	 */
	public static Long currentDateForDay() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime() / 1000;
	}

	/**
	 * 当天 00:00:00 精确到毫秒
	 * 
	 * @return
	 */
	public static Long currentDateBegin() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/**
	 * 当天 23:59:59 精确到毫秒
	 */
	public static Long currentDateEnd() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime().getTime();
	}

	/**
	 * 精确到秒
	 * 
	 * @return
	 */
	public static Long getDateForDay(int date) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime() / 1000;
	}
	

	/**
	 * 精确
	 * 
	 * @return
	 */
	public static Long getBeforeDate(int date) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, date);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 精确到秒
	 * 
	 * @return
	 */
	public static Long getDateForDay(Long date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime() / 1000;
	}
	
	public static Long getDateForDay(Long currentDate,Integer addDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(currentDate));
		calendar.add(Calendar.DATE, addDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime() / 1000;
	}

	public static void main(String[] args) {
		Date d = new Date(1463500800l*1000l);
		formatter = new SimpleDateFormat("MM-dd");
		System.out.println( formatter.format(d));
	}

}
