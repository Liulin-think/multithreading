package com.liulin.study.multithreading.o3;

/**
 * 第三章:线程间通信
 *  
 * @author liulin_think
 *
 */
public class Thread_03_02 extends Thread {
	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread("A");
		myThread.start();
		// myThread 执行完后再执行下面的代码
		myThread.join();
		new MyThread("B").start();
	}
	
}
