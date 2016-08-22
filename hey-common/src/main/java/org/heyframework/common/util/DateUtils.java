package org.heyframework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

	private static SimpleDateFormat dateFormat = null;

	private static SimpleDateFormat datetimeFormat = null;

	private static final String pattern = "yyyy-MM-dd";

	private static final String patternTime = "yyyy-MM-dd HH:mm:ss";

	static {
		dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);

		datetimeFormat = new SimpleDateFormat(patternTime);
		datetimeFormat.setLenient(false);
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateString(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTimeString(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatterDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Date formatterDateTime(String dateTime) {
		try {
			return datetimeFormat.parse(dateTime);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrSeasonLastDay() {
		return getMonthLastDay(getSeasonDate(new Date())[2]);
	}

	/**
	 * 得到日期相减天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysOfTowDiffDate(Date startDate, Date endDate) {
		double l_startTime = getMillisOfDate(startDate);
		double l_endTime = getMillisOfDate(endDate);
		int betweenDays = (int) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 得到日期相减天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysOfTowDiffDate(String startDate, String endDate) {
		Date l_startDate = formatterDate(startDate);
		Date l_endDate = formatterDate(endDate);
		double l_startTime = getMillisOfDate(l_startDate);
		double l_endTime = getMillisOfDate(l_endDate);
		int betweenDays = (int) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 得到新的日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDate(Date date, long day) {
		long time = date.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 时间加月份
	 * 
	 * @param dateTime
	 * @param month
	 * @return
	 */
	public static String getDateForPlusMonth(Date dateTime, int month) {
		Calendar Cal = Calendar.getInstance();
		Cal.setTime(dateTime);
		Cal.add(Calendar.MONTH, month);
		return dateFormat.format(Cal.getTime());
	}

	/**
	 * 时间加小时
	 * 
	 * @param dateTime
	 * @param hour
	 * @return
	 */
	public static String getDateTimeForPlusHour(Date dateTime, int hour) {
		Calendar Cal = Calendar.getInstance();
		Cal.setTime(dateTime);
		Cal.add(Calendar.HOUR_OF_DAY, hour);
		return datetimeFormat.format(Cal.getTime());
	}

	/**
	 * 毫秒
	 * 
	 * @param p_date
	 * @return
	 */
	public static double getMillisOfDate(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSeasonLastDay(Date date) {
		return getMonthLastDay(getSeasonDate(date)[2]);
	}

	/**
	 * 当前月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrMonthLastDay() {
		return getMonthLastDay(new Date());
	}

	/**
	 * 月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static int getCurrYear() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return currentYear;
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, getCurrYear());
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, getCurrYear());
		return calendar.getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getTodayTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 获取当前时间(包含时分秒)
	 * 
	 * @return
	 */
	public static String getTodayDateTime() {
		Calendar cal = Calendar.getInstance();
		return datetimeFormat.format(cal.getTime());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTodayDate() {
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getTodayDateForDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 判断对象是否是日期类型
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isDate(Object value) {
		try {
			@SuppressWarnings("unused")
			Date date = (Date) value;
		} catch (ClassCastException cce) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否是日期类型
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {
		if (validateFormat(date)) {
			if (!isTime(date)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否是日期时间类型
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isTime(String date) {
		if (validateFormat(date)) {
			if (date.contains(":") && !date.contains("00:00:00")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证日期是否为空
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isEmpty(Date date) {
		if (date == null)
			return true;
		return false;
	}

	/**
	 * 判断是否是日期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean validateFormat(String date) {
		try {
			dateFormat.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取当前第几季度
	 * 
	 * @return
	 */
	public static int getCurrSeason() {
		return getSeason(getTodayDateForDate());
	}

	/**
	 * 根据日期得到当前第几季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	private static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {
			// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {
			// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {
			// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {
			// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}
}
