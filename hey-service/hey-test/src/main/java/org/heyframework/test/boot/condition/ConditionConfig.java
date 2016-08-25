package org.heyframework.test.boot.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

	@Bean
	@Conditional(WindowCondition.class)
	public ListService windownsListService() {
		return new WindowsListService();
	}

	@Bean
	@Conditional(LinuxCondition.class)
	public ListService linuxListService() {
		return new LinuxListService();
	}

	@Bean
	@Conditional(MacCondition.class)
	public ListService macListService() {
		return new MacListService();
	}
}
