package com.liulin.study.multithreading;

/**
 * 线程的创建与启动
 * 
 * @author liulin_think
 *
 */
public class Thread_01_01 {
	public static void main(String[] args) {
		Thread_01_01 thread_01_01 = new Thread_01_01();
		// 1.继承Thread,2.重写run方法
		MyThread myThread = thread_01_01.new MyThread();

		// 1.实现Runnable,实现run方法
		Thread myThread1 = new Thread(thread_01_01.new MyRunnable());

		// MyThread继承了Thread,而Thread实现了Runnable接口,所以,构造函数Thread(Runnable target)
		// 不仅可以传入Runnable接口,同时也可以 传入Thread类的对象.这样做完全可以将Thread对象的run方法交由给其他线程进行调用
		Thread myThread2 = new Thread(myThread);
		
		myThread.start();
		myThread1.start();
		myThread2.start();
	}

	class MyThread extends Thread {

		@Override
		public void run() {
			System.out.println("这是MyThread的run方法");
		}

	}

	class MyRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("这是MyRunnable的run方法");

		}

	}
}
