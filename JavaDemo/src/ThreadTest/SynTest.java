package ThreadTest;

/**
 * 
 * 锁定当前对象
 * @Description 
 *
 * @author lt
 *
 */
public class SynTest implements Runnable{

	@Override
	public void run() {
		synchronized (this) {
			for(int i = 0; i<10;i++) {
				System.out.println(Thread.currentThread().getName()+"-"+i);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		SynTest test = new SynTest();
		Thread t1 = new Thread(test, "t1");
		Thread t2 = new Thread(test, "t2");
		t1.start();
		t2.start();
	}

	
}
