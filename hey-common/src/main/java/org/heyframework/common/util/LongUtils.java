package org.heyframework.common.util;

public abstract class LongUtils {

	/**
	 * 格式long
	 * 
	 * @param str
	 * @return
	 */
	public static long parseLong(String str) {
		if (StringUtils.isEmpty(str))
			return 0L;
		return Long.parseLong(str);
	}

	public static long parseLong(Long l) {
		if (l == null)
			return 0L;
		return l;
	}
}
