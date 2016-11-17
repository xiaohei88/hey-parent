package org.heyframework.test.batch.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Starts an application context in a new thread
 * 
 * @author gawain
 *
 */
public class SlaveContext extends AbstractStartable<Integer> {

	private static final Log logger = LogFactory.getLog(SlaveContext.class);

	public SlaveContext() {
	}

	@Override
	public Integer call() throws Exception {
		try {
		} catch (Exception e) {
			logger.error("error initializing slave context", e);
			throw new RuntimeException(e);
		}

		return 0;
	}

	// public int writtenCount() {
	// TestItemWriter writer = (TestItemWriter)
	// applicationContext.getBean("writer");
	// return writer.getChunkCount();
	// }

	// public BatchStatus getBatchStatus() {
	// return batchStatus;
	// }

}
