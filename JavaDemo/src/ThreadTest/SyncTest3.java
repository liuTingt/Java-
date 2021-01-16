package ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * 
 * Synchronized优化
 * 	锁定某对象o，如果对象o的属性发生变化，不影响锁的使用
 * 	如果对象o变成另外一个对象，则锁定的对象发生变化
 * 	应该避免锁定的对象引用变成另外的对象
 * 
 *  锁对象发生变化，
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
//		test.o = new Object();// 锁对象发生改变，导致线程2可以执行，如果注释掉这句代码，线程2将永远得不到执行
//		
//		new Thread(test::m, "t2").start();
		
		System.out.println(System.currentTimeMillis());
	}
}
