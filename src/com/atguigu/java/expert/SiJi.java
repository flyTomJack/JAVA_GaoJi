package com.atguigu.java.expert;

import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SiJi {
	static public void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		StopCar sc = new StopCar();
		try {
			for (int i = 1; i <= 50; i++) {
				FutureTask<String> ft = sc.coin(i);
				service.schedule(ft, 2, TimeUnit.SECONDS);// ???为什么设定的两秒提交没有起到作用。
				// System.out.println("********"+ft.get()+"*******");
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
