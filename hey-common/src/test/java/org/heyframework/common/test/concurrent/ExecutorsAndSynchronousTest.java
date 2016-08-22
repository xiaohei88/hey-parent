package org.heyframework.common.test.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ExecutorsAndSynchronousTest {

	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<Integer> queue = new SynchronousQueue<Integer>();

		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					int rand = new Random().nextInt(1000);
					System.out.println("生产了一个产品：" + rand);
					System.out.println("等待三秒后运送出去...");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					queue.offer(rand);
				}
			}
		});
		service.execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("消费了一个产品:" + queue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("------------------------------------------");
				}
			}
		});
		service.shutdown();
	}
}
