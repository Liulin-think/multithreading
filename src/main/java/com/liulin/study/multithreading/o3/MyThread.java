package com.liulin.study.multithreading.o3;

public class MyThread extends Thread{

	// ThreadLocal public static每个线程绑定自己的值.
	public static ThreadLocal<String> local=new ThreadLocal<>();
	public static ThreadLocal<Integer> j=new ThreadLocal<>();
	public static Integer str=0;
	
	
	public MyThread(String name) {
		super(name);
	}


	@Override
	public void run() {
		if(j.get()==null){
			j.set(0);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+".local:"+local.get());
			j.set(j.get()+1);
			local.set(Thread.currentThread().getName()+"-"+(j.get()));
			
			System.out.println(Thread.currentThread().getName()+".str:"+str);
			str++;
		}
	}

}
