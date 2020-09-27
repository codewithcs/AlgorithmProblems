package AlgoExpert_Medium;

public class Kadane {
	public static void main(String[] args) {
		
	}
	
	 public static int kadanesAlgorithm(int[] array) {
		    
		 int[] solution = array.clone();
				
		 int max = solution[0]; 
				
		 for(int i=1 ; i<array.length ; i++ ) {
					
			if( solution[i-1] > 0 ) // only want this if there is a positive change from previous subproblem. 
				solution[i] = array[i] + solution[i-1]; 
			else 
				solution[i] = array[i]; 
			
			if(max < solution[i])
				max = solution[i] ;
		}
				
			return max;  
	}
	

// If arr[i] is +ve or -ve, then having solution[i-1] as positive will improve the answer. 
// If solution[i-1] is -ve then solution[i-1] will worsen the sum, so we take array[i]


// We can also solve it using O(1) space and O(n) time
public static int kadane(int[] array) {
	
		int maxEndingHere = array[0];
		int maxSoFar = array[0] ; 
		
		for(int i=1; i< array.length ; i++) {
			int num = array[i]; 
			maxEndingHere = Math.max(num, maxEndingHere + num) ; 
			maxSoFar = Math.max(maxSoFar, maxEndingHere) ; 
		}
		
		return maxSoFar; 
	
	}

}








