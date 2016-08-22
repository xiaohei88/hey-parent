package org.heyframework.common.exception.handler;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.heyframework.common.util.ReflectUtils;
import org.springframework.aop.ThrowsAdvice;

public class ExceptionLog implements ThrowsAdvice {

	protected final Log logger = LogFactory.getLog(getClass());

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		logger.error("*******************************************************************************");
		logger.error("Error happened in class: " + target.getClass().getName());
		logger.error("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			logger.error("arg[" + i + "]: " + ReflectUtils.describe(args[i]));
		}
		logger.error("Exception class: " + ex.getClass().getName());
		logger.error(ex.getMessage(), ex.getCause());
		logger.error("*******************************************************************************");
	}
}
