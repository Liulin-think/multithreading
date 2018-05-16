package com.liulin.study.multithreading.o2;

/**
 * 第二章:对象及变量的并发访问
 * 多个对象多个锁:
 * 多个线程分别访问同一个类中的多个对象的同一个synchronized类型的方法时,不需要等待,也就是异步.
 * 因为多个线程访问多个对象,JVM会创建多个锁.
 * synchronized方法与对象锁:
 * 1.A线程先持有object对象的Lock锁,B线程如果这时调用object对象中synchronized类型的方法则需要等待,也就是同步,
 * 即使B线程调用的synchronized方法与A线程调用的synchronized方法不是同一个方法.
 * 2.A线程先持有object多选的Lock锁,C线程如果这时调用object对象中的非synchronized类型的方法则不需要等待,也就是异步.
 * @author liulin_think
 *
 */
public class Thread_02_01 {
	
	public static void main(String[] args) {
		Thread_02_01 thread_02_01 = new Thread_02_01();
		ModelClass modelClass = thread_02_01.new ModelClass();
		ModelClass modelClass2 = thread_02_01.new ModelClass();
		thread_02_01.new ThreadA(modelClass,"ThreadA").start();
		thread_02_01.new ThreadB(modelClass,"ThreadB").start();
		thread_02_01.new ThreadC(modelClass,"ThreadC").start();
		thread_02_01.new ThreadB(modelClass2,"ThreadB2").start();
	}
	
	class ModelClass{
		public synchronized void modelA() {
			System.out.println("modelA begin,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("modelA end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
		}
		
		public synchronized void modelB() {
			System.out.println("modelB begin,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("modelB end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
		}
		public void modelC() {
			System.out.println("modelC begin,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("modelC end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
		}
	}
	
	class ThreadA extends Thread{
		private ModelClass modelClass;
		
		
		public ThreadA(ModelClass modelClass,String name) {
			super(name);
			this.modelClass=modelClass;
		}


		@Override
		public void run() {
			modelClass.modelA();
		}
	}
	
	class ThreadB extends Thread{
		private ModelClass modelClass;
		
		public ThreadB(ModelClass modelClass,String name) {
			super(name);
			this.modelClass=modelClass;
		}
		
		@Override
		public void run() {
			modelClass.modelB();
		}
	}
	class ThreadC extends Thread{
		private ModelClass modelClass;

		public ThreadC(ModelClass modelClass,String name) {
			super(name);
			this.modelClass=modelClass;
		}

		@Override
		public void run() {
			modelClass.modelC();
		}
	}
}
