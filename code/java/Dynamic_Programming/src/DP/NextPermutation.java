package DP;

import java.util.Arrays;

public class NextPermutation {

	public static void main(String[] args) {
	
		// Generate next permutation.  
		
		int permutation[] =  { 6, 2, 1, 5, 4, 3, 0 }; 
		
		int permutation2[] = { 0, 1, 2, 4, 3, 5, 6 } ;
		
		int permutation3 [] = {1, 2, 6, 5, 4, 3, 0 }; 
		
		int permutation4 [] = {1, 2, 3 };
		
		int permutation5 [] = {3, 7, 6, 9 }; 
		
		int permutation6[] = { 3, 2, 1, 4, 6, 7, 8} ; 
		
		int permutation7[] = { 3, 2, 1, 4, 6, 7, 8, 3, 2, 67, 4 , 2} ; 
		
		nextPermutation(permutation) ; // Expected answer: 6, 2, 3, 0, 1, 4, 5 
		
		nextPermutation(permutation2) ; // 0, 1, 3, 2, 4, 5, 6
		
		nextPermutation(permutation3) ;  // 1, 3, 0, 2, 4, 5, 6
		
		nextPermutation(permutation4) ; // 1, 3, 2 
	
		nextPermutation(permutation5) ; // 6, 3, 7, 9

		nextPermutation(permutation6) ; // 4, 1, 2, 3, 6, 7, 8
		
		nextPermutation(permutation7) ; // 3, 2, 1, 4, 6, 7, 8, 3, 4, 2, 2, 67

	}
	
	public static void nextPermutation(int[] permutation) {
		
		int lastIndex = permutation.length - 1; 
		
		boolean hasDecreasing = false ; // if the array has some decreasing sequence. 
		
		int start = -1; 
		int count = 0; 
		boolean firstTime = true ; 
		int end = -1; 
		
		for( int i = lastIndex ; i >=1 ; i-- ) { // Find indices of decreasing section
			
			if( permutation[i] <= permutation[i-1] ) { 
				
				start = i-1; 
				count = 1; 
				hasDecreasing = true; 
				
				if(firstTime) {
					end = i ; 
					firstTime = false; 
				} 
				
			} else if(count !=0 ) {
				break ; 
			}
		}
		// When first time we enter the if block, then that is the start of the decreasing sequence. 
		
			int indexToSwapWith = lastIndex;  
			
			if(hasDecreasing) {
			
				if(start == 0 && end == lastIndex) { // When the string is fully decreasing 
					Arrays.sort(permutation, start, end+1);
				}
				
				else if(start==0) { // swap start and end+1 index. When decreasing section is at leftmost part
					int temp = permutation[start] ;   
					permutation[start] = permutation[end+1] ;
					permutation[end+1] = temp;
					Arrays.sort(permutation, start+1, end+1); 
				} 
							
				else {
					
					for(int j = end ; j>=start ; j-- ) {
						
						if(permutation[start-1] < permutation[j]) { // Since it is a decreasing sequence.
							indexToSwapWith = j; 
							break; 
						} 
					}
							
					int temp = permutation[start-1] ;   
					permutation[start-1] = 	permutation[indexToSwapWith] ;
					permutation[indexToSwapWith] = temp;
					
					Arrays.sort(permutation, start, end+1);
			}
			
			
			
			}
			else { // just swap last and second last  // Case when the string is increasing 
				int temp = permutation[lastIndex-1] ;   
				permutation[lastIndex-1] = 	permutation[lastIndex] ;
				permutation[lastIndex] = temp;
			}	
		 
		
		// Print the next permutation. 
		for(int i=0 ; i<permutation.length ; i++) {
			System.out.print(permutation[i] + " ");
		}
		
		System.out.println();
	}
	
}
