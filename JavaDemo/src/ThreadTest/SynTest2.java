package ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * 程序执行过程中如果发生异常，默认情况锁会被释放
 * 所以在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况，
 * 比如，在一个web app处理过程中，多个servlet共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常时的数据，
 * 因此要非常小心处理同步业务逻辑中的异常
 * @Description 
 *
 * @author lt
 *
 */
public class SynTest2 {

	int count = 0;
	
	public synchronized void m() {
		System.out.println(Thread.currentThread().getName()+"-start");
		while(true) {
			count++;
			System.out.println(Thread.currentThread().getName()+"-"+count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(count ==5) {
				// 此处抛出异常，锁将被释放，若不想被释放，可以在这里进行catch，让循环继续
//				try {
					int s = 1/0;
//				} catch (Exception e) {
//				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		SynTest2 test = new SynTest2();
//		Thread t1 = new Thread(test::m, "t1");
//		Thread t2 = new Thread(test::m,"t2");
//		t1.start();
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t2.start();
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				test.m();
			}
		};
		
		new Thread(r, "t1").start();;
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r, "t2").start();;
	}
}
