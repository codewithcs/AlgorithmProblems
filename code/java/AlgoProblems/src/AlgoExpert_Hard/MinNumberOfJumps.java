package AlgoExpert_Hard;

import java.util.Arrays;

public class MinNumberOfJumps {
	public static void main(String[] args) {
		int[] array = { 3, 4, 2, 1 ,2, 3, 7, 1, 1, 1, 3 } ; // Positive integers, cannot jump backwards.  
		System.out.println(minNumberOfJumps(array));
		
		int[] cache = new int[array.length]; 
		Arrays.fill(cache, -1); //important step in top down. 
		
		array = new int[] { 3, 4, 2, 1 ,2, 3, 7, 1, 1, 1, 3 } ; 
		cache[array.length-1] = 0; // Base Case 
		System.out.println(minNumberOfJumpsTopDown(0, array[0], array, cache)) ; 
		
		System.out.println(minNumberOfJumpsBottomUp(array));
		
		array = new int[] { 1, 1, 1, 1, 1 } ;
		System.out.println(minNumberOfJumpsOptimal(array));
		
	}
	
	public static int minNumberOfJumps(int[] array) {
				
		if(array.length == 1 || array.length == 0) {
			return 0 ;
		}
		// Ask the interviewer about 0 and single length arrays. 
		
		return minNumberOfJumps(0, array[0], array);
	 }
	
	public static int minNumberOfJumps(int currentIndex, int value, int[] array) {
		
		if(currentIndex >= array.length - 1 ) { // Base Case 
			return 0 ; 
		}  		
		int min = Integer.MAX_VALUE ; 
		
		for(int jump=1 ; jump<=value ; jump++ ) {
			int newIndex = currentIndex + jump;  			
			if(newIndex < array.length ) {
				min = Math.min(min, 1 + minNumberOfJumps(newIndex, array[newIndex], array)) ; 
			}
		}
		
	    return min;
	 }
	
	public static int minNumberOfJumpsTopDown(int currentIndex, int value, int[] array, int[] cache) {
		
		if(currentIndex >array.length - 1 ) {
			return 0 ; 
		}  		
		
		int min = Integer.MAX_VALUE ; 
		
		if(cache[currentIndex] != -1  ) { // Important step in top down approach 
			return cache[currentIndex] ; 
		}
		
		for(int jump=1 ; jump<=value ; jump++ ) {
			int newIndex = currentIndex + jump;  			
			if(newIndex < array.length ) {
				min = Math.min(min, 1 + minNumberOfJumpsTopDown(newIndex, array[newIndex], array, cache)) ; 
			}
		}
		cache[currentIndex] = min ;  // will have to assign value to cache[currentIndex]

	    return cache[currentIndex];
	 }
	
	
	public static int minNumberOfJumpsBottomUp(int[] array) {
		
		int[] cache = new int[array.length] ; 
		cache[array.length-1] = 0; 
			
		for(int i=array.length-2 ; i>=0 ; i-- ) { // Have to start from base case. 
			int value = array[i] ; 		
			int min = Integer.MAX_VALUE ; 
			
			for(int jump=1 ; jump<=value; jump++) {
				int newIndex = i + jump; 
				if( newIndex < array.length) {
					min = Math.min(min, 1 + cache[newIndex]);
					cache[i] = min ; 
				}
			}
		}
		
		
		return cache[0] ; 
	 }
	
	// O(n) time and O(1) space
	public static int minNumberOfJumpsOptimal(int[] array) {
		
		if(array.length == 1) {
			return 0; 
		}
		
		int jumps = 0 ;
		int maxReach = array[0] ; 
		int steps = array[0] ; 
		
		for(int i=1 ; i<array.length-1 ; i++) { // we are going from [2, to array.length-2]
			maxReach = Math.max(maxReach, i + array[i]) ; 
			steps-- ;
			System.out.println("Steps is : " + steps);
			if(steps == 0) {
				jumps++; 
				steps = maxReach - i; 
			}
			System.out.print("i is : " + i + " and MaxReach is : " + maxReach + " and steps is : " + steps);
			System.out.println();
			System.out.println("jumps is: " + jumps);
			System.out.println(); 
			
		}
		return jumps + 1; 
	}

	
}