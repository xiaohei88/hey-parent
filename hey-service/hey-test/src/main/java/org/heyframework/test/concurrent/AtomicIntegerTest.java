package org.heyframework.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(0);

		int a = ai.getAndSet(3);
		System.out.println("取当前的值，并设置新的值:" + a);

		int b = ai.incrementAndGet();
		System.out.println("获取当前的值，并自增:" + b);

		int c = ai.decrementAndGet();
		System.out.println("获取当前的值，并自减:" + c);

		int d = ai.addAndGet(3);
		System.out.println("获取当前的值，并加上预期的值:" + d);

		int e = ai.get();
		System.out.println("获取当前的值:" + e);
	}

}
