package com.liulin.study.multithreading;

/**
 * yield()方法的作用是:放弃当前CPU资源,将它让给其他任务去占用CPU执行时间,但放弃的时间不确定,有可能刚放弃,马上又活动CPU时间片
 * 
 * @author liulin_think
 *
 */
public class Thread_01_07 {
	public static void main(String[] args) throws InterruptedException {
		Thread_01_07 thread_01_07 = new Thread_01_07();
		Thread1 thread1 = thread_01_07.new Thread1();
		thread1.start();
	}

	private class Thread1 extends Thread {

		private int count = 0;

		@Override
		public void run() {
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 5000000; i++) {
				count = count + i + 1;
				// 注释掉和不注释掉相差的结果还是很大的
				// Thread.yield();
			}
			long end = System.currentTimeMillis();
			System.out.println("用时:" + (end - begin) + "毫秒");
		}

	}
}
