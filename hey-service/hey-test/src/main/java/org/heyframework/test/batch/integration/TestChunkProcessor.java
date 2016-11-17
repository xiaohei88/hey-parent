package org.heyframework.test.batch.integration;

import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

public class TestChunkProcessor<I, O> extends SimpleChunkProcessor<I, O> {

	public TestChunkProcessor(ItemProcessor<? super I, ? extends O> itemProcessor, ItemWriter<? super O> itemWriter) {
		super(itemProcessor, itemWriter);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}


}
