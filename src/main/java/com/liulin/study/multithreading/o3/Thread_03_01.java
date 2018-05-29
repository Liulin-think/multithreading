package com.liulin.study.multithreading.o3;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 第三章:线程间通信
 * 
 * @author liulin_think
 *
 */
public class Thread_03_01 extends Thread {


	public static void main(String[] args) throws IOException, InterruptedException {
		Thread_03_01 thread_03_01 = new Thread_03_01();

		//PipedInputStream,PipedOutputStream字节流
		//PipedReader,PipedWriter字符流.
		PipedReader inputStream = new PipedReader();
		PipedWriter outputStream = new PipedWriter();
		
		// 输入流与输出流关联,outputStream.connect(inputStream);与inputStream.connect(outputStream);效果是一样的.
		inputStream.connect(outputStream);

		thread_03_01.new ThreadRead(inputStream).start();
		thread_03_01.new ThreadWrite(outputStream).start();
	}

	class ThreadWrite extends Thread {

		private PipedWriter out;

		public ThreadWrite(PipedWriter out) {
			super();
			this.out = out;
		}

		@Override
		public void run() {
			try {
				System.out.println("write:");
				String outData = "";
				int length = 0;
				for (int i = 0; i < 1000; i++) {
					outData = "a" + (i + 1);
					length += outData.length();
					System.out.print("\n" + outData);
					out.write(outData);
				}
				out.close();
				System.out.println();
				System.out.println("\n" + length);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class ThreadRead extends Thread {

		private PipedReader input;

		public ThreadRead(PipedReader input) {
			super();
			this.input = input;
		}

		@Override
		public void run() {
			System.out.println("read:");
			char[] byteArray = new char[100];
			int i = 0;
			try {
				int read = input.read(byteArray);
				while (read != -1) {
					i += read;
					System.out.print(byteArray);
					byteArray = new char[100];
					/*
					 * 注:read 并不会把read前的byteArray的值清空,所以,需要在read前把byteArray的值手动清空,
					 * 否则,例如:定义的byteArray长度为100,最后一次读到的值长度为30,
					 * 则byteArray[30]至byteArray[99]的值为上一次读到的值,会产生脏数据.
					 * java.io.PipedReader.read(char[] cbuf, int off, int len) throws IOException;
					 * read关键代码:
					 * cbuf[off] =  (char)c;
					 * int rlen = 1;
					 * while ((in >= 0) && (--len > 0)) {
					 * 		cbuf[off + rlen] = buffer[out++];
					 * 		rlen++;
					 * 		if (out >= buffer.length) {
					 * 		out = 0;
					 * 		}
					 * 		if (in == out) {
					 * 			in = -1;
					 * 		}
					 * }
					 */
					read = input.read(byteArray);
				}
				input.close();
			} catch (

			IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n" + i);
		}

	}
}
