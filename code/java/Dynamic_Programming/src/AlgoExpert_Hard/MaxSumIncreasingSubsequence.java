package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumIncreasingSubsequence {

	public static void main(String[] args) {
		int[] array = { 10, 70, 20, 30, 60, 11, 30 } ; 
		System.out.println(maxSumIncreasingSubsequence(array));
	}
	
	 public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
		 
		 List<List<Integer>> list = new ArrayList<>() ; 
		 
		 int[] sums = array.clone() ; // stores the global sums. 
		 List<Integer> elements = new ArrayList<>(); 
		 int[] sequence = new int[array.length]; 
		 Arrays.fill(sequence, Integer.MIN_VALUE); 
		 		 
		 for(int i=1 ; i<array.length ; i++) {
			 int globalSum = array[i];
			 
			 for(int j=0 ; j<i ; j++) {
				 int currentSum = array[i] ; 

				 if(array[j] < array[i] && sums[j] > 0 ) {
					currentSum += sums[j] ; 
				 }
				
				 if(currentSum > globalSum) {
					globalSum = currentSum ; 
					sums[i] = currentSum ; 
					sequence[i] = j; 
				 }
			 }
		 }
		 
		 int largest = array[0] ; // O(n) 
		 int globalIndex = 0; // to handle the case of single element. 
		 for(int i=1 ; i<array.length; i++ ) {
			 if(sums[i] > largest) {
				 largest = sums[i] ; 
				 globalIndex = i; 
			 }
		 }
		 
		 System.out.println();
		 
		 list.add(Arrays.asList(sums[globalIndex])) ; 
		 
		 for(int i=globalIndex;  i>=0 ;  ) {
			elements.add(0, array[i]) ; 
			i = sequence[i] ; 
		 }
		 
		 list.add(elements) ; 
		 
		 return list; 
	 }
	
}
