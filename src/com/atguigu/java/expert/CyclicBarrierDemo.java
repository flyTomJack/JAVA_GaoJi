package com.atguigu.java.expert;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author:�����İ���
 * @date:2018��3��18�� ��������˼�ǿ�ѭ����Cyclic��ʹ�õ����ϣ�Barrier������Ҫ���������ǣ�
 *                  ��һ���̵߳���һ�����ϣ�Ҳ���Խ�ͬ���㣩ʱ�������� ֱ�����һ���̵߳�������ʱ�����ϲŻῪ�ţ�����
 *                  ���������ص��̲߳Ż�����ɻ �߳̽�������ͨ��CyclicBarrier��await()������
 * 
 *                  ����7������Ϳ����ٻ�����
 */
public class CyclicBarrierDemo {
	private static final int NUMBER = 7;

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(NUMBER, () -> {
			System.out.println("�ٻ�������");
		});
		for (int i = 1; i <= NUMBER; i++) {
			int temp = i;
			new Thread(() -> {
				try {
					System.out.println("�ռ�����" + temp + "�����飡");
					cb.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}

	}
}
