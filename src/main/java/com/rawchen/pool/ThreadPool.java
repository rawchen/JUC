package com.rawchen.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池三种常用方法
 *
 * @author RawChen
 * @date 2021/10/8 23:03
 */
public class ThreadPool {
	public static void main(String[] args) {
		//一池N线程
		//ExecutorService threadPool01 = Executors.newFixedThreadPool(5);

		//一池一线程
		//ExecutorService threadPool02 = Executors.newSingleThreadExecutor();

		//一池可扩容线程
		ExecutorService threadPool03 = Executors.newCachedThreadPool();

		try {
			for (int i = 0; i < 10; i++) {
				threadPool03.execute(() -> {
					System.out.println(Thread.currentThread().getName() + " 办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool03.shutdown();
		}
	}
}
