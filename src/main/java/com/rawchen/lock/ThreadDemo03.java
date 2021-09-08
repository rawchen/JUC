package com.rawchen.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author RawChen
 * @date 2021/9/8 22:20
 */
public class ThreadDemo03 {
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print5(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print10(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print15(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
	}
}

class ShareResource {
	private int flag = 1;

	private Lock lock = new ReentrantLock();

	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5(int loop) {
		lock.lock();
		try {
			while (flag != 1) {
				c1.await();
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :轮数: " + loop);
			}
			flag = 2; //修改标志位
			c2.signal(); //通知BB线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print10(int loop) {
		lock.lock();
		try {
			while (flag != 2) {
				c2.await();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :轮数: " + loop);
			}
			flag = 3; //修改标志位
			c3.signal(); //通知CC线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print15(int loop) {
		lock.lock();
		try {
			while (flag != 3) {
				c3.await();
			}
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :轮数: " + loop);
			}
			flag = 1; //修改标志位
			c1.signal(); //通知AA线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
