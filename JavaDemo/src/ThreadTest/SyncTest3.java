package ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * 
 * Synchronized�Ż�
 * 	����ĳ����o���������o�����Է����仯����Ӱ������ʹ��
 * 	�������o�������һ�������������Ķ������仯
 * 	Ӧ�ñ��������Ķ������ñ������Ķ���
 * 
 *  ���������仯��
 *
 * @author lt
 *
 */
public class SyncTest3 {

	/* final */ Object o = new Object();
	
	public void m() {
		synchronized (o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(Thread.currentThread().getName());
			}
			
		}
	}
	
	public static void main(String[] args) {
//		SyncTest3 test = new SyncTest3();
//		
//		new Thread(test::m,"t1").start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		test.o = new Object();// ���������ı䣬�����߳�2����ִ�У����ע�͵������룬�߳�2����Զ�ò���ִ��
//		
//		new Thread(test::m, "t2").start();
		
		System.out.println(System.currentTimeMillis());
	}
}
