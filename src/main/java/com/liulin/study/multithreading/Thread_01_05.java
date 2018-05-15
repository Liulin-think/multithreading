package com.liulin.study.multithreading;

/**
 * 停止线程
 * @author liulin_think
 *
 */
public class Thread_01_05 {
	public static void main(String[] args) throws InterruptedException {
		Thread_01_05 thread_01_05 = new Thread_01_05();
		Thread1 thread1 = thread_01_05.new Thread1();
		thread1.start();
		
		Thread.sleep(10);
		
		// 建议使用 线程停止标记 thread1.interrupt() 配合线程内部使用this.isInterrupted()来判断线程是否是终止状态,使用return退出等操作来停止线程
		thread1.interrupt();
		// 不建议使用stop停止线程,会造成资源未释放等问题
//		thread1.stop();
		System.out.println("thread1.interrupt()");
	}
	private class Thread1 extends Thread{

		private int i=0;
		@Override
		public void run() {
			while (!this.isInterrupted()) {
				// 线程是否已经中断,表示执行该方法后不清除中断状态
//				this.isInterrupted();
				// 当前线程是否已经中断,执行后具有将状态标志清除为false
//				Thread.interrupted();
				System.out.println(i++);
			}
			System.out.println("线程被标记为停止");
		}
		
	}
}
