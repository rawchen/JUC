package com.rawchen.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author RawChen
 * @date 2021/9/21 22:56
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);

		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
				//计数-1
				countDownLatch.countDown();
			}, String.valueOf(i + 1)).start();
		}

		//等待
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
	}
}
