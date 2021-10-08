package com.rawchen.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author RawChen
 * @date 2021/10/8 23:46
 */
public class ForkJoinDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//创建MyTask对象
		MyTask myTask = new MyTask(1, 100);
		//创建分支合并池对象
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
		//获取合并后结果
		Integer result = forkJoinTask.get();
		System.out.println(result);
		//关闭池对象
		forkJoinPool.shutdown();
	}
}

class MyTask extends RecursiveTask<Integer> {

	//拆分差值不超过10，计算10以内运算
	private static final Integer VALUE = 10;
	private int begin; //拆分开始值
	private int end; //拆分结束值
	private int result; //返回结果

	//创建有参构造
	public MyTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		//判断两数相加是否大于10
		if (end - begin <= VALUE) {
			//相加
			for (int i = begin; i <= end; i++) {
				result += i;
			}
		} else {//进一步拆分
			//获取数据中间值
			int middle = (begin + end) / 2;
			//拆分左边
			MyTask task01 = new MyTask(begin, middle);
			//拆分右边
			MyTask task02 = new MyTask(middle + 1, end);
			//调用方法拆分
			task01.fork();
			task02.fork();
			//合并结果
			result = task01.join() + task02.join();
		}
		return result;
	}
}
