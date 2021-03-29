package abstract1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClass1 {

	public static int hello() {
		
		String count = "1";
		String a = count + "aa"+"bb";
		String b = "1"+ "aa"+"bb";
		System.out.println(a == b);
		StringBuffer aBuffer = new StringBuffer();
		
		
		String build = new StringBuilder().append("aa").append("bb").append("cc").toString();
		String bb = "aa" + "bb" +"cc" +"dd";
		System.out.println(build == bb);
		
		String t1 = "11";
		String t2 = new String("11");
		System.out.println(t1 == t2);
		return 0;
	}

	public static void main(String[] args) {
		
		System.out.println(hello());
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		Hashtable<String, String> taHashtable = new Hashtable<String, String>();
//		AbstractClass1 cl = new AbstractClass1() {
//			@Override
//			public void hello() {
//				// TODO Auto-generated method stub
//				super.hello();
//			}
//		};
	}
}
