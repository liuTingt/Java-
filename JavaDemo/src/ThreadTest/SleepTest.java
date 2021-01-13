package ThreadTest;

/**
 * 
 * 验证sleep方法会释放锁吗？
 * @author lt
 *
 */
public class SleepTest {

	private static Object o = new Object();
	
	public void doNotify() {
		synchronized (o) {
			System.out.println("notify 开始");
			o.notify();
			System.out.println("notify 结束");
		}
	}
	

	
	public static void main(String[] args) throws InterruptedException {
		SleepTest test = new SleepTest();
		new Thread(()->{
			synchronized (o) {
				try {
					System.out.println("sleep 开始");
					Thread.sleep(2000);
					System.out.println("sleep 结束");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}) .start();
		System.out.println("main函数");
		Thread.sleep(200);
		test.doNotify();
	}
}
