package com.rawchen.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author RawChen
 * @date 2021/9/21 23:07
 */
public class CyclicBarrierDemo {

	private static final int NUMBER = 7;

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
			System.out.println("-----集齐7颗龙珠召唤神龙-----");
		});

//		for (int i = 0; i < 7; i++) {
		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " 星龙珠被收集");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i + 1)).start();
		}
	}
}
