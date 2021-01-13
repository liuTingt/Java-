package ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * ����ִ�й�������������쳣��Ĭ��������ᱻ�ͷ�
 * �����ڲ�������Ĺ����У����쳣Ҫ���С�ģ���Ȼ���ܻᷢ����һ�µ������
 * ���磬��һ��web app��������У����servlet��ͬ����ͬһ����Դ����ʱ����쳣�������ʣ�
 * �ڵ�һ���߳����׳��쳣�������߳̾ͻ����ͬ�����������п��ܻ���ʵ��쳣ʱ�����ݣ�
 * ���Ҫ�ǳ�С�Ĵ���ͬ��ҵ���߼��е��쳣
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
				// �˴��׳��쳣���������ͷţ������뱻�ͷţ��������������catch����ѭ������
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
