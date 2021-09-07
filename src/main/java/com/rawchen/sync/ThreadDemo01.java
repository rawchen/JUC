package com.rawchen.sync;

/**
 * @author RawChen
 * @date 2021/9/3 22:43
 */
public class ThreadDemo01 {

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

	static class Share {
		private int number = 0;

		public synchronized void incr() throws InterruptedException {
			while (number != 0) {
				this.wait(); //if虚假唤醒：在哪睡就在哪醒
			}

			number++;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			this.notifyAll();
		}

		public synchronized void decr() throws InterruptedException {
			while (number != 1) {
				this.wait();
			}

			number--;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			this.notifyAll();
		}
	}
}
