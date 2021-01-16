package ThreadTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * volatile�����ܱ�֤����߳��޸�running����ʱ�������Ĳ�һ�����⣬Ҳ����˵volatile�������synchronized
 * 
 * @author lt
 *
 */
public class VolatileTest1 {

	volatile static int count = 0;
	
	public void m() {
		for(int i =0; i< 10000; i++) {
			count++;// count++����ԭ�Ӳ���
		}
	}
	
	public static void main(String[] args) {
		VolatileTest1 test = new VolatileTest1();
		List<Thread> threads = new ArrayList();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(test::m, "t"+i));
		}
		
		threads.forEach(o -> o.start());
		
		// �ȴ�threaִ�����
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
