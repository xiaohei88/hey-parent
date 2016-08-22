package org.heyframework.common.util;

public abstract class IntegerUtils {

	/**
	 * 转换成int
	 * 
	 * @param str
	 * @return
	 */
	public static int parseInt(String str) {
		if (StringUtils.isEmpty(str))
			return 0;
		return Integer.parseInt(str);
	}

	public static int parseInt(Integer integer) {
		if (integer == null)
			return 0;
		return integer;
	}
}
