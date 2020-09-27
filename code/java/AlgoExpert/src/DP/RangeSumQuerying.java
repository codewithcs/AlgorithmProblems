package DP;

public class RangeSumQuerying {
	
	public static void main(String[] args) {
		int[] arr = { 1, -3, 9, 12, -2, 1, -3, 9, 12, -5, 34, 67 } ;
		
		int[] cache = new int[arr.length]; 
		
		cache[0] = arr[0] ; 
		
		for (int i=1; i<cache.length; i++) {
			cache[i] = cache[i-1] + arr[i] ; 
		}
		
		System.out.println(rangeSumQueryBottomUp(3, 8, cache) ); 
		
	}
	
	public static void rangeSumQueryTopDown() {
		
	}
	
	public static int rangeSumQueryBottomUp(int i, int j, int[] cache) {
		return cache[j] - cache[i-1] ; 
	}
	
}
