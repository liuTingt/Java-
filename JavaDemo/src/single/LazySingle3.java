package single;

/**
 * 
 * 懒汉式加载
 *  减少锁定的代码，但是不可行
 *
 * @author lt
 *
 */
public class LazySingle3 {

	private static LazySingle3 lazySingle3;
	
	private LazySingle3() {
		
	}
	
	public static LazySingle3 getInstance() {
		// 省略业务代码
		if(lazySingle3 == null) {
			// 妄图通过减小同步代码块的方式提高效率，然而不可行
			synchronized (LazySingle3.class) {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				lazySingle3 = new LazySingle3();
			}
		}
		return lazySingle3;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			new Thread(()->{
				System.out.println(LazySingle3.getInstance().hashCode());
			}) .start();
		}
	}
}
