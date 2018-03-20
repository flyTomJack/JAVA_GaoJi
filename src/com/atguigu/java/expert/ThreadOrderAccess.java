package com.atguigu.java.expert;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
	private int number = 1;//���
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5(int j) {
		lock.lock();
		try {
			// �ж�
			while (number != 1)
				c1.await();
			// ִ��
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// ֪ͨ
			number = 2;
			c2.signal();
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			lock.unlock();
		}
	}

	public void print10(int j) {
		lock.lock();
		try {
			// �ж�
			while (number != 2)
				c2.await();
			// ִ��
			for (int i = 0; i < 10; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// ֪ͨ
			number = 3;
			c3.signal();
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			lock.unlock();
		}
	}

	public void print15(int j) {
		lock.lock();
		try {
			// �ж�
			while (number != 3)
				c3.await();
			// ִ��
			for (int i = 0; i < 15; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// ֪ͨ
			number = 1;
			c1.signal();
		} catch (Exception e) {
			// ��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			// ����ִ�У���̸��~~
			lock.unlock();
		}
	}
}

/**
 * @Description:
 * @Author:�����İ���
 * @date:2018��3��17�� ���߳�֮�䰴˳����ã�ʵ��A->B->C �����߳�������Ҫ�����£�
 * 
 *                  AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15�� ���� AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15�� ......��10��
 */
public class ThreadOrderAccess {
	public static void main(String[] args) {
		ShareResource sr = new ShareResource();
		new Thread(() -> {
			for (int j = 0; j < 10; j++)
				sr.print5(j);
		}, "AA").start();
		new Thread(() -> {
			for (int j = 0; j < 10; j++)
				sr.print10(j);
		}, "BB").start();
		new Thread(() -> {
			for (int j = 0; j < 10; j++)
				sr.print15(j);
		}, "CC").start();
	}
}
