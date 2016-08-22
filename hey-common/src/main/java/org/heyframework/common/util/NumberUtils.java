package org.heyframework.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class NumberUtils {

	/**
	 * 数字格式化成字符串
	 * 
	 * @param num
	 * @return
	 */
	public static String bigDecimalToString(String num) {
		BigDecimal db = new BigDecimal(num);
		return db.toPlainString();
	}

	/**
	 * 格式化小数
	 * 
	 * @param num
	 * @return
	 */
	public static double decimalFormatDecimal(String num) {
		return decimalFormatDecimal(DoubleUtils.parseDouble(num), 2);
	}

	/**
	 * 格式化两位小数
	 * 
	 * @param num
	 * @return
	 */
	public static double decimalFormatDecimal(double num) {
		return decimalFormatDecimal(num, 2);
	}

	/**
	 * 格式化小数
	 * 
	 * @param num
	 * @return
	 */
	public static double decimalFormatDecimal(String num, int newScale) {
		return decimalFormatDecimal(DoubleUtils.parseDouble(num), newScale);
	}

	/**
	 * 格式化小数
	 * 
	 * @param num
	 * @return
	 */
	public static double decimalFormatDecimal(double num, int newScale) {
		BigDecimal b = new BigDecimal(num);
		return b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 格式化钱
	 * 
	 * @param num
	 * @return
	 */
	public static String decimalFormatMoney(String num) {
		return decimalFormatMoney(DoubleUtils.parseDouble(num));
	}

	/**
	 * 格式化钱
	 * 
	 * @param num
	 * @return
	 */
	public static String decimalFormatMoney(double num) {
		if (num > 0) {
			return decimalFormat(num, "##,###.00");
		}
		return "0.00";
	}

	/**
	 * 数字格式化
	 * 
	 * @param num 数字
	 * @param pattern 格式
	 * @return
	 */
	public static String decimalFormat(double num, String pattern) {
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern(pattern);
		return myformat.format(num);
	}
}
