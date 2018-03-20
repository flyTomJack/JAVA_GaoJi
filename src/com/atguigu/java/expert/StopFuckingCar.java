package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class StopCar {
	private Semaphore sm = new Semaphore(5);// 模拟五个停车位
	private long time;

	public FutureTask<String> coin(int i) {
		FutureTask<String> ft = new FutureTask<String>(() -> {
			long startTime = System.currentTimeMillis();
			stopCar(String.valueOf(i));
			long endTime = System.currentTimeMillis();
			time = (endTime - startTime) / 100;
			System.out.println("应收" + i + "号汽车" + time + "元。");
			return String.valueOf(time);
		});
		return ft;
	}

	public String stopCar(String i) {
		try {
			sm.acquire();
			System.out.println("汽车" + i + "\t进入了停车位。");
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
			System.out.println("汽车" + i + "\t使出了停车位。");
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			sm.release();
		}
		return i;
	}
}

/**
 * @Description: 自我练习--》简单模拟停车问题
 * @Author:卖花的阿飞
 * @date:2018年3月18日
 *
 */

public class StopFuckingCar {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		park();
	}

	private static void park() throws InterruptedException, ExecutionException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		StopCar sc = new StopCar();
		try {
			for (int i = 1; i <= 50; i++) {
				FutureTask<String> ft = sc.coin(i);
				service.schedule(ft, 2, TimeUnit.SECONDS);//???为什么设定的两秒提交没有起到作用。
//				System.out.println("********"+ft.get()+"*******");
			}
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			service.shutdown();
		}
	}
}
