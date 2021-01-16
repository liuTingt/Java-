package single;

/**
 * 
 * ����ʽ����
 *  ���������Ĵ��룬���ǲ�����
 *
 * @author lt
 *
 */
public class LazySingle3 {

	private static LazySingle3 lazySingle3;
	
	private LazySingle3() {
		
	}
	
	public static LazySingle3 getInstance() {
		// ʡ��ҵ�����
		if(lazySingle3 == null) {
			// ��ͼͨ����Сͬ�������ķ�ʽ���Ч�ʣ�Ȼ��������
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
