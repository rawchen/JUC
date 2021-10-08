package com.rawchen.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author RawChen
 * @date 2021/10/8 23:31
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				2,
				5,
				2L,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
		);

		try {
			for (int i = 0; i < 10; i++) {
				threadPoolExecutor.execute(() -> {
					System.out.println(Thread.currentThread().getName() + " 办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPoolExecutor.shutdown();
		}

	}
}
