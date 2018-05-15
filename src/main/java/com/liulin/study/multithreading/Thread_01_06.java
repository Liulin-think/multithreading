package com.liulin.study.multithreading;

/**
 * 暂停线程与恢复线程
 * suspend与resume的方法缺点:独占及不同步
 * @author liulin_think
 *
 */
public class Thread_01_06 {
	public static void main(String[] args) throws InterruptedException {
		Thread_01_06 thread_01_06 = new Thread_01_06();
		Thread1 thread1 = thread_01_06.new Thread1();
		thread1.start();

		Thread.sleep(10);
		// 暂停线程
		thread1.suspend();
		System.out.println("已使用thread1.suspend()暂停线程:" + thread1.getI());
		Thread.sleep(10);
		System.out.println("已使用thread1.suspend()暂停线程:" + thread1.getI());
		// 恢复线程
		thread1.resume();
		Thread.sleep(10);
		System.out.println("已使用thread1.resume()恢复线程" + thread1.getI());
		thread1.stop();
	}

	private class Thread1 extends Thread {

		private int i = 0;

		public int getI() {
			return i;
		}

		@Override
		public void run() {
			while (true) {
				i++;
			}
		}

	}
}
