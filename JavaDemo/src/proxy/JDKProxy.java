package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @Description 
 *	创建JDKProxyImpl的代理类
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
		// 利用反射调用类里面的方法
		Object result = method.invoke(jdkProxyInterface, args);// result 方法的返回值，如果没有返回null
		System.out.println("end-----------------------");
		return result;
	}
	
	public static void main(String[] args) {
		JDKProxyInterface jdkProxyImpl = new JDKProxyImpl();
		
		InvocationHandler jdkProxy = new JDKProxy(jdkProxyImpl);
		
		// 参数：代理类的类加载器，被代理类的接口，代理类实例
		JDKProxyInterface proxyInstance = (JDKProxyInterface) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), jdkProxyImpl.getClass().getInterfaces(), jdkProxy);
		proxyInstance.Hello("ket");
		
	}

}
