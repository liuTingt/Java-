package fibonacci;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 使用递归和迭代两种方式表达斐波那契数列
 * 
 * 斐波那契数列:从第3项开始，每一项都等于前两项的和，F(0)=0,F(1)=1,F(n)=F(n-1)+F(n-2)
 * 
 * 递归：一个函数调用本身。将大问题化为相同结构的小问题，从待求解的问题出发，一直分解到已经已知答案的最小问题为止，
 * 		再逐级返回，从而得到大问题的解
 * 
 * 迭代：迭代是从已知值出发，通过递推式，不断更新变量新值，一直到能够解决要求的问题为止
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
		 * 输入整数n，输出斐波那契数列的第n项
		 */
		System.out.println("------------------start Time:"+System.currentTimeMillis());
		System.out.println(recursion(24));
		System.out.println("------------------end Time:"+System.currentTimeMillis());
		
		System.out.println("------------------start Time:"+System.currentTimeMillis());
		System.out.println(iteration(24));
		System.out.println("------------------end Time:"+System.currentTimeMillis());
	}
	
	/**
	 * 使用递归方式
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
	 * 使用迭代方式：从已知值出发，
	 * @Description 
	 *
	 * @param n
	 * @return
	 */
	public static int iteration(int n) {
		if(n < 2) return n;
		// 从已知值出发
		int f = 0,g = 1,result = 0;
		for(int i = 2; i <= n; i++) {
			// 不断更新变量新值
			result = f + g;
			f = g;
			g = result;
		}
		// 找到问题新解
		return result;
	}
	
}
