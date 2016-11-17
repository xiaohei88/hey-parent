package org.heyframework.test.batch;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

public class TestWriter<T> implements ItemStreamWriter<T> {

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("open");
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("update");
	}

	@Override
	public void close() throws ItemStreamException {
		System.out.println("close");
	}

	@Override
	public void write(List<? extends T> items) throws Exception {
		System.out.println(items);
	}
}
