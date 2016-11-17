package org.heyframework.test.batch.integration;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class TestItemSlaveWriter implements ItemWriter<String> {

	@Override
	public void write(final List<? extends String> items) throws Exception {

	}

}
