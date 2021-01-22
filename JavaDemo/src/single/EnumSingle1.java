package single;

public class EnumSingle1{

	private EnumSingle1(){
		
	}
	
	// ��̬ö����,ÿ��ö��ʵ������static final���͵�
	static enum SingletonEnum {
		// ����һ��ö�ٶ��󣬸ö�������Ϊ����
		INSTANCE;
		
		private EnumSingle1 enmSingle1;
		
		// ˽�л�ö�ٵĹ��캯��
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
