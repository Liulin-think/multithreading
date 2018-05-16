package com.liulin.study.multithreading.o2;

/**
 * 第二章:对象及变量的并发访问
 * 
 * 由于对象监视器不同,所以是异步的. 
 * 但是,对象监视器值是一样的话,则是同步的... 
 * 判断监视器对象是否相同可理解为: 对象监视器A == 对象监视器B
 * 
 * @author liulin_think
 *
 */
public class Thread_02_02 {

	public static void main(String[] args) {
		Thread_02_02 thread_02_02 = new Thread_02_02();
		one(thread_02_02);
		two(thread_02_02);
	}

	private static void one(Thread_02_02 thread_02_02) {
		/*
		 * 异步
		 */
		ModelClass modelClass = thread_02_02.new ModelClass(new Integer(0), new Integer(0));
		thread_02_02.new ThreadA(modelClass, "ThreadA").start();
		thread_02_02.new ThreadB(modelClass, "ThreadB").start();
	}
	private static void two(Thread_02_02 thread_02_02) {
		/*
		 * 同步
		 */
		ModelClass modelClass = thread_02_02.new ModelClass(0, 0);
		thread_02_02.new ThreadA(modelClass, "ThreadA").start();
		thread_02_02.new ThreadB(modelClass, "ThreadB").start();
	}

	class ModelClass {
		private Integer i;
		private Integer j;

		public ModelClass(Integer i, Integer j) {
			super();
			this.i = i;
			this.j = j;
		}

		public void modelA() {
			synchronized (i) {
				System.out.println(i == j);
				System.out.println("modelA begin,name:" + Thread.currentThread().getName() + "," + System.currentTimeMillis());
				System.out.println("modelA end,name:" + Thread.currentThread().getName() + "," + System.currentTimeMillis());
			}

		}

		public void modelB() {
			synchronized (j) {
				System.out.println("modelB begin,name:" + Thread.currentThread().getName() + "," + System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(
						"modelB end,name:" + Thread.currentThread().getName() + "," + System.currentTimeMillis());
			}
		}
	}

	class ThreadA extends Thread {
		private ModelClass modelClass;

		public ThreadA(ModelClass modelClass, String name) {
			super(name);
			this.modelClass = modelClass;
		}

		@Override
		public void run() {
			modelClass.modelA();
		}
	}

	class ThreadB extends Thread {
		private ModelClass modelClass;

		public ThreadB(ModelClass modelClass, String name) {
			super(name);
			this.modelClass = modelClass;
		}

		@Override
		public void run() {
			modelClass.modelB();
		}
	}
}
