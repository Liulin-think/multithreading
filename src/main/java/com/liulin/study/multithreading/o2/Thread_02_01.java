package com.liulin.study.multithreading.o2;

/**
 * 第二章:对象及变量的并发访问
 * 一、多个对象多个锁:
 * 	多个线程分别访问同一个类中的多个对象的同一个synchronized类型的方法时,不需要等待,也就是异步.
 * 	因为多个线程访问多个对象,JVM会创建多个锁.
 * 二、synchronized方法与对象锁:
 * 	1.A线程先持有object对象的Lock锁,B线程如果这时调用object对象中synchronized类型的方法则需要等待,也就是同步,
 * 	即使B线程调用的synchronized方法与A线程调用的synchronized方法不是同一个方法.
 * 	2.A线程先持有object多选的Lock锁,C线程如果这时调用object对象中的非synchronized类型的方法则不需要等待,也就是异步.
 * 三、synchronized锁重入:
 * 	可重入锁:
 * 		1.自己可以再次获取自己的内部锁
 * 		2.当存在父子类继承关系时,子类也可以通过可重入锁(刚才提到的第一条)调用父类的同步方法.
 * 四、出现异常，锁自动释放:当一个线程执行的代码出现异常时,其所持有的锁会自动释放.
 * 五、同步不具有继承性,所以如果子类需要同步的话,还是需要在子类中添加synchronized
 * @author liulin_think
 *
 */
public class Thread_02_01 {
	
	public static void main(String[] args) {
		Thread_02_01 thread_02_01 = new Thread_02_01();
		one(thread_02_01);
		two(thread_02_01);
		three(thread_02_01);
		four(thread_02_01);
	}
	private static void one(Thread_02_01 thread_02_01) {
		/*
		 * A线程先持有object对象的Lock锁,B线程如果这时调用object对象中synchronized类型的方法则需要等待,也就是同步,
		 * 即使B线程调用的synchronized方法与A线程调用的synchronized方法不是同一个方法.
		 */
		ModelClass modelClass = thread_02_01.new ModelClass();
		thread_02_01.new ThreadA(modelClass,"ThreadA").start();
		thread_02_01.new ThreadB(modelClass,"ThreadB").start();
	}
	
	private static void two(Thread_02_01 thread_02_01) {
		/*
		 * A线程先持有object多选的Lock锁,C线程如果这时调用object对象中的非synchronized类型的方法则不需要等待,也就是异步.
		 */
		ModelClass modelClass = thread_02_01.new ModelClass();
		thread_02_01.new ThreadA(modelClass,"ThreadA").start();
		thread_02_01.new ThreadC(modelClass,"ThreadC").start();
	}
	
	private static void three(Thread_02_01 thread_02_01) {
		/*
		 * 多个线程分别访问同一个类中的多个对象的同一个synchronized类型的方法时,不需要等待,也就是异步.
		 * 因为多个线程访问多个对象,JVM会创建多个锁.
		 */
		ModelClass modelClass = thread_02_01.new ModelClass();
		ModelClass modelClass2 = thread_02_01.new ModelClass();
		thread_02_01.new ThreadB(modelClass,"ThreadB").start();
		thread_02_01.new ThreadB(modelClass2,"ThreadB2").start();
	}
	

	private static void four(Thread_02_01 thread_02_01) {
		/*
		 * 同步不具有继承性
		 */
		ModelClass modelClass = thread_02_01.new ModelClass();
		ModelClassExtends modelClass3=thread_02_01.new ModelClassExtends();
		thread_02_01.new ThreadA(modelClass, "ThreadA").start();
		thread_02_01.new ThreadA(modelClass3, "ThreadA2").start();
		thread_02_01.new ThreadB(modelClass,"ThreadB").start();
		thread_02_01.new ThreadB(modelClass3,"ThreadB2").start();
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
				e.printStackTrace();
			}
			System.out.println("modelB end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
		}
		public void modelC() {
			System.out.println("modelC begin,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("modelC end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
		}
	}
	
	class ModelClassExtends extends ModelClass{
		@Override
		public void modelA() {
			System.out.println("@Override modelA begin,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("@Override modelA end,name:"+Thread.currentThread().getName()+","+System.currentTimeMillis());
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
