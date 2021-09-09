package com.rawchen.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author RawChen
 * @date 2021/9/9 23:35
 */
public class Lock_8 {
	public static void main(String[] args) throws InterruptedException {
		Phone phone = new Phone();
		Phone phone2 = new Phone();

		new Thread(() -> {
			try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();

		Thread.sleep(100);

		new Thread(() -> {
			try {
//				phone.sendEmail();
//				phone.getHello();
				phone2.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
	}
}


/*
 * @Description: 8种锁
 *
 * 1.标准访问
 * 锁的对象
 *
 * 2.短信方法停4秒
 * 锁对象
 *
 * 3.新增普通方法
 * 与锁无关
 *
 * 4.两对象
 * 不是同一把锁
 *
 * 5.2个静态同步方法，1对象
 * 锁的字节码对象
 *
 * 6.2个静态同步方法，2对象
 * 锁的字节码对象
 *
 * 7.1个静态同步方法，1个普通同步方法，1个对象
 * 不是同一把锁
 *
 * 8.1个静态同步方法，1个普通同步方法，2个对象
 * 不是同一把锁
 */
class Phone {

	public synchronized void sendSMS() throws Exception {
		TimeUnit.SECONDS.sleep(4);
		System.out.println("-----sendSMS-----");
	}

	public synchronized void sendEmail() throws Exception {
		System.out.println("-----sendEmail-----");
	}

	public void getHello() {
		System.out.println("-----getHello-----");
	}
}
