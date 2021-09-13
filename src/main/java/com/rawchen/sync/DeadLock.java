package com.rawchen.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author RawChen
 * @date 2021/9/13 23:32
 */
public class DeadLock {
	public static void main(String[] args) {
		Object a = new Object();
		Object b = new Object();
		new Thread(() -> {
			synchronized (a) {
				System.out.println(Thread.currentThread().getName()+" 持有锁a资源，试图获取锁b资源");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (b) {
					System.out.println(Thread.currentThread().getName()+" 获取锁b");
				}
			}
		}, "T1").start();

		new Thread(() -> {
			synchronized (b) {
				System.out.println(Thread.currentThread().getName()+" 持有锁b资源，试图获取锁b资源");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (a) {
					System.out.println(Thread.currentThread().getName()+" 获取锁a");
				}
			}
		}, "T2").start();
	}
}
