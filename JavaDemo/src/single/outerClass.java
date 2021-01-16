package single;

public class outerClass {
	
	private static outerClass o = new outerClass();
	static {
		System.out.println("外部类");
	}

	
	static class InnerClass {
		static InnerClass i = new InnerClass();
		static {
			System.out.println("内部类");
		}
	}
	
	public static void main(String[] args) {
		outerClass o = new outerClass();
		System.out.println(o.o);
		System.out.println(outerClass.InnerClass.i);
	}
}
