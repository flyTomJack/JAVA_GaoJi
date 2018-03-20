package com.atguigu.java.expert;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue{
	private Object obj;
	private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
	public void write(Object obj) {
		rwlock.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t д���ˣ�"+obj);
		} catch (Exception e) {
			//��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			//����ִ�У���̸��~~
			rwlock.writeLock().unlock();
		}
	}
	public void read() {
		rwlock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ��ȡ�ˣ�"+obj);
		} catch (Exception e) {
			//��ֱ��̸~��ӡһ���������~~~
			e.printStackTrace();
		} finally {
			//����ִ�У���̸��~~
			rwlock.readLock().unlock();
		}
	}
}
/**
 * @Description: һ���߳�д�룬һ�ٸ��̶߳�ȡ��
 * @Author:�����İ���
 * @date:2018��3��18��
 *
 */
public class ReadWriteLockDemo {	
	public static void main(String[] args) {
		MyQueue mq=new MyQueue();
		new Thread(()->{mq.write("hahaha");},"�߳�д����").start();
		for (int i = 0; i < 100; i++) {
			new Thread(()->{mq.read();},String.valueOf(i)).start();
		}
	}
}
