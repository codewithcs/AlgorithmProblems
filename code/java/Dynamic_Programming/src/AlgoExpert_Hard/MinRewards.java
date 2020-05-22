package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MinRewards {
	public static void main(String[] args) {
		int[] array = { 800, 400, 20, 10, 30, 61, 70, 90, 17, 21, 22, 13, 12, 11, 8, 4, 2, 1, 3, 6, 7, 9, 0, 
				68, 55, 67, 57, 60, 51, 661, 50, 65, 53 } ; // +ve and distinct 
		minRewards(array);
	}
	
	public static void minRewards(int[] scores) { // O(n^2) time, O(n) space. 
		int[] values = new int[scores.length] ; 
		Arrays.fill(values, 1); 
		
		for( int i=1 ; i<scores.length ; i++) {	
					
			if(scores[i] > scores[i-1]) {
				values[i] = values[i-1] + 1; 
			} 
			else { // Backtrack only when the previous value is 1. 
				
				values[i] = 1; 
				
				if(values[i-1] == 1) { // Using Math.max() will make this a lot cleaner. can remove this line. 
					
					int j=i; 
					
					while(j > 0 && scores[j] < scores[j-1]  ) {
						values[j-1] = values[j] + 1; // to form a continuous chain 
						j--; 
					}
				} // else we take values[i-1] original value. // Can also assign values[i-1] = Math.max(1+values[i], values[i-1])
				  // but this is not required depending on the given conditions.  
			}
		}
		
		int sum = 0 ; 
		for(int a : values ) {
			sum += a ; 
		}
		
		System.out.println(sum );
	
		for (int a : values) {
			System.out.print(a + ", ");
		}
			
	}
	
	public static void minRewards2(int[] scores) {
	
		int[] rewards = new int[scores.length]; 
		Arrays.fill(rewards, 1);
		
		List<Integer> localMinIdxs = getLocalMinIdxs(scores) ; 
		
		for(Integer localMinIdx: localMinIdxs) {
			expandFromLocalMinIdx(localMinIdx, scores, rewards) ; 
		}
		
		System.out.println(IntStream.of(rewards).sum());
		
	}
	
	public static List<Integer> getLocalMinIdxs(int[] array) {
		List<Integer> localMinIdxs = new ArrayList<>() ; 
		
		if(array.length == 1) { // Impo edge case. 
			localMinIdxs.add(0); 
			return localMinIdxs ; 
		}
		 
		for(int i=0 ; i<array.length ; i++ ) {
			
			if(i==0 && array[i] < array[i+1]) localMinIdxs.add(i) ; 
			
			else if(i==array.length-1 && array[i] < array[i-1]) localMinIdxs.add(i) ;
			
			else if(i==0 || i==array.length -1) continue; // IMP, prevent going to the next line. 
			
			else if(array[i]<array[i+1] && array[i] < array[i-1]) localMinIdxs.add(i) ; 
			
		}
		
		
		return localMinIdxs ; 
	}
	
	
	public static void expandFromLocalMinIdx(int localMinIdx, int[] scores, int[] rewards) {
		
		int leftIdx = localMinIdx - 1; 
		
		while(leftIdx>=0 && scores[leftIdx] > scores[leftIdx+1]) {
			rewards[leftIdx] = Math.max(rewards[leftIdx], rewards[leftIdx+1] + 1) ; 
			leftIdx--; 
		}
		
		int rightIdx = localMinIdx + 1; 
		
		while(rightIdx < scores.length && scores[rightIdx] > scores[rightIdx-1]) {
			rewards[rightIdx] = rewards[rightIdx-1] + 1 ; 
			rightIdx++ ; 
		}
		
	}
	
	
	public static void minRewards3(int[] scores) {
		int[] rewards = new int[scores.length] ; 
		Arrays.fill(rewards, 1) ;
		
		for(int i=1 ; i<scores.length ; i++){			
			if(scores[i] > scores[i-1] ) {
				rewards[i] = rewards[i-1] + 1 ; 
			}			
		}
		
		for(int i=scores.length-2;  i>=0 ; i--) {			
			if(scores[i] > scores[i+1]) {
				rewards[i] = Math.max(1+rewards[i+1], rewards[i]) ;  // Need to do Math.max() in 2nd for-loop
			}			
		}
		
		int sum = 0 ; 
		for (int a: rewards) {
			sum += a ; 
		}
		System.out.println("Minimum Sum is : " + sum);
	}
	
}
