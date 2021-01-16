package single;

/**
 * ����ģʽ�������ص㣺
 * ���췽��˽�л�
 * ʵ�����ı�������˽�л�
 * ��ȡʵ���ķ�������
 * 
 * ����ʽ����
 * ��Ȼ�ﵽ�����ʼ����Ŀ�ģ�����ȴ�����̲߳���ȫ������ 
 *
 * ȱ�㣺�̲߳���ȫ
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
