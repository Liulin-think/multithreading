package com.liulin.study.multithreading.o4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 第四章:定时器Timer
 *
 */
public class Thread_04_01 {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) throws ParseException {
		System.out.println("main:");
		// true:设为守护进程
		Timer timer = new Timer(true);
//		Timer timer = new Timer();
		MyTask myTask = new MyTask();
		timer.schedule(myTask, Thread_04_01.simpleDateFormat.parse("2018-05-31 12:00:00"));
	}

	static class MyTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("开始执行myTask:"+Thread_04_01.simpleDateFormat.format(new Date()));
		}
	}
}
