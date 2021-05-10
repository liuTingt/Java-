package fibonacci;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ʹ�õݹ�͵������ַ�ʽ���쳲���������
 * 
 * 쳲���������:�ӵ�3�ʼ��ÿһ�����ǰ����ĺͣ�F(0)=0,F(1)=1,F(n)=F(n-1)+F(n-2)
 * 
 * �ݹ飺һ���������ñ����������⻯Ϊ��ͬ�ṹ��С���⣬�Ӵ��������������һֱ�ֽ⵽�Ѿ���֪�𰸵���С����Ϊֹ��
 * 		���𼶷��أ��Ӷ��õ�������Ľ�
 * 
 * �����������Ǵ���ֵ֪������ͨ������ʽ�����ϸ��±�����ֵ��һֱ���ܹ����Ҫ�������Ϊֹ
 * 
 * 
 * 
 * @Description 
 *
 * @author lt
 *
 */
public class FibonacciSequence {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		Deque<Integer> deque = new ArrayDeque<Integer>();
		Deque<Integer> deque2 = new ConcurrentLinkedDeque<Integer>();
		List<Integer> s = new ArrayList<>();
		
		
		boolean b1 = true,b2 = true;
		while(!b1 && !b2) {
			System.out.println("ttttttttttt");
		}
		
		/**
		 * ��������n�����쳲��������еĵ�n��
		 */
		System.out.println("------------------start Time:"+System.currentTimeMillis());
		System.out.println(recursion(24));
		System.out.println("------------------end Time:"+System.currentTimeMillis());
		
		System.out.println("------------------start Time:"+System.currentTimeMillis());
		System.out.println(iteration(24));
		System.out.println("------------------end Time:"+System.currentTimeMillis());
	}
	
	/**
	 * ʹ�õݹ鷽ʽ
	 * @Description 
	 *
	 * @param n
	 * @return
	 */
	public static int recursion(int n) {
		if(n==0) return 0;
		if(n == 1) return 1;
		
		return recursion(n-1) + recursion(n-2);
	}
	
	/**
	 * ʹ�õ�����ʽ������ֵ֪������
	 * @Description 
	 *
	 * @param n
	 * @return
	 */
	public static int iteration(int n) {
		if(n < 2) return n;
		// ����ֵ֪����
		int f = 0,g = 1,result = 0;
		for(int i = 2; i <= n; i++) {
			// ���ϸ��±�����ֵ
			result = f + g;
			f = g;
			g = result;
		}
		// �ҵ������½�
		return result;
	}
	
}
