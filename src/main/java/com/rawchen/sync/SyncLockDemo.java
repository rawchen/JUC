package com.rawchen.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author RawChen
 * @date 2021/9/12 23:48
 */
public class SyncLockDemo {

	public synchronized void add() {
		add();
	}

	public static void main(String[] args) {

		Lock lock = new ReentrantLock();
		new Thread(() -> {
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " 外层");

				try {
					lock.lock();
					System.out.println(Thread.currentThread().getName() + " 内层");
				} finally {
					lock.unlock();
				}
			} finally {
				lock.unlock();
			}

		}, "t2").start();

		new Thread(() -> {
			lock.lock();
			System.out.println("aaa");
			lock.unlock();
		}, "aaa").start();


//		new SyncLockDemo().add();

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
