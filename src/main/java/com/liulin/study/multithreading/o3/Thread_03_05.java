package com.liulin.study.multithreading.o3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第三章:线程间通信
 * 交替打印
 * @author liulin_think
 *
 */
public class Thread_03_05 extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Thread_03_05 thread_03_05 = new Thread_03_05();
		MyService myService = thread_03_05.new MyService();
		thread_03_05.new Thread1(myService, "ThreadA").start();
		thread_03_05.new Thread2(myService, "ThreadB").start();
	}

	class Thread1 extends Thread {
		private MyService myService;

		public Thread1(MyService myService, String ThreadName) {
			super(ThreadName);
			this.myService = myService;
		}

		@Override
		public void run() {
			myService.awaitA();
		}

	}

	class Thread2 extends Thread {
		private MyService myService;

		public Thread2(MyService myService, String ThreadName) {
			super(ThreadName);
			this.myService = myService;
		}

		@Override
		public void run() {
			myService.awaitB();
		}

	}

	class MyService {
		private Lock lock = new ReentrantLock();
		public Condition conditionA = lock.newCondition();
		public Condition conditionB = lock.newCondition();

		public void awaitA() {
			try {
				lock.lock();
				while(true) {
					System.out.println("★★★★★");
					conditionB.signal();
					conditionA.await();
				}
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		}

		public void awaitB() {
			try {
				lock.lock();
				while(true) {
					System.out.println("☆☆☆☆☆");
					conditionA.signal();
					conditionB.await();
				}
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		}

		public void signalA() {
			lock.lock();
			System.out.println("signalA:" + System.nanoTime());
			conditionA.signal();
			lock.unlock();
		}
		public void signalB() {
			lock.lock();
			System.out.println("signalB:" + System.nanoTime());
			conditionB.signal();
			lock.unlock();
		}
	}
}
