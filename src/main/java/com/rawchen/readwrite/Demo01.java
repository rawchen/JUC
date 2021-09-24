package com.rawchen.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author RawChen
 * @date 2021/9/24 23:56
 */
public class Demo01 {
	public static void main(String[] args) {
		//可重入读写锁对象
		ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();	//读锁
		ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//写锁

		writeLock.lock();

		System.out.println("RawChen");
//		writeLock.unlock();

		readLock.lock();
	}
}
