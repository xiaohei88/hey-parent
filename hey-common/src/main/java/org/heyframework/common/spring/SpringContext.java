package org.heyframework.common.spring;

import javax.servlet.ServletContext;

import org.heyframework.common.template.TemplateConfigurer;
import org.springframework.context.ApplicationContext;

public class SpringContext {
	private static ApplicationContext applicationContext;
	private static ServletContext servletContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		if (SpringContext.applicationContext == null) {
			SpringContext.applicationContext = applicationContext;
		}
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		if (SpringContext.servletContext == null) {
			SpringContext.servletContext = servletContext;
		}
	}

	/**
	 * 获取模板器
	 *
	 * @return
	 */
	public static TemplateConfigurer getTemplateConfigurer() {
		if (SpringContext.applicationContext != null) {
			TemplateConfigurer templateConfigurer = (TemplateConfigurer) applicationContext.getBean("templateConfigurer");
			return templateConfigurer;
		}
		return null;
	}
}
