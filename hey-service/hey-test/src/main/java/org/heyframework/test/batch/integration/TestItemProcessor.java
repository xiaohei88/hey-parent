package org.heyframework.test.batch.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class TestItemProcessor<T> implements ItemProcessor<T, T> {

	private static final Log logger = LogFactory.getLog(TestItemProcessor.class);

	@SuppressWarnings("unchecked")
	@Override
	public T process(T item) throws Exception {
		logger.error("正在处理:" + item + "@process");
		return (T) (item + "@process");
	}

}
