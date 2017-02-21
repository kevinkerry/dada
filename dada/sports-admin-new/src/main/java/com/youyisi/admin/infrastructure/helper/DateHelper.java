package com.youyisi.admin.infrastructure.helper;

import java.text.ParseException;
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
public class DateHelper {

	public static final String FORMATTERTIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATTERDATE = "yyyy-MM-dd";
	public static SimpleDateFormat formatter;

	// public static Date date = new Date();

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
	 * 字符串转换成时间
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToTime(String str) {
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
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERDATE);
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
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String dateToStr(Long date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERDATE);
		String str = format.format(date);
		return str;
	}

	public static String dateTimeToStr(Long time) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERTIME);
		String str = format.format(new Date(time));
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

	public static Long currentDate() {
		Date d = new Date();
		formatter = new SimpleDateFormat(FORMATTERDATE);
		return strToTime(formatter.format(d) + " 00:00:00").getTime();
	}

	@SuppressWarnings("static-access")
	public static Date dateModified(int amount) {
		Date date = strToTime(getDateStr() + " 23:59:59");
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
		Date date = strToTime(getDateStr() + " 23:59:59");
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

	public static Long getTimeScope(int day, boolean isBegin) {
		if (isBegin) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime().getTime();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime().getTime();
	}

	/**
	 * 字符串转换成日期 yyyy-MM-dd
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate2(String str) {
		formatter = new SimpleDateFormat(FORMATTERDATE);
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateToStr(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String str = f.format(date);
		return str;
	}

	public static String timeToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERDATE);
		String str = format.format(date);
		return str;
	}

	public static String timestampToStr(long time) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATTERDATE);
		return format.format(new Date(time));
	}

	public static Date timeToDate(Date date) {
		formatter = new SimpleDateFormat(FORMATTERDATE);
		Date d = null;
		try {
			d = formatter.parse(dateToStr(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String timeToDate(String date) {
		formatter = new SimpleDateFormat(FORMATTERDATE);
		return formatter.format(strToDate(date));
	}

	public static Integer timeCalculate(Date beginTime, Date endTime) {
		double newdate = endTime.getTime() - beginTime.getTime();
		Double minute = (newdate / (1000 * 60 * 60 * 24)) * 24 * 60;
		return minute.intValue();
	}

	public static Date appendDate(Date date, Integer number) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() + (number * 60 * 60 * 1000));
		return newDate;
	}

	public static int getAge(Date birthDay) throws Exception {
		// 获取当前系统时间
		Calendar cal = Calendar.getInstance();
		// 如果出生日期大于当前时间，则抛出异常
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		// 取出系统当前时间的年、月、日部分
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		// 将日期设置为出生日期
		cal.setTime(birthDay);
		// 取出出生日期的年、月、日部分
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		// 当前年份与出生年份相减，初步计算年龄
		int age = yearNow - yearBirth;
		// 当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
		if (monthNow <= monthBirth) {
			// 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}

		return age;
	}

	/**
	 * 当前日期减去天数
	 * 
	 * @return String "yyyy-MM-dd"
	 */
	public static String currentDateSubtractDay(Integer dayNum) {
		formatter = new SimpleDateFormat(FORMATTERDATE);
		long t = dayNum * (24 * 60 * 60 * 1000);
		Long time = getDate().getTime() - t;
		return formatter.format(new Date(time));
	}

	/**
	 * 开始到结束日期补全
	 * 
	 * @return String[]
	 */
	public static String[] beginForEndDate(String begin, String end) {
		int days = daysBetween(begin, end);
		if (days < 0) {
			days = 0;
		}
		String[] dates = new String[days + 1];
		// 1天
		long t = 24 * 60 * 60 * 1000;
		formatter = new SimpleDateFormat(FORMATTERDATE);
		Date date = null;
		try {
			if (days > 0) {
				for (int i = 0; i < days + 1; i++) {
					date = formatter.parse(begin);
					dates[i] = timestampToStr(date.getTime() + (i * t));
				}
			} else {
				dates[0] = timeToDate(begin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	public static int daysBetween(String smdate, String bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		long between_days = 0;
		try {
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String[] args) {
		DateHelper.getTimeScope(0, true);
		DateHelper.getTimeScope(0, false);
		System.out.println(DateHelper.getTimeScope(0, true));

		System.out.println(dateToStr(new Date(DateHelper.getTimeScope(0, true))));
		System.out.println(dateToStr(new Date(DateHelper.getTimeScope(0, false))));

	}

}
