package single;

/***
 * 
 * 如果在类里定义了静态对象，会先执行非静态块，然后按照（静态优先，非静态其次的原则进行），静态块只会在非静态块执行完之后执行一次
 * 
 * 类里面没有定义静态对象，执行顺序 静态代码块、代码块、构造函数
 * @author lt
 *
 */
public class ClassLoadOrder {
	
	static ClassLoadOrder order = new ClassLoadOrder();
	
	static int num = 1;
	
	String name = "name";
	
	public ClassLoadOrder() {
		System.out.println("构造函数");
	}
	
	static {
		System.out.println("静态代码块");
		System.out.println(order.name);
	}
	
	{
		System.out.println("普通代码块");
	}
	
	

	public static void main(String[] args) {
		
	}
}
