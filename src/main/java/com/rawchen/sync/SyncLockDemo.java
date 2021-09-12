package com.rawchen.sync;

/**
 * @author RawChen
 * @date 2021/9/12 23:48
 */
public class SyncLockDemo {

	public synchronized void add() {
		add();
	}

	public static void main(String[] args) {
		new SyncLockDemo().add();

//		Object o = new Object();
//		new Thread(() -> {
//			synchronized (o) {
//				System.out.println(Thread.currentThread().getName() + " 外层");
//
//				synchronized (o) {
//					System.out.println(Thread.currentThread().getName() + " 中层");
//
//					synchronized (o) {
//						System.out.println(Thread.currentThread().getName() + " 内层");
//					}
//				}
//			}
//		}, "t1").start();
	}
}
