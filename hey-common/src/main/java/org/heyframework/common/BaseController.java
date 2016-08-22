package org.heyframework.common;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	protected final static String CODE = "code";
	protected final static String MSG = "msg";

	protected final static Map<String, Object> ERROR_MAP = new HashMap<String, Object>(2);
	protected final static Map<String, Object> WARN_MAP = new HashMap<String, Object>(2);
	protected final static Map<String, Object> SUCCESS_MAP = new HashMap<String, Object>(2);

	static {
		ERROR_MAP.put(CODE, 0);
		ERROR_MAP.put(MSG, "操作失败.");

		WARN_MAP.put(CODE, 1);
		WARN_MAP.put(MSG, "");

		SUCCESS_MAP.put(CODE, 2);
		SUCCESS_MAP.put(MSG, "操作成功.");
	}

	protected Map<String, Object> returnMsg() {
		return new HashMap<String, Object>(WARN_MAP);
	}

	protected Map<String, Object> returnMsg(Map<String, Object> msgMap) {
		return new HashMap<String, Object>(msgMap);
	}

	/**
	 * 异常统一处理,返回前台统一打印
	 * <p>
	 * 1、所有的控制器必须实现继承与BaseController.java才可以
	 * 2、还有一种方法就是实现接口.查看ExceptionHandler.java
	 * </p>
	 * 
	 * @param ex
	 * @return
	 */
	// @ExceptionHandler(Exception.class)
	// @ResponseBody
	public Map<String, Object> exceptionHandler(Exception ex) {
		Map<String, Object> errorMap = returnMsg(ERROR_MAP);
		errorMap.put(MSG, ex.getMessage());
		Map<String, Object> childMap = getExceptionMessage(ex);
		if (childMap != null) {
			errorMap.putAll(childMap);
		}
		return errorMap;
	}

	/**
	 * 异常信息
	 * 
	 * @param ex
	 * @return
	 */
	protected Map<String, Object> getExceptionMessage(Exception ex) {
		return null;
	}
}
