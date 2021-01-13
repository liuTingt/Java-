package ThreadTest;

/**
 * 
 * ��֤sleep�������ͷ�����
 * @author lt
 *
 */
public class SleepTest {

	private static Object o = new Object();
	
	public void doNotify() {
		synchronized (o) {
			System.out.println("notify ��ʼ");
			o.notify();
			System.out.println("notify ����");
		}
	}
	

	
	public static void main(String[] args) throws InterruptedException {
		SleepTest test = new SleepTest();
		new Thread(()->{
			synchronized (o) {
				try {
					System.out.println("sleep ��ʼ");
					Thread.sleep(2000);
					System.out.println("sleep ����");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}) .start();
		System.out.println("main����");
		Thread.sleep(200);
		test.doNotify();
	}
}
