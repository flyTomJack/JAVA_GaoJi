package com.atguigu.java.expert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: Callable�ӿڻ�ö��߳�
 * @Author:�����İ���
 * @date:2018��3��18��
 *
 */
public class CallableDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		testfuturetask();
	}

	private static void testfuturetask() throws InterruptedException, ExecutionException, TimeoutException {
		FutureTask<String> ft1 = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + "���̵߳ĺ�̨������ͬʱ����1111");
//			Thread.sleep(4000);
			return Thread.currentThread().getName();
		});
		FutureTask<String> ft2 = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + "���̵߳ĺ�̨������ͬʱ����2222");
			Thread.sleep(4000);
			return Thread.currentThread().getName();
		});
		System.out.println("�������̣߳������ں�æ����Ҫ���Ч�ʡ��������̨ͬʱ����");
		new Thread(ft1, "AA").start();
		new Thread(ft2, "BB").start();//
		String result1 = ft1.get(2, TimeUnit.SECONDS);// future���е�get����һ����������Ϊ�����뿴����ע�͡�
		String result2 = ft2.get();// future���е�get����һ����������Ϊ�����뿴����ע�͡�
		System.out.println("result:" + result1);
		System.out.println("result:" + result2);
	}
}
/**
 * 
 * 
 * �����߳�����Ҫִ�бȽϺ�ʱ�Ĳ���ʱ�����ֲ����������߳�ʱ�����԰���Щ��ҵ����Future�����ں�̨��ɣ�
 * �����߳̽�����Ҫʱ���Ϳ���ͨ��Future�����ú�̨��ҵ�ļ���������ִ��״̬��
 * 
 * һ��FutureTask�����ں�ʱ�ļ��㣬���߳̿���������Լ����������ȥ��ȡ�����
 * 
 * ���ڼ������ʱ���ܼ�����������������δ��ɣ������� get ������һ��������ɣ�������������������
 * �Ͳ��������¿�ʼ��ȡ�����㡣get��������ȡ���ֻ���ڼ������ʱ��ȡ�������һֱ����ֱ������ת�����״̬�� Ȼ��᷵�ؽ�������׳��쳣��
 * 
 * ֻ����һ�� get�����ŵ����
 */
