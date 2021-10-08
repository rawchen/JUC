package com.rawchen.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author RawChen
 * @date 2021/10/9 0:05
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//异步调用 无返回值
		CompletableFuture<Void> completableFuture01 = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "completableFuture01");
		});
		completableFuture01.get();

		//异步调用 有返回值
		CompletableFuture<Integer> completableFuture02 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "completableFuture02");
			//模拟异常
			int i = 1/0;

			return 1024;
		});
		completableFuture02.whenComplete((t, u) -> {
			System.out.println("t: " + t); //返回值
			System.out.println("u: " + u); //异常
		}).get();
	}
}
