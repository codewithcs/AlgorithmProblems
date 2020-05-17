package AlgoExpert_Medium;

public class MaxSubsetSumNoAdjacent {

	public static void main(String[] args) {
	
		int[] array = { 75, 105, 120, 75, 95, 135} ; // array of +ve integers. 
		
		System.out.println( maxSubsetSumNoAdjacent(array) );

	}
	
	// Maximum sum of non-adjacent elements in the array. 
	
	public static int maxSubsetSumNoAdjacent(int[] array) { // bottom up. 
		  
			int[] solution = new int[array.length] ;  // This approach takes O(n^2) 
			 
			solution[0] = array[0]; 
			
			for(int i=1 ; i< array.length; i++ ) {
				
				for( int j=i-2 ; j >=0 ; j-- ) {
					solution[i] = Math.max(array[i], solution[j] + array[i]) ;
				}
				
				solution[i] = Math.max(solution[i] , solution[i-1]) ;
				
			}
			
			for(int i=0 ; i<solution.length ; i++) {
				System.out.println("solution["+ i + "] : " + solution[i]);
			}
					
			return solution[array.length-1] ;
		
		}
	
	// In top down, fill the cache initially with the array elements.  
	
	public static int maxSubsetSumNoAdjacent2(int[] array) { // O(n)
		
				if( array.length == 0 ) {
					return 0 ; 
				} else if (array.length == 1) {
					return array[0]; 
				}
			
			    int[] solution = array.clone(); // Can solve this in O(n) if all the elements of the array are positive.  

			    solution[1] = Math.max(array[0], array[1]) ;
				
				for(int i=2 ; i< array.length; i++ ) {
				
					solution[i] = Math.max(solution[i-1], solution[i-2] + array[i]) ;
					
					// Important case. 
				}
				
				
				return solution[array.length-1] ;
	  }
	
	// Can also solve this in O(1) space. 
	public static int maxSubsetSumNoAdjacentO1Space(int[] array) {
		
		if( array.length == 0 ) {
			return 0 ; 
		} else if (array.length == 1) {
			return array[0]; 
		}
	
		int second = array[0]; 
		int first = Math.max(array[0], array[1]) ; 
				
		for(int i=2 ; i< array.length; i++ ) {
			
			int current = Math.max(second + array[i], first ) ; 
			second = first ;
			first = current; 
			
		}
		
		return first ; 
	}
	
	
	
	
	// If the numbers are also negative, then we will need to add solution[i] in the max() function as well. 

	
}
