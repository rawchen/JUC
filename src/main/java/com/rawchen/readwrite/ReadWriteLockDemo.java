package com.rawchen.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author RawChen
 * @date 2021/9/23 22:30
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();

		//写数据
		for (int i = 0; i < 5; i++) {
			final int num = 1;
			new Thread(() -> {
				myCache.put(String.valueOf(num), String.valueOf(num));
			}, String.valueOf(i + 1)).start();
		}

		//取数据
		for (int i = 0; i < 5; i++) {
			final int num = i;
			new Thread(() -> {
				myCache.get(String.valueOf(num));
			}, String.valueOf(i + 1)).start();
		}
	}
}

class MyCache {
	private volatile Map<String, String> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void put(String k, String v) {
		//添加写锁
		rwl.writeLock().lock();

		System.out.println(Thread.currentThread().getName() + " 正在写操作 " + k);
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}

		map.put(k, v);
		System.out.println(Thread.currentThread().getName() + " 写完了 " + k);
	}

	public Object get(String k) {
		//添加读锁
		rwl.readLock().lock();
		Object result = null;
		try {
			System.out.println(Thread.currentThread().getName() + " 正在读操作 " + k);
			TimeUnit.MILLISECONDS.sleep(300);
			result = map.get(k);
			System.out.println(Thread.currentThread().getName() + " 读完了 " + k);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
		return result;
	}
}
