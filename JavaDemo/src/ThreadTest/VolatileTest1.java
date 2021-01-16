package ThreadTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * volatile并不能保证多个线程修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 
 * @author lt
 *
 */
public class VolatileTest1 {

	volatile static int count = 0;
	
	public void m() {
		for(int i =0; i< 10000; i++) {
			count++;// count++不是原子操作
		}
	}
	
	public static void main(String[] args) {
		VolatileTest1 test = new VolatileTest1();
		List<Thread> threads = new ArrayList();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(test::m, "t"+i));
		}
		
		threads.forEach(o -> o.start());
		
		// 等待threa执行完毕
		threads.forEach(o -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println(count);
	}
}
