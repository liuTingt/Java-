package atomicXXX;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 
 * AtomicInteger��ģ��ABA����
 * 	���߳�t2����֮ǰ��֪��ai�Ѿ������˱仯
 * 
 * ʹ��AtomicStampedReference��¼�������ı仯
 * @author lt
 *
 */
public class AtomicABA {

	private static AtomicInteger ai = new AtomicInteger(10);
	
	static AtomicStampedReference<Integer> asr = new AtomicStampedReference<Integer>(10, 0);
	
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			ai.compareAndSet(10, 11);
			ai.compareAndSet(11, 10);
			System.out.println("�߳�"+Thread.currentThread().getName()+"--"+ai.get());
		},"t1") ;
		
		Thread t2 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean b = ai.compareAndSet(10, 13);
			System.out.println("�߳�"+Thread.currentThread().getName()+"--"+b+"--"+ai.get());
		},"t2") ;
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Thread t3 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			asr.compareAndSet(10, 11, asr.getStamp(), asr.getStamp()+1);
			
			asr.compareAndSet(11, 10, asr.getStamp(), asr.getStamp()+1);
			
		},"t3") ;
		
		
		Thread t4 = new Thread(()->{
			int stamp = asr.getStamp();
			System.out.println("˯��before ǰ��ʱ��汾��"+stamp);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("˯��after ���ʱ��汾��"+asr.getStamp());
			
			boolean bo = asr.compareAndSet(10, 11, stamp, stamp+1);
			System.out.println(bo+"��ǰֵ��"+asr.getReference());
		},"t4") ;
		
		t3.start();
		t4.start();
		try {
			t3.join();
			t4.join();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
