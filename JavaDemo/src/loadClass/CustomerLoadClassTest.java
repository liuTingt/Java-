package loadClass;

public class CustomerLoadClassTest extends ClassLoader{

	/**
	 * jdk1.2֮��Ĭ�ϣ�������Լ���������߼�ʵ����findClass��
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}

	
}
