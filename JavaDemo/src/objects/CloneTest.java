package objects;

import java.lang.reflect.Proxy;

public class CloneTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		MyObject myo = new MyObject();
		MyObject myo1= (MyObject) myo.clone();
		System.out.println(myo);
		System.out.println(myo1);
		Proxy.newProxyInstance(loader, interfaces, h)
	}
}
