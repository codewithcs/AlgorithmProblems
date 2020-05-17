package AlgoExpert_Medium;

import java.util.Arrays;

public class MinNumberOfCoinsForChange {

	public static void main(String[] args) {
		
		int[] denoms = {2, 4 } ; int n= 7 ; 
		
		System.out.println(minNumberOfCoinsForChange(n, denoms));
		
	}
	
	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		   
		int[] solution = new int[n+1] ; 
		Arrays.fill(solution, 100000); 
		
		solution[0] = 0 ; 
		
		for(int i=1; i<n+1; i++) {
			
			for(int j=0 ; j<denoms.length ; j++) { // iterate over coins array. 
								
				if(i-denoms[j] >= 0 ) {
					solution[i] =  Math.min(solution[i], 1 + solution[i-denoms[j]] ); 
				} 
				
			}
				
		}
		
		if(solution[n] == 100000) {
			return -1 ; 
		}
		
		return solution[n] ; 
		
  }
	
}
