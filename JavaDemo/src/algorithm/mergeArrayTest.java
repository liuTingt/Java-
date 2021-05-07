package algorithm;

public class mergeArrayTest {

	
	public static void main(String[] args) {
		int[] nums1 = {1,3,5,7,9};
		int[] nums2 = {2,4,6};
		int[] nums3 = mergeArray1(nums2, nums1);
		for(int i = 0; i < nums3.length; i++) {
			System.out.print(nums3[i] + "  ");	
		}
		
	}
	
	/**
	 * 两个有序数组从小到大排序，合并两个数组返回一个从小到大有序的数组
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] mergeArray1(int[] nums1, int[] nums2) {
		if (nums1 == null && nums2 == null) return null;
		if(nums1 == null && nums2.length >= 0) return nums2;
		if(nums2 == null && nums1.length >= 0) return nums1;
		
		int len1 = nums1.length, len2 = nums2.length;
		int index1 = 0, index2 = 0, index3 = 0;
		int[] nums3 = new int[nums1.length + nums2.length];
		
		while(index3 < (len1+ len2)) {
			if (index1 == len1) {
				nums3[index3++] = nums2[index2++];
			} else if(index2 == len2) {
				nums3[index3++] = nums1[index1++];
			} else if(nums1[index1] < nums2[index2]) {
				nums3[index3++] = nums1[index1++];
			} else {
				nums3[index3++] = nums2[index2++];
			}
		}
		return nums3;
	}
	
	public static int[] mergeArray2(int[] nums1, int[] nums2) {
		if (nums1 == null && nums2 == null) return null;

		int len1 = nums1.length, len2 = nums2.length;
		int index1 = len1 - 1, index2 = len2 - 1, index3 = len1 + len2 -1;
		int[] nums3 = new int[len1 + len2];
		
		while(index3 >= 0) {
			if(index1 == -1) {
				nums3[index3--] = nums2[index2--];
			} else if(index2 == -1) {
				nums3[index3--] = nums1[index1--];
			} else if(nums1[index1] > nums2[index2]) {
				nums3[index3--] = nums1[index1--];
			} else{
				nums3[index3--] = nums2[index2--];
			}
		}
		return nums3;
	}
}
