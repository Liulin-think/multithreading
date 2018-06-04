package com.liulin.study.multithreading.o3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第三章:线程间通信
 * ReentrantLock,Condition
 * @author liulin_think
 *
 */
public class Thread_03_04 extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Thread_03_04 thread_03_05 = new Thread_03_04();
		MyService myService = thread_03_05.new MyService();
		thread_03_05.new Thread1(myService, "ThreadA").start();
		thread_03_05.new Thread2(myService, "ThreadB").start();
		Thread.sleep(1000);
		myService.signalA();
//		Thread.sleep(1000);
//		myService.signalB();
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
				System.out.println(Thread.currentThread().getName() + ":awaitA begin:" + System.nanoTime());
				conditionA.await();
				System.out.println(Thread.currentThread().getName() + ":awaitA end:" + System.nanoTime());
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		}

		public void awaitB() {
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + ":awaitB begin:" + System.nanoTime());
				conditionB.await(3l,TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + ":awaitB end:" + System.nanoTime());
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
