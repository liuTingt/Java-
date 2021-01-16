package single;

/**
 * 单例模式的三个特点：
 * 构造方法私有化
 * 实例化的变量引用私有化
 * 获取实例的方法共享
 * 
 * 懒汉式加载
 * 虽然达到按需初始化的目的，但是却带来线程不安全的问题 
 *
 * 缺点：线程不安全
 * @author lt
 *
 */
public class LazySingle {
	
	private static  LazySingle lazySingle;
	
	private LazySingle() {}
	
	public static LazySingle getInstance() {
		if(lazySingle == null) {
			lazySingle = new LazySingle();
		}
		return lazySingle;
	}
	
	public static void main(String[] args) {
		for (int i =0 ; i<10; i++) {
			new Thread(()->{
				System.out.println(LazySingle.getInstance().hashCode());
			}) .start();
		}
	}
}
