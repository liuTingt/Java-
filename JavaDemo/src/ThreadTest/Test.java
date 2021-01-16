package ThreadTest;

public class Test {

	public void m1() {
		System.out.println(Thread.currentThread().getName()+"m1");
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		Thread t1 = new Thread(test::m1 ,"t1");
		// new Thread(test::m1 ,"t1")lambda表达式写法相当于以下代码
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				test.m1();
			}
		}).start();
		t1.start();
	}
}
