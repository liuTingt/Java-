package ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 
 * @Description 
 *	ʹ��Callable�������߳�
 *	ʵ��Callable�ӿڣ���дcall()�������÷����з���ֵ�������������׳��쳣,ʵ�ֲ�������
 *	1��ʵ��Callable�ӿڣ���дcall()����
 *	2������Callable��ʵ����ʹ��FutureTask����װCallable����
 *	3��ʹ��FutureTask��ΪThread���target�����������߳�
 *	4������FutureTask��get()�������������߳�ִ�н���ķ���ֵ
 *	
 * @author lt
 *
 */
public class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("ִ�С�������");
		return "OK";
	}
	public static void main(String[] args) {
		Callable<String> myCallable = new MyCallable();
		// ����FutureTask����,
		FutureTask<String> task = new FutureTask<String>(myCallable);
		// FutureTask�ǰ�װ������ͨ������Callable��������ͬʱʵ����Future��Runnable�ӿ�
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
