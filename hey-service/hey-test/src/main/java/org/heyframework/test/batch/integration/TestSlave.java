package org.heyframework.test.batch.integration;

import org.heyframework.common.spring.SpringContext;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:batch/integration/slave/slave1-batch-context.xml")
public class TestSlave extends AbstractTester {

	@Test
	public void test() {
		final SlaveContext slaveContext1 = new SlaveContext();
		slaveContext1.applicationContext = applicationContext;
		SpringContext.setApplicationContext(applicationContext);
		slaveContext1.start();

		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
