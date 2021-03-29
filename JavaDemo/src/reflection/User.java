package reflection;

public class User {

	private String name;
	private int age;
	public String address;
	
	public User() {
	}
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private int privateMethod() {
		return age;
	}
	
	public static void staticMethod(int age) {
		System.out.println("ÄêÁä£º"+age);
	}
	
	public String publicMethod() {
		return name;
	}
	
	protected void protectedMethod() {
	}

	
	
}
