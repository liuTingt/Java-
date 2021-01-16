package single;

/**
 * 
 * ����ʽ����
 * ʹ��synchronized��֤�̰߳�ȫ 
 * 
 * ȱ�㣺synchronized�������������ҵ���߼����ӣ�Ч�ʵ���
 *
 * @author lt
 *
 */
public class LazySingle2 {

	private static LazySingle2 lazySingle2;
	
	private LazySingle2() {
		
	}
	
	public static synchronized LazySingle2 getInstance() {
		// ʡ��ҵ����롣����������
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
