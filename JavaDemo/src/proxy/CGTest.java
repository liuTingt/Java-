package proxy;

import java.util.concurrent.atomic.LongAdder;

import net.sf.cglib.proxy.Enhancer;

public class CGTest {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CGSubject.class);
		enhancer.setCallback(new CGMethodInterceptor());
		CGSubject cgSubject = (CGSubject) enhancer.create();
//		LongAdder
//		cgSubject.hello();
	}
}
