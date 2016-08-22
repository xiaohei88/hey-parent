package org.heyframework.common.util;

public abstract class DoubleUtils {

	/**
	 * 格式double
	 * 
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str) {
		if (StringUtils.isEmpty(str))
			return 0.0;
		return Double.parseDouble(str);
	}

	/**
	 * 格式double
	 * 
	 * @param d
	 * @return
	 */
	public static Double parseDouble(Double d) {
		if (d == null) {
			return 0.0;
		}
		return d;
	}
}
