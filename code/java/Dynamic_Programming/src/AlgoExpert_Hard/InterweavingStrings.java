package AlgoExpert_Hard;

public class InterweavingStrings {

	public static void main(String[] args) {
		double one = System.nanoTime() ;
		System.out.println(interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjob"));
		double two = System.nanoTime() ; 
		System.out.println("Time is : " + (two-one));
		
		one = System.nanoTime() ;
		System.out.println(interweavingStringsTopDown("algoexpert", "your-dream-job", "your-algodream-expertjob"));
		two = System.nanoTime() ;
		System.out.println("Top Down Time is : " + (two-one));

		one = System.nanoTime() ;
		//System.out.println(interweavingStringsBottomUp("algoexpert", "your-dream-job", "your-algodream-expertjob"));
		two = System.nanoTime() ;
		System.out.println("Bottom Up Time is : " + (two-one));
	}
	
	// Method 1: 
	public static boolean interweavingStrings(String one, String two, String three) {

		if(one.isEmpty() && two.isEmpty() && three.isEmpty()) { // Case 1
			return true ; 
		}
		
		else if( three.isEmpty()) { // Covers 3 cases
			return false;
		}
		
		else if(one.isEmpty() && two.isEmpty() && !three.isEmpty()) { // Cover 1 case
			return false ; 
		}
		else if(one.isEmpty()) {
			
			if(two.charAt(0) == three.charAt(0)) {
				return interweavingStrings(one, two.substring(1, two.length()), three.substring(1, three.length())  );  
			} else {
				return false ; 
			}
			
		} else if(two.isEmpty()) {
			
			if(one.charAt(0) == three.charAt(0)) {
				return interweavingStrings(one.substring(1, one.length()), two, three.substring(1, three.length())  );  
			} else {
				return false ;
			}
			
		} else {
			
			boolean r1 = false ; boolean r2 = false;
			if(two.charAt(0) == three.charAt(0)) {
				r1 = interweavingStrings(one.substring(0, one.length()), two.substring(1, two.length()), three.substring(1, three.length()) );
				if(r1) {
					return true ; 
				}
				
			} 
			
			if(one.charAt(0) == three.charAt(0)) {
				r2 = interweavingStrings(one.substring(1, one.length()), two.substring(0, two.length()), three.substring(1, three.length()) );  
			} 
			
			// r1 can be true and r2 can be false ; That is why we have to return the OR operation. 
			// And if r1 is true then we return true without going to the next recursive call. 
			
			return r1 || r2 ; 
		}
		
	}
	
	// Method 2: Uses Recursion, Cleaner as compared to first solution. 
	public static boolean interweavingStringsRecursion2(String one, String two, String three) {
		if(three.length() != one.length() + two.length()) { // Handled 3 cases here.   
			return false ;
		}
		
		return areInterwoven(one, two, three, 0, 0) ; 
	}
	
	public static boolean areInterwoven(String one, String two, String three, int i, int j) {
		int k = i + j; 
		
		if( k == three.length()) { // Reached the end. 
			return true; 
		}
		
		if(i < one.length() && one.charAt(i) == three.charAt(k)) {
			if(areInterwoven(one, two, three, i+1, j)) {
				return true ;
			}
		}
		
		if(j < two.length() && two.charAt(j) == three.charAt(k)) {
			return areInterwoven(one, two, three, i, j+1) ; 
		}
		
		// We are incrementing i and j just by 1, so k increments by only 1 when we do i+j. 
		// We do further recursive calls only when there is some match. 
		
		return false; 
	}
	
	// Method 3: 
	public static boolean interweavingStringsTopDown(String one, String two, String three) {
		if(three.length() != one.length() + two.length() ) { // covered 4 cases here. 
			return false ; 
		}
		
		Boolean[][] cache = new Boolean[one.length()+1][two.length()+1]; 
		return areInterwovenTopDown(one, two, three, 0, 0, cache) ; 
	}
	
	public static boolean areInterwovenTopDown(String one, String two, String three, int i, int j, Boolean[][] cache) {
		if(cache[i][j] != null) {
			return cache[i][j] ; 
		}
		
		int k = i+j ; 
		
		if(k==three.length() ) {
			return true ; 
		}
		
		if( i < one.length() && one.charAt(i) == three.charAt(k) ) {
			cache[i][j] = areInterwovenTopDown(one, two, three, i+1, j, cache); 
			if(cache[i][j]) { 
				return true ; 
			}
		}
		
		if( j < two.length() && two.charAt(j) == three.charAt(k)) {
			boolean result = areInterwovenTopDown(one, two, three, i, j+1, cache) ; 
			cache[i][j] = result ; 
			return result; 
		}
		
		cache[i][j] = false ; 
		return false ; 	
	}	
	
}