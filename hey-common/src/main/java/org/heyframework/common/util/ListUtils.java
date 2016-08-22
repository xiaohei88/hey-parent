package org.heyframework.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Mr.He Date : 2013-9-27 Verion : 3.0
 */
public abstract class ListUtils {

	/**
	 * 字符串去重
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> distinctString(List<T> list) {
		List<T> distinctList = new ArrayList<T>();
		for (T t : list) {
			if (!distinctList.contains(t))
				distinctList.add(t);
		}
		return distinctList;
	}

	/**
	 * list分组取出
	 * 
	 * @param totalList
	 * @param eachGroup
	 * @return
	 */
	public static <T> Map<Integer, List<T>> group(List<T> totalList, int eachGroup) {
		if (isEmpty(totalList)) {
			return null;
		}

		Map<Integer, List<T>> groupMap = new HashMap<Integer, List<T>>();
		int total = totalList.size();
		int groupNum = total / eachGroup;
		int leave = total % eachGroup;

		int start = 0;
		for (int i = 0; i < groupNum; i++) {
			int end = start + eachGroup;
			List<T> list = totalList.subList(start, end);
			groupMap.put(i, list);
			start = end;
		}

		if (leave > 0) {
			groupMap.put(groupNum, totalList.subList(start, total));
		}
		return groupMap;
	}

	/**
	 * 对象转换数组
	 * 
	 * @param list
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        
        return (T[]) list.toArray(new Object[0]);
    }

	/**
	 * 集合转换成字符串
	 * 
	 * @param lists
	 * @return
	 */
	public static <T> String toString(List<T> lists) {
		return Arrays.toString(lists.toArray()).replace("[", "").replace("]", "");
	}

	/**
	 * 验证集合是否为空
	 * 
	 * @param lists
	 * @return
	 */
	public static <T> boolean isEmpty(List<T> lists) {
		if (lists != null && lists.size() > 0)
			return false;
		return true;
	}
}
