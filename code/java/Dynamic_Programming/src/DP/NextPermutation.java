package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextPermutation {

	public static void main(String[] args) {
	
		// Generate next permutation.  
		
		int permutation[] =  { 6, 2, 1, 5, 4, 3, 0 }; 
		
		int permutation2[] = { 0, 1, 2, 4, 3, 5, 6 } ;
		
		int permutation3 [] = {1, 2, 6, 5, 4, 3, 0 };  
		
		nextPermutation(permutation) ; 
		
		nextPermutation(permutation2) ;
		
		nextPermutation(permutation3) ; 
	}
	
	public static void nextPermutation(int[] permutation) {
		
		int length = permutation.length;
		
		int lastIndex = length - 1; 
		
		List<Integer> list = new ArrayList<>() ; 
 		
		list.add(permutation[lastIndex]) ; 
		boolean hasDecreasing = false ; // if the array has some decreasing sequence. 
		
		for( int i = lastIndex ; i >=1 ; i-- ) {
			
			if( permutation[i] <= permutation[i-1] ) {
				hasDecreasing = true; 
				continue; 
			}
			
			int indexToSwapWith = lastIndex;  // in case there was no decreasing sequence, then swap with last index.
			
			for(int j = lastIndex ; j>=i ; j-- ) {
				if(permutation[i-1] < permutation[j]) {
					indexToSwapWith = j; 
					break; 
				} // have to swap with a number just greater than permutation[i-1]
			}
			
			int temp = permutation[i-1] ;   
			permutation[i-1] = 	permutation[indexToSwapWith] ;
			permutation[indexToSwapWith] = temp;
			
			if(hasDecreasing)
			Arrays.sort(permutation, i, permutation.length);
			
			break; 
			
		}
		
		// Print the next permutation. 
		for(int i=0 ; i<permutation.length ; i++) {
			System.out.print(permutation[i] + " ");
		}
		
		System.out.println();
	}
	
}
