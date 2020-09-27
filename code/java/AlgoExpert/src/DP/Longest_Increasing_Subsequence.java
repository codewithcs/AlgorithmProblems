package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Cases: Assume non empty. 
 	1. Fully Decreasing sequence => Length = 1 
 	2. Fully Increasing sequence => Length = Number of elements in the array. 
 	3. Some part is increasing.  
 
 
 */


public class Longest_Increasing_Subsequence {
	
	
	 /**
     * Returns index in T for ceiling of s; Tushar Roy's solution
     */
    private static int ceilIndex(int input[], int T[], int end, int s){
        int start = 0;
        int middle;
        int len = end;
        while(start <= end){
            middle = (start + end)/2;
            if(middle < len && input[T[middle]] < s && s <= input[T[middle+1]]){
                return middle+1;
            }else if(input[T[middle]] < s){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }
    
    public static int longestIncreasingSubSequence(int input[]){
    	
        int T[] = new int[input.length];
        int R[] = new int[input.length];
        
        for(int i=0; i < R.length ; i++) {
            R[i] = -1;
        }
        
        T[0] = 0;
        
        int len = 0;
        
        for(int i=1; i < input.length; i++){
            
        	if(input[T[0]] > input[i]){ //if input[i] is less than 0th value of T then replace it there.
                T[0] = i;
            } 
        	
        	else if(input[T[len]] < input[i]){ //if input[i] is greater than last value of T then append it in T
                len++;
                T[len] = i;
                R[T[len]] = T[len-1];               
            } 
            
            else{ //do a binary search to find ceiling of input[i] and put it there.
                int index = ceilIndex(input, T, len,input[i]);
                T[index] = i;
                R[T[index]] = T[index-1];
            }
        }

        //this prints increasing subsequence in reverse order.
        System.out.print("Longest increasing subsequence ");
        
        int index = T[len];
        
        while(index != -1) {
            System.out.print(input[index] + " ");
            index = R[index];
        }

        System.out.println();
        
        return len+1;
    }
	
	
	public static void main(String[] args) {
	
		int[] sequence = { 7, 4, 3, -7, 11, 0, 17, 1, 2, 14, 22} ; 
		
		lis_loop(sequence);
			
		lis_bottomUp(sequence); 
		
		int[] solution = new int[sequence.length] ;
		Arrays.fill(solution, 1);
		System.out.println( "[Top Down] Length of lis is : " + lis_topDown(sequence, solution) ) ;
		
		int[] ar = {10, 5, 8, 3, 9, 4, 12, 11};
		
		System.out.println(longestIncreasingSubSequence(ar)); // giving answer in the reverse order. 
		
	}
	
	
	public static void lis_loop(int[] sequence) { // Converting Recursion into iteration; Try this someday later. 
		
		int max = 1 ; int currentLength = 1; 
		
		for(int i=0; i<sequence.length; i++) {
			
			currentLength = 1; 
			
			for(int j=i+1; j<sequence.length; j++) {
				
			//	System.out.println("sequence[j]  is : "  + sequence[j] );

				if(sequence[j] > sequence[i] ) {
					currentLength += 1 ; 
				}
				
			}
			
			//System.out.println("current length is : " + currentLength );
			
			if(max < currentLength) {
				max = currentLength; 
			}
			
		}
		
		
//		System.out.println("Length of longest increasing subsequence is : " + max);
		
	}
	
	public static void lis_nlogn () {
		
	}
	
	public static int lis_topDown (int[] sequence, int[] solution) {
		
		if(sequence.length == 0 ) {
			return 0 ; 
		}
		
		if(sequence.length == 1) {
			return 1 ; 
		}
		
		if(solution[sequence.length-1] != 1 ) {
			return solution[sequence.length-1] ; 
		}
		
		
		for(int i=1 ; i<sequence.length; i++) { // Choice 
			
			int max = 1 ; 
			
			for(int j=i-1; j>=0 ; j--) {
				
				if(sequence[i] > sequence[j]) { // Constraints 
					max = Math.max(max, 1 + lis_topDown(Arrays.copyOfRange(sequence, 0, j+1), solution ) ) ;  
				}
				
			}
			
			solution[solution.length-1] = max;  // Goal
		}
		
		
		return solution[solution.length-1] ; 
	}

	public static void lis_bottomUp (int[] sequence) {
	
		int[] solution = new int[sequence.length]; 
		Arrays.fill(solution, 1);
		
		for (int i=1 ; i<sequence.length ; i++) {
			
			int	max = 1; 
			
			for(int j=i-1 ; j>=0 ; j--) {
				
				if(sequence[j] < sequence[i]) {
					max = Math.max( max, solution[j] + 1 ) ; // build from smaller subproblem.
				}
				
			}
			
			solution[i] = max ; 
			System.out.println("solution["+i+ "] : is "+solution[i]);
			
		}
		
		System.out.println("[Bottom Up] Length of lis is : " + solution[sequence.length-1]);
		
	}

}
