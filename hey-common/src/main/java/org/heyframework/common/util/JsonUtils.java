package org.heyframework.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.heyframework.common.bean.BaseBean;

public abstract class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * list转换json
	 * 
	 * @param list
	 * @return
	 */
	public static String listToJson(List<Map<String, Object>> list) {
		String returnValue = "";
		try {
			if (!ListUtils.isEmpty(list)) {
				returnValue = objectMapper.writeValueAsString(list).replaceAll("\"", "'");
			}
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return returnValue;
	}

	/**
	 * map转换json
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, Object> map) {
		String returnValue = "";
		try {
			if (!MapUtils.isEmpty(map)) {
				returnValue = objectMapper.writeValueAsString(map);
			}
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return returnValue;
	}

	/**
	 * 对象转json
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		String returnValue = "";
		try {
			if (object != null) {
				returnValue = objectMapper.writeValueAsString(object);
			}
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return returnValue;
	}

	/**
	 * 将对象转换成json 返回给前台
	 * 
	 * @param out
	 * @param map
	 */
	public static void writeValue(PrintWriter out, Object obj) {
		try {
			objectMapper.writeValue(out, obj);
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * json转map
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> newMap = null;
		try {
			if (!StringUtils.isEmpty(json)) {
				Map<String, Object> map = objectMapper.readValue(json, Map.class);
				if (!MapUtils.isEmpty(map)) {
					newMap = new HashMap<String, Object>(map);
					for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
						String key = mapEntry.getKey();
						String value = String.valueOf(mapEntry.getValue());
						if (key.contains("@")) {
							String newKey = key.substring(0, key.indexOf("@"));
							if (key.endsWith("@date")) {
								newMap.put(newKey, DateUtils.formatterDate(value));
							} else if (key.endsWith("@time")) {
								newMap.put(newKey, DateUtils.formatterDateTime(value));
							} else if (key.endsWith("@int")) {
								newMap.put(newKey, IntegerUtils.parseInt(value));
							} else if (key.endsWith("@long")) {
								newMap.put(newKey, LongUtils.parseLong(value));
							} else if (key.endsWith("@double")) {
								newMap.put(newKey, DoubleUtils.parseDouble(value));
							}
							newMap.remove(key);
						}
					}
				}
			}
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return newMap;
	}

	/**
	 * json转list
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonToList(String json) {
		List<Map<String, Object>> map = null;
		try {
			map = objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return map;
	}

	/**
	 * json转换对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T extends BaseBean> T jsonToObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * json转换list对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T extends BaseBean> List<T> jsonToList(String json, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			JsonFactory jsonFactory = new JsonFactory();
			JsonParser jsonParser = jsonFactory.createJsonParser(json);
			jsonParser.nextToken();
			while (jsonParser.nextToken() == JsonToken.START_OBJECT) {
				T obj = objectMapper.readValue(jsonParser, clazz);
				list.add(obj);
			}
		} catch (JsonParseException e) {
		} catch (IOException e) {
		}
		return list;
	}
}
