package org.heyframework.test.batch.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:batch/integration/master/master-batch-context.xml")
public class TestMaster extends AbstractTester {
	private static final Log logger = LogFactory.getLog(TestMaster.class);

	@Test
	public void test() {
		Map<String, JobParameter> params = new HashMap<String, JobParameter>(2);
		params.put("param1", new JobParameter(UUID.randomUUID().toString()));

		MasterBatchContext masterBatchContext = new MasterBatchContext("testjob", params);
		masterBatchContext.applicationContext = applicationContext;
		masterBatchContext.start();

		BatchJobTestHelper.waitForJobTopComplete(masterBatchContext);

		final BatchStatus batchStatus = masterBatchContext.getBatchStatus();
		logger.info("job finished with status: " + batchStatus);
		Assert.assertEquals("Batch Job Status", BatchStatus.COMPLETED, batchStatus);
	}

}
