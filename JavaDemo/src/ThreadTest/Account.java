package ThreadTest;

import java.util.concurrent.TimeUnit;

/***
 * 
 * �����⣺ģ�������˻�����
 * ��ҵ��д��������
 * ��ҵ�������������
 * �в��У�
 * 
 * ���ײ����������
 * 
 * ��Ϊ�����Ͳ������ķ����ǿ���ͬʱ���еģ����Բ������������ܻ��ȡ������
 * @Description 
 *
 * @author lt
 *
 */
public class Account {

	private String name;
	private double balance;
	
	public synchronized void set(String name, double balance) {
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}
	
	public /* synchronized */ double getBalance(String name) {
		return this.balance;
	}
	
	public static void main(String[] args) {
		Account account = new Account();
		new Thread(()->account.set("С��", 100)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(account.getBalance("С��"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(account.getBalance("С��"));
	}
}
