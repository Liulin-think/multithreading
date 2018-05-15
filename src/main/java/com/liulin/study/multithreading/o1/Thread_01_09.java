package com.liulin.study.multithreading.o1;

/**
 * 守护线程
 * @author liulin_think
 *
 */
public class Thread_01_09{
	public static void main(String[] args) throws InterruptedException {
		Thread_01_09 thread_01_09 = new Thread_01_09();
		Thread1 thread1 = thread_01_09.new Thread1();
		// 设置为守护线程,必须在启动前设置,否则会抛异常:java.lang.IllegalThreadStateException
		thread1.setDaemon(true);
		thread1.start();
		// 判断是否是守护线程
		System.out.println(thread1.isDaemon());
		Thread.sleep(10);
		System.out.println("如果主线程休眠或停止,守护线程也将会被停止");
	}

	private class Thread1 extends Thread {

		private int i=0;
		@Override
		public void run() {
			while (true) {
				System.out.println(i++);
			}
		}

	}
}
