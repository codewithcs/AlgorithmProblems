package AlgoExpert_Medium;

public class MonotonicArray {

	public static boolean isMonotonic(int[] array) {
	    // Write your code here.
			
			int count = 1 ; 
			
			// Case 1.
			for(int i=1 ; i<array.length; i++) {					
					if(count == i && array[i] >= array[i-1])  {
						count ++; 
					}			
				}
			
			if(count == array.length){
				return true; 
			}
			
			// Case 2
			count =1 ;  
			
			for(int i=1 ; i<array.length; i++) {					
					if(count == i && array[i] <= array[i-1])  {
						count ++; 
					}			
				}
	 			
		
			if(count == array.length){
					return true; 
				}
			
	    return false;
	    
	  } 
	
	// O(n) time, O(1) space 
	public static boolean isMonotonic2(int[] array) {
		
		if(array.length <= 2 )  return true ; 
		
		int direction = array[1] - array[0] ; 
		
		for(int i= 2; i<array.length; i++) {
			if(direction ==0) {
				direction = array[i] - array[i-1] ; 
				continue; 
			}
			
			if(breaksDirection(direction, array[i-1], array[i])) {
				return false; 
			}			
		}
		return true; 
	}

	public static boolean breaksDirection(int direction, int previous, int current) {
		int difference = current - previous ; 
		
		if(direction>0) return difference < 0 ; 
		
		return difference > 0 ; 
		
	}
	
	public static boolean isMonotonic3(int[] array) {
		
		boolean isNonDecreasing = true ; 
		boolean isNonIncreasing = true ; 
		
		for ( int i=1 ; i<array.length ; i++) {
			if(array[i] < array[i-1]) {
				isNonDecreasing = false; 
			}
			
			if(array[i] > array[i-1]) { // avoiding <= 
				isNonIncreasing = false ; 
			}
		}
		
		return isNonDecreasing || isNonIncreasing ; 	
		
	}
	
}