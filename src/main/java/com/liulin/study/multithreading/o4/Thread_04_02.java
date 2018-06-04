package com.liulin.study.multithreading.o4;

/**
 * 第四章:定时器Timer
 *
 */
public class Thread_04_02 {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
			while (true) {
			}
		},"mythread");
		thread.start();
		ThreadGroup threadGroup = thread.getThreadGroup();
		Thread[] list=new Thread[threadGroup.activeCount()];
		threadGroup.list();
		
		System.out.println(threadGroup.activeCount());
		threadGroup.enumerate(list);
		System.out.println("list[1].getId():"+list[1].getId());
		System.out.println("thread.getId():"+thread.getId());
	}
}
