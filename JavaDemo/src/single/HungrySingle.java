package single;

/**
 * 
 * ����ʽ����
 * ����ص��ڴ�󣬾�ʵ����һ��������JVM��֤�̰߳�ȫ
 * ��ʵ�ã��Ƽ�ʹ��
 * 
 * ȱ�㣺�����õ������װ��ʱ�����ʵ����
 *
 * @author lt
 *
 */
public class HungrySingle {

	private static final HungrySingle hungrySingle = new HungrySingle();
	
	// ���췽��������˽�еģ���ֹʵ����
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
