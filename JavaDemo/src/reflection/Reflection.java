package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

	public static void main(String[] args) throws Exception, SecurityException {
		Class clazz = null;
		try {
			clazz = Class.forName("reflection.User");
			System.out.println("class对象：-------------------------------");
			System.out.println(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("类中所有公有构造函数：-------------------------------");
		// 获取构造函数
		Constructor<User>[] constructors = clazz.getConstructors();
		for(int i =0; i< constructors.length; i++) {
			System.out.println(constructors[i]);
		}
		System.out.println("类中所有构造函数：-------------------------------");
		Constructor<User>[] cons = clazz.getDeclaredConstructors();
		for(int i =0; i< cons.length; i++) {
			System.out.println(cons[i]);
		}
		
		System.out.println("public方法：-------------------------------");
		// 获取public方法，包括父类
		Method[] methods = clazz.getMethods();
		for(int i =0; i< methods.length; i++) {
			System.out.println(methods[i]);
		}
		System.out.println("本类的所有方法：-------------------------------");
		Method m[] = clazz.getDeclaredMethods();
		for(int i =0; i< m.length; i++) {
			System.out.println(m[i]);
		}
		
		System.out.println("public字段：-------------------------------");
		// 获取public字段
		Field[] fields = clazz.getFields();
		for(int i =0; i< fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println("本类所有字段：-------------------------------");
		Field[] f = clazz.getDeclaredFields();
		for(int i =0; i< f.length; i++) {
			System.out.println(f[i]);
		}
		try {
			Field field = clazz.getField("address");
			System.out.println(field);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("创建对象---------------------");
		User user = null;
		try {
			user = (User)clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.staticMethod(21);
		
		System.out.println("反射公有构造方法-------------------------------");
		try {
			Constructor<?> constr = clazz.getConstructor(String.class, int.class);
//			constr.setAccessible(true);// 设置公有权限
			User user2 = (User)constr.newInstance("小王",12);
			System.out.println(user2.publicMethod());
			System.out.println(user2.toString());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("反射私有属性：---------------------------");
		Object objField = clazz.newInstance();
		Field fieldName = clazz.getDeclaredField("name");
		fieldName.setAccessible(true);// 设置为公有的访问权限
		String name = (String)fieldName.get(objField);
		System.out.println(name);
		
		ReflectClass
	}
}
