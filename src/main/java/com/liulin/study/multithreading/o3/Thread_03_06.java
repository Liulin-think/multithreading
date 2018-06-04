package com.liulin.study.multithreading.o3;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 第三章:线程间通信
 * synchronized进阶_读写锁:
 * 读读不互斥,
 * 写读,读写,写写互斥.
 * @author liulin_think
 *
 */
public class Thread_03_06 extends Thread {
	public static void main(String[] args) {
		Thread_03_06 thread_03_06 = new Thread_03_06();
		for (int i = 0; i < 100; i++) {
			thread_03_06.new ThreadRead().start();
		}
		for (int i = 0; i < 100; i++) {
			thread_03_06.new ThreadWrite().start();
		}
		for (int i = 0; i < 100; i++) {
			thread_03_06.new ThreadRead().start();
		}
	}
	private ReentrantReadWriteLock look = new ReentrantReadWriteLock();
	
	class ThreadRead extends Thread{
		@Override
		public void run() {
			look.readLock().lock();
			System.out.println(Thread.currentThread().getName()+":look.readLock().lock();=====");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":look.readLock().lock();---");
			look.readLock().unlock();			
		}
		
	}
	

	class ThreadWrite extends Thread{
		@Override
		public void run() {
			look.writeLock().lock();
			System.out.println(Thread.currentThread().getName()+":writeLock();=====");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":writeLock();---");
			look.writeLock().unlock();				
		}
		
	}
}
