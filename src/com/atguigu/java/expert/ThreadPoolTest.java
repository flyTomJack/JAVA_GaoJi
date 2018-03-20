package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description: �����ֻ�ȡ�̵߳ķ�ʽ���̳߳أ�ThreadPool
 * @Author:�����İ���
 * @date:2018��3��18��
 *
 */
public class ThreadPoolTest {
	// ExecutorService service=Executors.newFixedThreadPool(5);//һ��5�߳�
	// ExecutorService service=Executors.newCachedThreadPool();//һ�������߳�
	// ExecutorService service=Executors.newSingleThreadExecutor();//һ��һ���߳�
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);// һ��5�߳�
		ScheduledFuture<Integer> result = null;
		try {
			for (int i = 1; i <= 15; i++) {
				int temp = i;
				result = service.schedule(() -> {
					System.out.println(Thread.currentThread().getName()+"\t"+temp + "������������ͣ��λ");
					return new Random().nextInt(10);
				}, 2, TimeUnit.SECONDS);
//				Thread.sleep(2100);
				System.out.println( temp+ "�������뿪��ͣ��λ");
				System.out.println("******  reslut:"+result.get());
			}
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			System.out.println("**********************" + result.get());
			service.shutdown();
		}
	}
}
