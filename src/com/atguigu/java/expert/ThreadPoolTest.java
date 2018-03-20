package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 第四种获取线程的方式。线程池，ThreadPool
 * @Author:卖花的阿飞
 * @date:2018年3月18日
 *
 */
public class ThreadPoolTest {
	// ExecutorService service=Executors.newFixedThreadPool(5);//一池5线程
	// ExecutorService service=Executors.newCachedThreadPool();//一池无限线程
	// ExecutorService service=Executors.newSingleThreadExecutor();//一池一个线程
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);// 一池5线程
		ScheduledFuture<Integer> result = null;
		try {
			for (int i = 1; i <= 15; i++) {
				int temp = i;
				result = service.schedule(() -> {
					System.out.println(Thread.currentThread().getName()+"\t"+temp + "号汽车进入了停车位");
					return new Random().nextInt(10);
				}, 2, TimeUnit.SECONDS);
//				Thread.sleep(2100);
				System.out.println( temp+ "号汽车离开了停车位");
				System.out.println("******  reslut:"+result.get());
			}
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			System.out.println("**********************" + result.get());
			service.shutdown();
		}
	}
}
