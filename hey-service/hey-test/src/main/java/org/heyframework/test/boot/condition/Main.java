package org.heyframework.test.boot.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * boot下Condition测试
 * 
 * @author helei
 *
 */
public class Main {

	private static AnnotationConfigApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(ConditionConfig.class);
		ListService listService = context.getBean(ListService.class);
		System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为:" + listService.showListCmd());
	}
}
