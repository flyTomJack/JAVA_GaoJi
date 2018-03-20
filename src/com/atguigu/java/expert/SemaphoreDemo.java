package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author:�����İ���
 * @date:2018��3��18�� ���ź��������Ƕ������ֲ����� acquire����ȡ��
 *                  ��һ���̵߳���acquire����ʱ����Ҫôͨ���ɹ���ȡ�ź������ź�����1����
 *                  Ҫôһֱ����ȥ��ֱ�����߳��ͷ��ź�������ʱ�� release���ͷţ�ʵ���ϻὫ�ź�����ֵ��1��Ȼ���ѵȴ����̡߳�
 * 
 *                  �ź�����Ҫ��������Ŀ�ģ�һ�������ڶ��������Դ�Ļ���ʹ�ã���һ�����ڲ����߳����Ŀ��ơ�
 *  ���ͣ��λ��ʮ������ȥ������ÿ����ͣ�����ʱ�䣬Ȼ���뿪ͣ��λ�������������������ѭ����
 */

public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(5);//ģ�����ͣ��λ
		for (int i = 1; i <= 10; i++) {//ģ��ʮ��������ͣ��λ������������Ϊ��
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"������������ͣ��λ��������������");
					TimeUnit.SECONDS.sleep(new Random().nextInt(6));
					System.out.println(Thread.currentThread().getName()+"������ʻ����ͣ��λ��������������");
				} catch (Exception e) {
					//��ֱ��̸~��ӡһ���������~~~
					e.printStackTrace();
				} finally {
					//����ִ�У���̸��~~
					semaphore.release();
				}
			}, String.valueOf(i)).start();
		}
	}
}
