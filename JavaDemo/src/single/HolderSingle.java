package single;

/**
 * 
 * ��̬�ڲ���ķ�ʽ
 * 
 * �����ϣ�����ö���ģʽ�о�̬�����ķ�����̰߳�ȫ����ϣ��ͨ�������ع����Դ�˷ѣ�
 * Holderģʽ������������Ҫ�󣺺�����Ȼ�Ǿ�̬�������㹻������̰߳�ȫ��ͨ����̬��Holder���������ʵ�������ʵ���������� 
 *
 * @author lt
 *
 */
public class HolderSingle {
	
	private static class HolderSingleHodler {
		
		private static HolderSingle holderSingle = new HolderSingle();
		
		private HolderSingleHodler() {
			
		}
	}
	
	private HolderSingle() {
		
	}
	
	public HolderSingle getInstance() {
		return HolderSingleHodler.holderSingle;
	}
}
