package com.rawchen.sync;

/**
 * @author RawChen
 * @date 2021/9/1 22:57
 */
public class SaleTicket {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "AA").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "BB").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "CC").start();
	}
}

/**
 * 票资源
 */
class Ticket {

	//票数
	private int number = 30;
	//卖票
	public synchronized void sale() {
		//判断是否有票
		if (number > 0) {
			System.out.println(Thread.currentThread().getName() +
					"：卖出：" + (number--) + " 剩余：" + number);
		}
	}
}
