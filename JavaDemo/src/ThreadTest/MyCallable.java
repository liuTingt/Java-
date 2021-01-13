package ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 
 * @Description 
 *	使用Callable来创建线程
 *	实现Callable接口，重写call()方法，该方法有返回值，并可以声明抛出异常,实现步骤如下
 *	1、实现Callable接口，重写call()方法
 *	2、创建Callable的实例，使用FutureTask来包装Callable对象
 *	3、使用FutureTask做为Thread类的target创建并启动线程
 *	4、调用FutureTask的get()方法，返回子线程执行结果的返回值
 *	
 * @author lt
 *
 */
public class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("执行。。。。");
		return "OK";
	}
	public static void main(String[] args) {
		Callable<String> myCallable = new MyCallable();
		// 创建FutureTask对象,
		FutureTask<String> task = new FutureTask<String>(myCallable);
		// FutureTask是包装器，它通过接收Callable来创建，同时实现了Future和Runnable接口
		Thread t = new Thread(task);
		t.start();
		t.yield();
		try {
			String result = task.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
