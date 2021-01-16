package single;

/**
 * 
 * 饿汉式加载
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 
 * 缺点：不管用到与否，类装载时就完成实例化
 *
 * @author lt
 *
 */
public class HungrySingle {

	private static final HungrySingle hungrySingle = new HungrySingle();
	
	// 构造方法必须是私有的，防止实例化
	private HungrySingle() {}
	
	public static HungrySingle getInstance() {
		return hungrySingle;
	}
	
	public static void main(String[] args) {
		HungrySingle h1 = HungrySingle.getInstance();
		HungrySingle h2 = HungrySingle.getInstance();
		System.out.println(h1 == h2);
	}
	
}
