package com.atguigu.java.expert;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch
 * @Author:�����İ���
 * @date:2018��3��18�� ��һЩ�߳�����ֱ����һЩ�߳����һϵ�в�����ű����ѡ�
 * 
 *                  CountDownLatch��Ҫ��������������һ�������̵߳���await����ʱ����Щ�̻߳�������
 *                  �����̵߳���countDown�����Ὣ��������1(����countDown�������̲߳�������)��
 *                  ����������ֵ��Ϊ0ʱ����await�����������̻߳ᱻ���ѣ�����ִ�С�
 * p
 *                  ���ͣ�5��ͬѧ½���뿪���Һ�ֵ��ͬѧ�ſ��Թ��š� Ҳ�� ����6����һͳ����
 *                  main���̱߳���Ҫ��ǰ��5���߳����ȫ���������Լ����ܿ���
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws Exception {
		CountDownLatch cdl=new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t���ع�����ˣ�");
				cdl.countDown();
			},Country.forEachCountry(i)).start();
		}
		cdl.await();
		System.out.println("�ع�\tͳһ���£�");
	}
}
