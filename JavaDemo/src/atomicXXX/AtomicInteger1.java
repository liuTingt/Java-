package atomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @Description 
 * java 提供了一些内部带有锁的类（锁的实现不是sync重量级锁，是CAS），即使用AtomXXX类
 * AtomXXX类本身方法是原子性的，但不能保证多个方法连续调用的原子性的
 *
 * @author lt
 *
 */
public class AtomicInteger1 {

	AtomicInteger count = new AtomicInteger();
	
	/* synchronized */ void m() {
		for(int i = 0; i<10000; i++) {
			count.incrementAndGet();
		}
	}
	
	
	
	public static void main(String[] args) {
		AtomicInteger1 test = new AtomicInteger1();
		List<Thread> threads = new ArrayList<>();
		
		for(int i = 0; i< 10; i++) {
			threads.add(new Thread(test::m, "t"+i));
		}
		
		threads.forEach(o->o.start());
		
		threads.forEach(o->{
			try {
				o.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println(test.count);
		
	}
	
}
