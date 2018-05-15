package com.liulin.study.multithreading;

/**
 * 线程的优先级:
 * 获取优先级:getPriority
 * 设置优先级:setPriority
 * 同等的任务,优先级高的一般比优先级低的先运行完.
 * 但是优先级还具有随机性,也就是说优先级高的不一定每次都比优先级低的先执行完.
 * 哎,此处省略n行代码.
 * @author liulin_think
 *
 */
public class Thread_01_08{
	public static void main(String[] args) {
		System.out.println("设置优先级前的main:"+Thread.currentThread().getPriority());
		// 线程的优先级具有继承性,比如A线程启动B线程,则B线程的优先级和A线程的优先级是一样的
//		Thread.currentThread().setPriority(6);
		System.out.println("设置优先级后的main:"+Thread.currentThread().getPriority());
		new Thread_01_08().new Thread1().start();;
		
	}

	private class Thread1 extends Thread {

		@Override
		public void run() {
			System.out.println("thread1.getPriority():"+this.getPriority());
			new Thread_01_08().new Thread2().start();
		}

	}
	
	private class Thread2 extends Thread {

		@Override
		public void run() {
			System.out.println("Thread2.getPriority():"+this.getPriority());
		}

	}
}
