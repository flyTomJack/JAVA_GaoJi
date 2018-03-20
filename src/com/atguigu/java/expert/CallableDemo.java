package com.atguigu.java.expert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: Callable接口获得多线程
 * @Author:卖花的阿飞
 * @date:2018年3月18日
 *
 */
public class CallableDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		testfuturetask();
	}

	private static void testfuturetask() throws InterruptedException, ExecutionException, TimeoutException {
		FutureTask<String> ft1 = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + "主线程的后台多任务同时进行1111");
//			Thread.sleep(4000);
			return Thread.currentThread().getName();
		});
		FutureTask<String> ft2 = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + "主线程的后台多任务同时进行2222");
			Thread.sleep(4000);
			return Thread.currentThread().getName();
		});
		System.out.println("我是主线程，我现在很忙，我要提高效率。多任务后台同时进行");
		new Thread(ft1, "AA").start();
		new Thread(ft2, "BB").start();//
		String result1 = ft1.get(2, TimeUnit.SECONDS);// future类中的get方法一般放在最后，因为……请看下面注释。
		String result2 = ft2.get();// future类中的get方法一般放在最后，因为……请看下面注释。
		System.out.println("result:" + result1);
		System.out.println("result:" + result2);
	}
}
/**
 * 
 * 
 * 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
 * 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。
 * 
 * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
 * 
 * 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，！！！！！！！！！
 * 就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态， 然后会返回结果或者抛出异常。
 * 
 * 只计算一次 get方法放到最后
 */
