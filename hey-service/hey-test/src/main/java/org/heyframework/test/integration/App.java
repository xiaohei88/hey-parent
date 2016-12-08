package org.heyframework.test.integration;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	@SuppressWarnings("unused")
	private static ClassPathXmlApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("integration/integration-file.xml");
		// GreeterService greeterService =
		// applicationContext.getBean("greeterServiceImpl",
		// GreeterService.class);
		// greeterService.greet("Spring Integration!");

		// greeterService.greet2("Spring Integration (with response)!");
	}

}
