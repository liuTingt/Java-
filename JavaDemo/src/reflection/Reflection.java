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
			System.out.println("class����-------------------------------");
			System.out.println(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�������й��й��캯����-------------------------------");
		// ��ȡ���캯��
		Constructor<User>[] constructors = clazz.getConstructors();
		for(int i =0; i< constructors.length; i++) {
			System.out.println(constructors[i]);
		}
		System.out.println("�������й��캯����-------------------------------");
		Constructor<User>[] cons = clazz.getDeclaredConstructors();
		for(int i =0; i< cons.length; i++) {
			System.out.println(cons[i]);
		}
		
		System.out.println("public������-------------------------------");
		// ��ȡpublic��������������
		Method[] methods = clazz.getMethods();
		for(int i =0; i< methods.length; i++) {
			System.out.println(methods[i]);
		}
		System.out.println("��������з�����-------------------------------");
		Method m[] = clazz.getDeclaredMethods();
		for(int i =0; i< m.length; i++) {
			System.out.println(m[i]);
		}
		
		System.out.println("public�ֶΣ�-------------------------------");
		// ��ȡpublic�ֶ�
		Field[] fields = clazz.getFields();
		for(int i =0; i< fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println("���������ֶΣ�-------------------------------");
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
		
		System.out.println("��������---------------------");
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
		
		System.out.println("���乫�й��췽��-------------------------------");
		try {
			Constructor<?> constr = clazz.getConstructor(String.class, int.class);
//			constr.setAccessible(true);// ���ù���Ȩ��
			User user2 = (User)constr.newInstance("С��",12);
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
		
		System.out.println("����˽�����ԣ�---------------------------");
		Object objField = clazz.newInstance();
		Field fieldName = clazz.getDeclaredField("name");
		fieldName.setAccessible(true);// ����Ϊ���еķ���Ȩ��
		String name = (String)fieldName.get(objField);
		System.out.println(name);
		
		ReflectClass
	}
}
