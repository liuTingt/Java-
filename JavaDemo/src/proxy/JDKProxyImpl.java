package proxy;

public class JDKProxyImpl implements JDKProxyInterface{

	@Override
	public void Hello(String str) {
		System.out.println("Hello "+ str);
	}

}
