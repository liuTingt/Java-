package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
	
	private class Sync extends AbstractQueuedSynchronizer {
		
	}
	
	public static void main(String[] args) {
		if(t2() && t1()) {
			System.out.println("Ë«ÖØ¼ì²é");
		}
	}
	
	public static boolean t1() {
		System.out.println("t1");
		return true;
	}

	public static boolean t2() {
		System.out.println("t2");
		return false;
	}
}
