package org.heyframework.test.batch.integration;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

/**
 * Starts an application context in a new thread.
 * 
 * @author gawain
 *
 */
public class MasterBatchContext extends AbstractStartable<BatchStatus> {

	private final Log logger = LogFactory.getLog(MasterBatchContext.class);

	private final String jobName;
	private BatchStatus batchStatus;
	private Map<String, JobParameter> params;

	private JobExecution jobExecution;

	public MasterBatchContext(final String jobName, Map<String, JobParameter> params) {
		this.jobName = jobName;
		this.params = params;
	}

	@Override
	public BatchStatus call() throws Exception {
		try {
			final JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
			final Job job = (Job) applicationContext.getBean(jobName);
			logger.info("batch context running job " + job);
			this.jobExecution = jobLauncher.run(job, new JobParameters(params));

			logger.info("JobLauncher run over.");
			// this.jobExecution = jobExecution;
			// batchStatus = jobExecution.getStatus();
		} catch (Throwable e) {
			logger.error("Exception thrown in batch context", e);
			throw new RuntimeException(e);
		}

		logger.info("batch context finished running job: " + batchStatus);
		return batchStatus;
	}

	public BatchStatus getBatchStatus() {
		if (null != jobExecution) {
			batchStatus = jobExecution.getStatus();
		}
		return batchStatus;
	}

	public JobExecution getJobExcution() {
		return this.jobExecution;
	}

}
