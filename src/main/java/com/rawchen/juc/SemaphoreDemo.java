package com.rawchen.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author RawChen
 * @date 2021/9/21 23:18
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " -->抢到了车位");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					System.out.println(Thread.currentThread().getName() + " 离开了车位-->");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}, String.valueOf(i + 1)).start();
		}
	}
}
