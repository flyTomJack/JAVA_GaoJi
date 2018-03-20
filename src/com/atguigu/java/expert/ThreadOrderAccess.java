package com.atguigu.java.expert;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
	private int number = 1;//标记
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5(int j) {
		lock.lock();
		try {
			// 判断
			while (number != 1)
				c1.await();
			// 执行
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// 通知
			number = 2;
			c2.signal();
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			lock.unlock();
		}
	}

	public void print10(int j) {
		lock.lock();
		try {
			// 判断
			while (number != 2)
				c2.await();
			// 执行
			for (int i = 0; i < 10; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// 通知
			number = 3;
			c3.signal();
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			lock.unlock();
		}
	}

	public void print15(int j) {
		lock.lock();
		try {
			// 判断
			while (number != 3)
				c3.await();
			// 执行
			for (int i = 0; i < 15; i++)
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + j);
			// 通知
			number = 1;
			c1.signal();
		} catch (Exception e) {
			// 简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			// 最终执行，不谈了~~
			lock.unlock();
		}
	}
}

/**
 * @Description:
 * @Author:卖花的阿飞
 * @date:2018年3月17日 多线程之间按顺序调用，实现A->B->C 三个线程启动，要求如下：
 * 
 *                  AA打印5次，BB打印10次，CC打印15次 接着 AA打印5次，BB打印10次，CC打印15次 ......来10轮
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
