package atomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @Description 
 * java �ṩ��һЩ�ڲ����������ࣨ����ʵ�ֲ���sync������������CAS������ʹ��AtomXXX��
 * AtomXXX�౾������ԭ���Եģ������ܱ�֤��������������õ�ԭ���Ե�
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
