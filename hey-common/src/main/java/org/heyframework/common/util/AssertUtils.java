package org.heyframework.common.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.heyframework.common.exception.ServiceWarnException;

public abstract class AssertUtils {

	/**
	 * 如果对象不为空,就报异常信息
	 * 
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object, String message) {
		if (object != null) {
			throw new ServiceWarnException(message);
		}
	}

	/**
	 * 如果对象为空,就报异常信息
	 * 
	 * @param object
	 * @param message
	 */
	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new ServiceWarnException(message);
		}
	}

	public static void isNull(String str, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new ServiceWarnException(message);
		}
	}

	public static void isNull(Date date, String message) {
		if (DateUtils.isEmpty(date)) {
			throw new ServiceWarnException(message);
		}
	}

	@SuppressWarnings("hiding")
	public static <T> void isNull(List<T> lists, String message) {
		if (ListUtils.isEmpty(lists)) {
			throw new ServiceWarnException(message);
		}
	}

	@SuppressWarnings("hiding")
	public static <T> void isNull(Map<String, T> map, String message) {
		if (MapUtils.isEmpty(map)) {
			throw new ServiceWarnException(message);
		}
	}
}
