package DP;

import java.util.ArrayList;
import java.util.Arrays; // Max of Continuous Sub Array Sum 
import java.util.List;

public class MaxContinuousSubArray { 

	public static void main(String[] args) {
		
		int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		
		int cache[] = new int[arr.length]; 
		List<Integer> optimalList = new ArrayList<>();
		List<Integer> currentList = new ArrayList<>();

		int answer = maxContinuousSubArrayBottomUp(arr, cache, optimalList); 
		System.out.println("Bottom Up answer: " + answer);
		
		// to build a solution, find the index of answer in cache[]. 

		int index = 0 ; 
		
		for (int i=0 ; i<cache.length; i++) {
			if (cache[i] == answer) {
				index = i; 
			}
		} // O(n) 
		
	   int j=index; optimalList.add(arr[j]) ; 
	   int sum = arr[j] ;
	   System.out.println("Index is : " + index);
	   
		while(j>0) { // O(n) 
						
			if(sum == answer) {
				break; 
			} else {
				sum = sum + arr[j-1] ; 
				optimalList.add(arr[j-1]);
			}
			j--; 
		}
			
		System.out.println(optimalList);
		
		cache = new int[arr.length]; 
		//Arrays.fill(cache, Integer.MIN_VALUE);
		
		maxContinuousSubArrayTopDown(arr, arr.length-1 , cache) ;
		
		Arrays.sort(cache); 
		System.out.println( "Top Down answer: " + cache[cache.length-1] ); 
			
	}
	
	public static int maxContinuousSubArrayTopDown(int[] arr, int size, int[] cache) {
		
		// Base Case 
		if(size ==1 ) {
			cache[size-1] = arr[size-1] ; 
			return cache[size-1] ; 
		}
		
		cache[size-1] = maxContinuousSubArrayTopDown(arr, size-1, cache) ; 
		
		cache[size] = Math.max(arr[size], arr[size] + cache[size-1]) ; 

		return cache[size] ; 
		
	}
	
	// Kadane's Algorithm assumes atleast 1 positive number. 
	public static int maxContinuousSubArrayBottomUp(int[] arr, int[] cache, List<Integer> optimalList) {

		 int globalMax ; 
		 int currentLeft = 0, currentRight = 0 ; 
		 int bestLeft = 0, bestRight = 0 ; 
		
		cache[0] = arr[0] ; 
		globalMax = cache[0] ; 
		
		for ( int i=1 ; i<cache.length ; i++ ) { 		
			
			int max = cache[i-1] + arr[i] ;
			
			
			currentRight = i; 
			
			if( max < arr[i] ) {
				max = arr[i] ; 
				currentLeft = i; 
				currentRight = i; 
			}
			
			if(globalMax <= max) {
				globalMax = max; 
				bestLeft = currentLeft ; 
				bestRight = currentRight;
			}
			
			cache[i] = max; 				 
		}
		
		return globalMax;
		
	}
	
}

/*
 Can Store the solution in another array or a  map. 
*/
