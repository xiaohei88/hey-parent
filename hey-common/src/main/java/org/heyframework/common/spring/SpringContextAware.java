package org.heyframework.common.spring;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

public class SpringContextAware implements ApplicationContextAware, ServletContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.setApplicationContext(applicationContext);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.log("Initializing heyframework SpringContext ");
		SpringContext.setServletContext(servletContext);
	}
}
