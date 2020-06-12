package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumIncreasingSubsequence2 {

	public static void main(String[] args) {
		int[] array = { 10, 15, 4, 5, 11, 14, 31, 25, 31, 23, 25, 31, 50 } ; 
		System.out.println(maxSumIncreasingSubsequence(array));
	}
	
	 public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
		 int[] sequences = new int[array.length] ; 
		 Arrays.fill(sequences, Integer.MIN_VALUE);
		 
		 int[] sums = array.clone(); 
		 int maxSumIdx = 0 ;
		 
		 for(int i=0; i<array.length ; i++) {
			 int currentNum = array[i] ; 
			 
			 for(int j=0; j < i ; j++) {
				 int otherNum = array[j] ; 
				 
				 if(otherNum < currentNum && sums[j] + currentNum >= sums[i]) { // sums[] are global values for their indices. 
					 sums[i] = sums[j] + currentNum ; 	// So no need to create a globalSum variable separately. 
					 sequences[i] = j ; 
				 }
			 }
			 
			 if(sums[i] >= sums[maxSumIdx]) { // Imp Step
				 maxSumIdx = i; 
			 }
		 }

		 for(int g: sums) {
			 System.out.print(g + " : ");
		 }
		 System.out.println();
		 
		 return buildSequence(array, sequences, maxSumIdx, sums[maxSumIdx]) ; 
	 }

	 public static List<List<Integer>> buildSequence(int[] array, int[] sequences, int currentIdx, int sums) {
		 
		 List<List<Integer>> sequence = new ArrayList<>() ; 
		 
		 sequence.add(new ArrayList<>()) ; 
		 sequence.add(new ArrayList<>()) ; 
		 sequence.get(0).add(sums) ; 
		 
		 while(currentIdx != Integer.MIN_VALUE) {
			 sequence.get(1).add(0, array[currentIdx]) ; 
			 currentIdx = sequences[currentIdx]; 
		 }
		 
		 return sequence ; 
	 }
}