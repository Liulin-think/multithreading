package com.liulin.study.multithreading;

/**
 * isAlive()方法： 判断当前的线程是否处于活动状态
 * 活动状态是指线程已经启动且尚未终止，线程处于正在运行或准备开始运行的状态，就认为线程是存活的
 * 
 * @author liulin_think
 *
 */
public class Thread_01_03 {
	public static void main(String[] args) throws InterruptedException {
		Thread_01_03 thread_01_03 = new Thread_01_03();
		MyThread myThread = thread_01_03.new MyThread();
		System.out.println("begin:"+myThread.isAlive());
		myThread.start();
		// 此处执行结果为:【end:true】的原因是,此时的线程可能还未执行完毕,所以此时的值是不确定的
		System.out.println("end:"+myThread.isAlive());
		Thread.sleep(1000);
		// 此处执行的结果为:【Thread.sleep after:false】的原因是,此时线程已经在1秒内执行完成了,所以此时的线程并不处于活动状态
		System.out.println("Thread.sleep after:"+myThread.isAlive());
	}
	private class MyThread extends Thread{
		
	    public MyThread() {
			System.out.println("==========进入构造方法begin==========");
			// 注:this.isAlive()与Thread.currentThread().isAlive()的执行结果不一样,是因为构造函数中的this与Thread.currentThread()还尚有一些差异...此处只可意会,不可言传,自己慢慢体会吧
			System.out.println("this.isAlive():"+this.isAlive()); 
	        System.out.println("Thread.currentThread().isAlive():"+Thread.currentThread().isAlive()); 
	        System.out.println("构造函数中的 this==Thread.currentThread():"+(this==Thread.currentThread()));
	        System.out.println("==========进入构造方法end==========");
		}

		@Override   
	    public void run(){  
			System.out.println("==========进入run方法begin==========");
	    	System.out.println("run:"+this.isAlive());   
	    	System.out.println("run中的this==Thread.currentThread():"+(this==Thread.currentThread()));
			System.out.println("==========进入run方法end==========");
	    }  
	}  
}
