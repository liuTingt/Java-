package loadClass;

public class CalssLoadT extends ClassLoadTest{
	static {
		System.out.println("a静态代码库啊u");
	}
	
	public CalssLoadT() {
		System.out.println("a构造函数");
	}
}
