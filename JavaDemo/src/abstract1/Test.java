package abstract1;

public class Test {

	public static void main(String[] args) {
		Object
	}
	
	public static int[] arrays(int[] a1, int[] b1, int a, int b) {
		int[] c = new int[a+b];
		for(int i = 0,j=0;i<a1.length;) {
			if(a1[i] < b1[j]) {
				c[i+j]=a1[i];
				i++;
			} else {
				c[i+j]=b1[j];
				j++;
			}
		}
		
		return c;
	}
}
