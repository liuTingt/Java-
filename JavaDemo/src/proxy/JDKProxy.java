package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @Description 
 *	����JDKProxyImpl�Ĵ�����
 * @author lt
 *
 */
public class JDKProxy implements InvocationHandler{

	private JDKProxyInterface jdkProxyInterface;
	
	
	public JDKProxy(JDKProxyInterface jdkProxyInterface) {
		this.jdkProxyInterface = jdkProxyInterface;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("begin-----------------------");
		// ���÷������������ķ���
		Object result = method.invoke(jdkProxyInterface, args);// result �����ķ���ֵ�����û�з���null
		System.out.println("end-----------------------");
		return result;
	}
	
	public static void main(String[] args) {
		JDKProxyInterface jdkProxyImpl = new JDKProxyImpl();
		
		InvocationHandler jdkProxy = new JDKProxy(jdkProxyImpl);
		
		// ������������������������������Ľӿڣ�������ʵ��
		JDKProxyInterface proxyInstance = (JDKProxyInterface) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), jdkProxyImpl.getClass().getInterfaces(), jdkProxy);
		proxyInstance.Hello("ket");
		
	}

}
