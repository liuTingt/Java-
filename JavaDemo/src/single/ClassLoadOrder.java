package single;

/***
 * 
 * ��������ﶨ���˾�̬���󣬻���ִ�зǾ�̬�飬Ȼ���գ���̬���ȣ��Ǿ�̬��ε�ԭ����У�����̬��ֻ���ڷǾ�̬��ִ����֮��ִ��һ��
 * 
 * ������û�ж��徲̬����ִ��˳�� ��̬����顢����顢���캯��
 * @author lt
 *
 */
public class ClassLoadOrder {
	
	static ClassLoadOrder order = new ClassLoadOrder();
	
	static int num = 1;
	
	String name = "name";
	
	public ClassLoadOrder() {
		System.out.println("���캯��");
	}
	
	static {
		System.out.println("��̬�����");
		System.out.println(order.name);
	}
	
	{
		System.out.println("��ͨ�����");
	}
	
	

	public static void main(String[] args) {
		
	}
}
