package com.rawchen.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author RawChen
 * @date 2021/9/2 23:34
 */
public class LSaleTicket {
	public static void main(String[] args) {

		LTicket ticket = new LTicket();

		new Thread(()->{
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "AA").start();

		new Thread(()->{
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "BB").start();

		new Thread(()->{
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "CC").start();
	}


	/**
	 * 票资源
	 */
	static class LTicket {

		//票数
		private int number = 30;

		//创建可重入锁
		private final ReentrantLock lock = new ReentrantLock(true); //公平锁

		//卖票
		public void sale() {
			try {
				lock.lock();
				//判断是否有票
				if (number > 0) {
					System.out.println(Thread.currentThread().getName() +
							"：卖出：" + (number--) + " 剩余：" + number);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
