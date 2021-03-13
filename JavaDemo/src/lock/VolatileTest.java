package lock;

public class VolatileTest {

	public static void main(String[] args) {
		PrintFlag flag = new PrintFlag();
		flag.start();
		for(; ;) {
			synchronized (flag) {
				if(flag.isFlag()) {
					System.out.println("¸Ä±äÁË¡£¡£");
				}
			}
			
		}
	}
}


class PrintFlag extends Thread{
	
	private volatile boolean flag = false;
	
	public boolean isFlag () {
		return flag;
	}
	
	public void run () {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = true;
		System.out.println("flag = "+ flag);
	}
	
}