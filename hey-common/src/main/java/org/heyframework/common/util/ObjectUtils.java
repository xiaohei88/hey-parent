package org.heyframework.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public abstract class ObjectUtils {

	/**
	 * 对象的克隆
	 * 
	 * @param object
	 * @return
	 */
	public static Object clone(Object object) {
		try {
			return BeanUtils.cloneBean(object);
		} catch (IllegalAccessException e) {
		} catch (InstantiationException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		return null;
	}

	/**
	 * 得到类名
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassName(Class<?> clazz) {
		if (clazz == null) {
			return "";
		}
		String className = clazz.getName();
		int endIndex = className.lastIndexOf(".");
		return className.substring(endIndex + 1);
	}

	/**
	 * 对象为NULL转换成空
	 * 
	 * @param str
	 * @return
	 */
	public static String toEmpty(Object str) {
		if (str == null) {
			return "";
		}
		return String.valueOf(str);
	}
}
