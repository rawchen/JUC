package com.rawchen.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 比较两接口
 * 实现Runnable接口
 * 实现Callable接口
 *
 * @author RawChen
 * @date 2021/9/14 23:44
 */
public class Demo01 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		new Thread(new MyThread01(), "AA").start();

		//Callable接口报错
//		new Thread(new MyThread02(), "BB").start();

		FutureTask<Integer> futureTask01 = new FutureTask<>(new MyThread02());

		FutureTask<Integer> futureTask02 = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + " :: come in Callable");
			return 1024;
		});

		new Thread(futureTask02, "lucy").start();
		new Thread(futureTask01, "mary").start();

//		while (!futureTask02.isDone()) {
//			System.out.println("wait...");
//		}

		System.out.println(futureTask02.get());

		System.out.println(Thread.currentThread().getName() + " :: come over");
	}
}


class MyThread01 implements Runnable {

	@Override
	public void run() {

	}
}

class MyThread02 implements Callable {

	@Override
	public Integer call() throws Exception {
		return 200;
	}
}
