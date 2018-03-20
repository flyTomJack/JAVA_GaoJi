package com.atguigu.java.expert;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author:卖花的阿飞
 * @date:2018年3月18日 在信号量上我们定义两种操作： acquire（获取）
 *                  当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 *                  要么一直等下去，直到有线程释放信号量，或超时。 release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * 
 *                  信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *  五个停车位，十辆汽车去争抢，每辆车停随机的时间，然后离开停车位。其他汽车进来。如此循环。
 */

public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(5);//模拟五个停车位
		for (int i = 1; i <= 10; i++) {//模拟十辆汽车对停车位做出的争抢行为。
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"号汽车进入了停车位《《《《《《。");
					TimeUnit.SECONDS.sleep(new Random().nextInt(6));
					System.out.println(Thread.currentThread().getName()+"号汽车驶出了停车位》》》》》》。");
				} catch (Exception e) {
					//简直不谈~打印一波错误。舒服~~~
					e.printStackTrace();
				} finally {
					//最终执行，不谈了~~
					semaphore.release();
				}
			}, String.valueOf(i)).start();
		}
	}
}
