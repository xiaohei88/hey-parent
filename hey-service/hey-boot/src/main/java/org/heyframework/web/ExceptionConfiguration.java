package org.heyframework.web;

import org.heyframework.common.exception.handler.ExceptionHandler;
import org.heyframework.common.exception.handler.ExceptionLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfiguration {

	@Bean
	public ExceptionLog exceptionLog() {
		return new ExceptionLog();
	}

	@Bean
	public ExceptionHandler exceptionHandler() {
		ExceptionHandler exceptionHandler = new ExceptionHandler();
		exceptionHandler.setErrorPage("/common/error");
		return exceptionHandler;
	}
}
