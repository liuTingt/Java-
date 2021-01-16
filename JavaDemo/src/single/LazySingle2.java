package single;

/**
 * 
 * 懒汉式加载
 * 使用synchronized保证线程安全 
 * 
 * 缺点：synchronized锁定方法，如果业务逻辑复杂，效率低下
 *
 * @author lt
 *
 */
public class LazySingle2 {

	private static LazySingle2 lazySingle2;
	
	private LazySingle2() {
		
	}
	
	public static synchronized LazySingle2 getInstance() {
		// 省略业务代码。。。。。。
		if(lazySingle2 == null) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			lazySingle2 = new LazySingle2();
		}
		return lazySingle2;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			new Thread(()->{
				System.out.println(LazySingle2.getInstance().hashCode());
			}) .start();
		}
	}
}
