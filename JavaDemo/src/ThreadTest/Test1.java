package ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 
 * 保证线程按顺序执行 使用join()方法实现
 *
 * @author lt
 *
 */
public class Test1 {

	static class MyThread extends Thread {
		public void run() {
			System.out.println("执行t1。。。");
		}
	}

	static class MyRunnable implements Runnable {
		public void run() {
			System.out.println("执行t2");
		}
	}

	static class MyCallable implements Callable<Integer> {
		public Integer call() throws InterruptedException {
			System.out.println("执行t3");
			return 1;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread t1 = new MyThread();
		t1.setName("t1");

		MyCallable callable = new MyCallable();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		Thread t3 = new Thread(futureTask, "t3");

		MyRunnable runnable = new MyRunnable();
		Thread t2 = new Thread(runnable, "t2");

		t2.start();
		t1.start();
		t3.start();

		// 在主线程中按顺序调用线程的join方法，不能保证按顺序执行

		new Thread(() -> {
			try {
				t1.join();
				t2.join();
				t3.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("mian");
		}).start();

	}
}
