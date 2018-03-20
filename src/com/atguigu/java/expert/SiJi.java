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
				service.schedule(ft, 2, TimeUnit.SECONDS);// ???Ϊʲô�趨�������ύû�������á�
				// System.out.println("********"+ft.get()+"*******");
			}
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			service.shutdown();
		}
	}
}
