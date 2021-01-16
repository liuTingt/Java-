package single;

/**
 * 
 * 单例懒汉式加载
 * 双重检查机制
 * 
 * 单例对象lazySingle4要加volatile吗？要加
 * 缺点：双重检查机制很难发生并发问题，但由于指令重排序，可能得到半个对象，即 部分初始化问题
 *
 * @author lt
 *
 */
public class LazySingle4 {
	private static /* volatile */ LazySingle4 lazySingle4;
	
	private LazySingle4() {
		
	}
	
	public static LazySingle4 getInstance() {
		// 业务逻辑
		if (lazySingle4 == null) {
			synchronized (LazySingle4.class) {
				if(lazySingle4 == null) {
					lazySingle4 = new LazySingle4();
				}
			}
		}
		return lazySingle4;
	}

	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			new Thread(()->{
				System.out.println(LazySingle4.getInstance().hashCode());
			}) .start();
		}
	}
}
