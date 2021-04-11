package loadClass;

public class CustomerLoadClassTest extends ClassLoader{

	/**
	 * jdk1.2之后默认，建议把自己的类加载逻辑实现在findClass中
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}

	
}
