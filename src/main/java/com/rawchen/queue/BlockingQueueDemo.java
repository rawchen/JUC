package com.rawchen.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author RawChen
 * @date 2021/10/8 22:40
 */
public class BlockingQueueDemo {
	public static void main(String[] args) {
		//第一组
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

		System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));
		System.out.println(blockingQueue.element());

		blockingQueue.remove();

		System.out.println(blockingQueue.add("w"));

		for (String s : blockingQueue) {
			System.out.println(s);
		}

		//第二组
		BlockingQueue<String> blockingQueue02 = new ArrayBlockingQueue<>(3);
		System.out.println(blockingQueue02.offer("a"));
		System.out.println(blockingQueue02.offer("b"));
		System.out.println(blockingQueue02.offer("c"));
		System.out.println(blockingQueue02.offer("w"));

		System.out.println(blockingQueue02.poll());
		System.out.println(blockingQueue02.poll());
		System.out.println(blockingQueue02.poll());
		System.out.println(blockingQueue02.poll());

		//第三组
//		BlockingQueue<String> blockingQueue03 = new ArrayBlockingQueue<>(3);
//		try {
//			blockingQueue03.put("a");
//			blockingQueue03.put("b");
//			blockingQueue03.put("c");
////			blockingQueue03.put("w");
//
//			blockingQueue03.take();
//			blockingQueue03.take();
//			blockingQueue03.take();
//			blockingQueue03.take();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//第四组
		BlockingQueue<String> blockingQueue04 = new ArrayBlockingQueue<>(3);
		try {
			System.out.println(blockingQueue04.offer("a"));
			System.out.println(blockingQueue04.offer("b"));
			System.out.println(blockingQueue04.offer("c"));
			System.out.println(blockingQueue04.offer("w", 3L, TimeUnit.SECONDS));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
