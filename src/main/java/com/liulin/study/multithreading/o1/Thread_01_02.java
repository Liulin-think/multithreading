package com.liulin.study.multithreading.o1;

/**
 * 实例变量与线程安全 注:
 * 非线程安全:主要是指多个线程对同一个对象中的同一个实例变量进行操作时,会出现值被更改、值不同步的情况.解决非线程安全可使用synchronized关键字,synchronized可在任意对象及方法上加锁
 * 
 * @author liulin_think
 *
 */
public class Thread_01_02 {

	public static void main(String[] args) throws InterruptedException {
		Thread_01_02 thread_01_02 = new Thread_01_02();
		// 不共享数据的情况
		System.out.println("=============数据不共享的情况=============");
		MyThread myThread = thread_01_02.new MyThread("myThread");
		MyThread myThread1 = thread_01_02.new MyThread("myThread1-----------------------");
		myThread.start();
		myThread1.start();

		Thread.sleep(1000);

		// 数据共享的情况
		System.out.println("=============数据共享的情况=============");
		MyThread1 myThread12 = thread_01_02.new MyThread1();
		Thread myThread13 = new Thread(myThread12, "myThread13");
		Thread myThread14 = new Thread(myThread12, "myThread14");
		Thread myThread15 = new Thread(myThread12, "myThread15");
		Thread myThread16 = new Thread(myThread12, "myThread16");
		Thread myThread17 = new Thread(myThread12, "myThread17");
		myThread13.start();
		myThread14.start();
		myThread15.start();
		myThread16.start();
		myThread17.start();

		Thread.sleep(1000);
		// 数据共享的情况_线程安全
		System.out.println("=============数据共享的情况_线程安全=============");
		MyThread2 myThread18 = thread_01_02.new MyThread2();
		Thread myThread19 = new Thread(myThread18, "myThread19");
		Thread myThread20 = new Thread(myThread18, "myThread20");
		Thread myThread21 = new Thread(myThread18, "myThread21");
		Thread myThread22 = new Thread(myThread18, "myThread22");
		Thread myThread23 = new Thread(myThread18, "myThread23");
		myThread19.start();
		myThread20.start();
		myThread21.start();
		myThread22.start();
		myThread23.start();
	}

	private class MyThread extends Thread {
		private int i = 5;

		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (i >= 0) {
				System.out.println(this.getName() + ":" + "i=" + i--);
			}
		}

	}

	private class MyThread1 extends Thread {
		private int i = 5;

		public MyThread1() {
			super();
		}

		@Override
		public void run() {
			i--;
			System.out.println(Thread.currentThread().getName() + ":" + "i=" + i);
		}

	}

	private class MyThread2 extends Thread {
		private int i = 5;

		public MyThread2() {
			super();
		}

		@Override
		public synchronized void run() {
			i--;
			System.out.println(Thread.currentThread().getName() + ":" + "i=" + i);
		}

	}

}
