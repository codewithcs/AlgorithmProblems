package DP;

public class MaxSumRectangle {

	
	public static void main(String[] args) {
	
		int[][] rectangle  = new int[][] {
			{6, -5, -7, 4, -4}  , 
			{-9, 3, -6, 5, 2}   , 
			{-10, 4,  7, -6, 3} , 
			{-8, 9, -3, 3, -7}	
		};
		
		int cache[] = new int[rectangle.length]; 
		
		//maxSumRectangleTopDown(rectangle, cache) ; 
		
		maxSumRectangleBottomUp(rectangle, cache) ; 
		
	}
	
	public static void maxSumRectangleTopDown(int[][] rectangle, int[] cache) {
		
	}
	
	public static void maxSumRectangleBottomUp(int[][] rectangle, int[] cache){
		
		int maxSum =0, currentSum =0 ; 
		int maxLeft=0, maxRight=0 ; 
		int maxUp=0, maxDown=0; 
		int currentUp = 0;   
		
		// Exploring all subproblems. 
		
		for (int left=0 ; left<5 ; left++) {	
			
			for(int i=0 ; i<4 ; i++ ) { // Set it to 0 when we right pointer finishes it's iteration.  
				cache[i] = 0; 
			}
			
			for(int right= left ; right< 5; right++ ) {
																			
				for (int i = 0 ; i <= 4 ; i++ ) { 
					cache[i] = cache[i] + rectangle[i][right] ; 
				}
				
				// Now apply Kadane's algorithm on cache[]		
				currentSum = 0; currentUp = 0 ; 
				
				for (int k=0 ; k<4 ; k++ ) {
					
					currentSum = currentSum + cache[k] ; 
					
					if (currentSum < 0 ) { // Confirm about this ? 
						currentSum = 0; 
					    currentUp = k+1; 
					} 
					
					if( maxSum < currentSum ) {
						maxSum = currentSum ; 
						maxLeft = left ; 
						maxRight = right; 
						maxUp = currentUp;
						maxDown = k ;  
					}
					
				}
				
			}
			
		}
		
		System.out.println(maxLeft + " " + maxRight + " " + maxUp + " " + maxDown + " " + maxSum );
		
	}
	
}
