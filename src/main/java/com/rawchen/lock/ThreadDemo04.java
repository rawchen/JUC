package com.rawchen.lock;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author RawChen
 * @date 2021/9/8 22:36
 */
public class ThreadDemo04 {
	public static void main(String[] args) {
//		List<String> list = new ArrayList<>();

		//Vector解决
//		List<String> list = new Vector<>();

		//Collections解决
//		List<String> list = Collections.synchronizedList(new ArrayList<>());

		//CopyOnWriteArrayList解决
//		List<String> list = new CopyOnWriteArrayList<>();
//
//		for (int i = 0; i < 10; i++) {
//			new Thread(() -> {
//				//向集合添加内容
//				list.add(UUID.randomUUID().toString().substring(0, 8));
//				System.out.println(list);
//
//				//向集合获取内容
//			}, String.valueOf(i)).start();
//		}

//		Set<String> set = new CopyOnWriteArraySet<>();
//
//		for (int i = 0; i < 10; i++) {
//			new Thread(() -> {
//				//向集合添加内容
//				set.add(UUID.randomUUID().toString().substring(0, 8));
//				System.out.println(set);
//
//				//向集合获取内容
//			}, String.valueOf(i)).start();
//		}

		Map<String, String> map = new ConcurrentHashMap<>();

		for (int i = 0; i < 30; i++) {
			String key = String.valueOf(i);
			new Thread(() -> {
				//向map添加内容
				map.put(key, UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
				//向map获取内容
			}, String.valueOf(i)).start();
		}
	}
}
