package org.heyframework.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * Author : Mr.He Date : 2013-9-27 Verion : 3.0
 */
public abstract class ArraysUtils {

	/**
	 * 数组转换成字符串
	 * 
	 * @param arrays
	 * @param initSign
	 *            是否带单引号.默认有
	 */
	public static String toString(boolean initSign, String... arrays) {
		if (!isEmpty(arrays) && arrays.length <= 0)
			return "";
		String single = initSign ? "'" : "";
		return single.concat(Arrays.toString(arrays).replace(" ", "").replace("[", "").replace("]", "").replace(",",
				single + "," + single)).concat(single);
	}

	/**
	 * 数组转换成字符串(默认带有单引号)
	 * 
	 * @param arrays
	 */
	public static String toString(String... arrays) {
		return toString(true, arrays);
	}

	/**
	 * 数组转换LIST
	 * 
	 * @param arrays
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(T... arrays) {
		return Arrays.asList(arrays);
	}

	/**
	 * 验证数组是否为空
	 * 
	 * @param args
	 * @return
	 */
	public static boolean isEmpty(Object[] args) {
		if (args != null && args.length > 0)
			return false;
		return true;
	}
}
