package org.heyframework.common.util;

import java.util.HashMap;
import java.util.Map;

public abstract class MapUtils {

	/** 数据库表 */
	public static final String TABLE = "TABLE";

	/** 数据库表主键ID */
	public static final String TABLE_ID = "ID";

	/**
	 * 验证MAP是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		if (map != null && map.size() > 0)
			return false;
		return true;
	}

	/**
	 * map键转换成实体类字段(AA_BB_CC ===> aaBbCc)
	 * 
	 * @param map
	 */
	public static Map<String, Object> toEntityField(Map<String, Object> map) {
		if (isEmpty(map)) {
			return null;
		}
		
		Map<String, Object> newMap = new HashMap<String, Object>(map.size());
		for (Map.Entry<String, Object> oldMap : map.entrySet()) {
			String key = oldMap.getKey();
			String lowerKey = key.toLowerCase();
			if (key.contains("_")) {
				String[] lowerKeys = lowerKey.split("_");
				StringBuilder newKey = new StringBuilder();
				for (int i = 0; i < lowerKeys.length; i++) {
					String oldKey = lowerKeys[i];
					if (i != 0) {
						// 从第二个开始把第一个字符转换成大写
						char[] oldKeyChars = oldKey.toCharArray();
						oldKeyChars[0] = Character.toUpperCase(oldKeyChars[0]);
						oldKey = String.valueOf(oldKeyChars);
					}
					newKey.append(oldKey);
				}
				lowerKey = newKey.toString();
			}
			newMap.put(lowerKey, oldMap.getValue());
		}
		return newMap;
	}
}
