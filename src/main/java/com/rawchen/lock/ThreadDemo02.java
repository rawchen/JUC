package com.rawchen.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author RawChen
 * @date 2021/9/8 22:05
 */
public class ThreadDemo02 {
	public static void main(String[] args) {
		Share share = new Share();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					share.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					share.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					share.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					share.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "DD").start();
	}
}

class Share {
	private int number = 0;

	private Lock lock = new ReentrantLock(true); //公平锁
	private Condition condition = lock.newCondition();

	public void incr() throws InterruptedException {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + " :: " + number);
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decr() throws InterruptedException {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + " :: " + number);
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
