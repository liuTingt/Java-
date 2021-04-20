package ThreadTest;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingQueue<Runnable>(3);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 40, 5, TimeUnit.SECONDS,queue);
		for(int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						System.out.println("==========================");
						System.out.println(Thread.currentThread().getName());
						Iterator ite = queue.iterator();
						while(ite.hasNext()) {
							System.out.println(ite.next());
						}
						System.out.println("==========================");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
	}
}
