package com.liulin.study.multithreading.o1;

/**
 * sleep方法
 * getId方法
 * @author liulin_think
 *
 */
public class Thread_01_04 {
	public static void main(String[] args) throws InterruptedException {
		// sleep()方法的作用是让当前"正在执行的线程"休眠(暂停执行)指定的毫秒数
		Thread.sleep(1000);
		// getId()方法的作用是取得线程的唯一标识
		Thread.currentThread().getId();
	}
}
