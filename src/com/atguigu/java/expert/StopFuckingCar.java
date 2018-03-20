package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class StopCar {
	private Semaphore sm = new Semaphore(5);// ģ�����ͣ��λ
	private long time;

	public FutureTask<String> coin(int i) {
		FutureTask<String> ft = new FutureTask<String>(() -> {
			long startTime = System.currentTimeMillis();
			stopCar(String.valueOf(i));
			long endTime = System.currentTimeMillis();
			time = (endTime - startTime) / 100;
			System.out.println("Ӧ��" + i + "������" + time + "Ԫ��");
			return String.valueOf(time);
		});
		return ft;
	}

	public String stopCar(String i) {
		try {
			sm.acquire();
			System.out.println("����" + i + "\t������ͣ��λ��");
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
			System.out.println("����" + i + "\tʹ����ͣ��λ��");
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			sm.release();
		}
		return i;
	}
}

/**
 * @Description: ������ϰ--����ģ��ͣ������
 * @Author:�����İ���
 * @date:2018��3��18��
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
				service.schedule(ft, 2, TimeUnit.SECONDS);//???Ϊʲô�趨�������ύû�������á�
//				System.out.println("********"+ft.get()+"*******");
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
