package single;

public class EnumSingle1{

	private EnumSingle1(){
		
	}
	
	// 静态枚举类,每个枚举实例都是static final类型的
	static enum SingletonEnum {
		// 创建一个枚举对象，该对象天生为单例
		INSTANCE;
		
		private EnumSingle1 enmSingle1;
		
		// 私有化枚举的构造函数
		private SingletonEnum () {
			enmSingle1 = new EnumSingle1();
		}
		
		public EnumSingle1 getInstance() {
			return enmSingle1;
		}
		
	}
	
	public static EnumSingle1 getInstance() {
		return SingletonEnum.INSTANCE.getInstance();
	}
	
	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			new Thread(()->{
				System.out.println(EnumSingle1.getInstance().hashCode());
			}) .start();
		}
	}
}
