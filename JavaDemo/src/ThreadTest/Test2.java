package ThreadTest;

/**
 * 
 * 保证线程按顺序执行 使用join()方法实现
 * 
 * @author lt
 *
 */
public class Test2 {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(()-> {
			System.out.println("t1");
		});
		
		final Thread t2 = new Thread(()-> {
			try {
				t1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("t2");
		});
		
		final Thread t3 = new Thread(()-> {
			try {
				t2.join();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("t3");
		});
		t2.start();
		t3.start();
		t1.start();
	}
}
