package ThreadTest;

import java.util.concurrent.TimeUnit;

/***
 * 
 * 面试题：模拟银行账户问题
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 行不行？
 * 
 * 容易产生脏读问题
 * 
 * 因为加锁和不加锁的方法是可以同时运行的，所以不加锁方法可能会读取脏数据
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
		new Thread(()->account.set("小王", 100)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(account.getBalance("小王"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(account.getBalance("小王"));
	}
}
