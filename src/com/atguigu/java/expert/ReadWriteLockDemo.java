package com.atguigu.java.expert;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue{
	private Object obj;
	private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
	public void write(Object obj) {
		rwlock.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t 写入了："+obj);
		} catch (Exception e) {
			//简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			//最终执行，不谈了~~
			rwlock.writeLock().unlock();
		}
	}
	public void read() {
		rwlock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 读取了："+obj);
		} catch (Exception e) {
			//简直不谈~打印一波错误。舒服~~~
			e.printStackTrace();
		} finally {
			//最终执行，不谈了~~
			rwlock.readLock().unlock();
		}
	}
}
/**
 * @Description: 一个线程写入，一百个线程读取。
 * @Author:卖花的阿飞
 * @date:2018年3月18日
 *
 */
public class ReadWriteLockDemo {	
	public static void main(String[] args) {
		MyQueue mq=new MyQueue();
		new Thread(()->{mq.write("hahaha");},"线程写入者").start();
		for (int i = 0; i < 100; i++) {
			new Thread(()->{mq.read();},String.valueOf(i)).start();
		}
	}
}
