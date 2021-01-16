package single;

/**
 * 
 * ��������ʽ����
 * ˫�ؼ�����
 * 
 * ��������lazySingle4Ҫ��volatile��Ҫ��
 * ȱ�㣺˫�ؼ����ƺ��ѷ����������⣬������ָ�������򣬿��ܵõ�������󣬼� ���ֳ�ʼ������
 *
 * @author lt
 *
 */
public class LazySingle4 {
	private static /* volatile */ LazySingle4 lazySingle4;
	
	private LazySingle4() {
		
	}
	
	public static LazySingle4 getInstance() {
		// ҵ���߼�
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
