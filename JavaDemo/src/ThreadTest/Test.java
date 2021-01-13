package ThreadTest;

public class Test {

	public void m1() {
		System.out.println(Thread.currentThread().getName()+"m1");
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		// new Thread(test::m1 ,"t1")£¿£¿£¿
		Thread t1 = new Thread(test::m1 ,"t1");
		t1.start();
	}
}
