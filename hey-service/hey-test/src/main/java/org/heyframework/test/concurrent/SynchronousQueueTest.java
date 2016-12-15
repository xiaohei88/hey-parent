package org.heyframework.test.concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 模拟来线程池SynchronousQueue队列的使用。参考 ExecutorService service =
 * Executors.newCachedThreadPool();
 * 
 * @author helei
 *
 */
public class SynchronousQueueTest {

	public static void main(String[] args) throws InterruptedException {
		SynchronousQueueTest test = new SynchronousQueueTest();
		test.aa(1);
		test.aa(2);
		test.aa(3);
		test.aa(4);
		test.aa(5);
		test.aa(6);
		test.aa(61);
		test.aa(62);
		test.aa(63);
		test.aa(64);
		test.aa(65);
		test.aa(66);
		test.aa(67);
		test.aa(68);
	}

	final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

	private void aa(int num) {
		if (queue.offer(num)) {
		} else {
			Thread t = new Thread(new Worker(num));
			t.start();
		}

	}

	private class Worker implements Runnable {
		Integer t;

		public Worker(Integer t) {
			this.t = t;
		}

		public void run() {
			while (t != null || (t = getTask()) != null) {
				try {
					System.out.println("##获取的数据为:" + t);
				} finally {
					t = null;
				}
			}
		}

		private Integer getTask() {
			System.out.println("等待数据传入...");
			try {
				Integer a = queue.poll(TimeUnit.SECONDS.toNanos(60L), TimeUnit.NANOSECONDS);
				if (a != null) {
					return a * 1000;
				}
			} catch (InterruptedException e) {
			}
			return null;
		}
	}

}
