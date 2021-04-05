package loadClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassLoadTest {

	static int s = 0;
	public ClassLoadTest() {
		System.out.println("1构造函数");
		
	}
	static {
		System.out.println("1静态代码库啊u");
	}


	
	public static void main(String[] args) {
		ClassLoadTest cl = new CalssLoadT();
//		cl = new CalssLoadT();
		List<String> list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		for(int i = 0; i< list.size(); i++) {
			if(i == 2) {
				list.remove("c");
			}
		}
		
//		 Iterator<String> iterator = list.iterator();
//		  while (iterator.hasNext()) {
//		    String next = iterator.next();
//		    if (next.equals("b")) {
//		      list.remove(next);
//		    }
//		  }
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
