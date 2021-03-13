package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @Description 
 * ʵ��MethodInterceptor�ӿڣ��Է�����������
 * @author lt
 *
 */
public class CGMethodInterceptor implements MethodInterceptor{

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("begin--------------");
		Object o1 = methodProxy.invokeSuper(o, objects);
		System.out.println("end--------------");
		return o1;
	}

	
	
}
