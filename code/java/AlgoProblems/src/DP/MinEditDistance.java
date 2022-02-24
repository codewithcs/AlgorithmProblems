package DP;

public class MinEditDistance {
	
	public static void main(String[] args) { 
		// Minimum number of operations required to convert a string into another string using minimum number of Replace, Insert and Delete operations
		// Assuming each of these 3 operations count as 1 unit. 
		
		char A[] = {'b', 'e', 'n', 'y', 'a', 'm'} ; 
		char B[] = {'e', 'p', 'h', 'r', 'e', 'm' } ; 
		
		
		int[][] solution = new int[A.length+1][B.length+1] ; 
	
		solution[0][0] = 0; 
		
		for(int i=1 ; i<B.length; i++) { // Insertions
			solution[0][i] = solution[0][i-1] + 1 ;  
		}
		for(int i=1 ; i<A.length; i++) { // Deletions
			solution[i][0] = solution[i-1][0] + 1 ;  
		}
		
		System.out.println("Bottom Up: " + bottomUp (A, B, solution) );
		
		
		String A1 = "benyam" ; 
		String B1 = "ephrem" ; 
		
		int[][] s = new int[A1.length()][B1.length()] ; 
		
		for(int i=0 ; i<A1.length(); i++) {
			for(int j=0 ; j<B1.length(); j++) {
				s[i][j] = -1; 
			}
		} 
		
		System.out.println("Top Down: " + topDown(A1, B1, s ));
	}
	
	public static int bottomUp(char[] A, char[] B, int[][] solution) {
		
		for(int i=1; i<A.length+1; i++) {
			for(int j=1; j<B.length+1; j++) {
				if(A[i-1] == B[j-1]) {
					solution[i][j] = solution[i-1][j-1]; 
				} else {
					solution[i][j] = Math.min(solution[i-1][j-1], Math.min(solution[i][j-1], solution[i-1][j] ) ) + 1; 
				}
			} 
		}
	
		return solution[A.length-1][B.length-1] ; 
	}
	
	public static int topDown( String A, String B, int[][] solution ) {  // Convert A --> B 
		
		if(A.length() == 0) {
			return B.length(); 
		}
		if (B.length() == 0 ) {
			return A.length(); 
		}
		
		if (solution[A.length()-1][B.length()-1] != -1 ) {
			return solution[A.length()-1][B.length()-1] ; 
		}
		
        int min = -1; 
         
		if(A.charAt(A.length()-1) == B.charAt(B.length()-1)) {
			return topDown(A.substring(0, A.length()-1 ) , B.substring(0, B.length()-1 ) , solution ) ; 
		} else {
		 
		// Try Replacement 
		int a = topDown(A.substring(0, A.length()-1 ) , B.substring(0, B.length()-1 ) , solution ) + 1; 
		
		// Try Deletion
		int b  = 1 + topDown(A.substring(0, A.length()-1 ) , B.substring(0, B.length() ) , solution )  ;  // Logic: First delete and then transform. 
				
		// Try Insertion
		int c = topDown(A.substring(0, A.length() ) , B.substring(0, B.length()-1 ) , solution ) + 1 ; 
		
		min = Math.min( Math.min(a, b) , c ) ; 
		
		solution[A.length()-1][B.length()-1] = min ; 
		
		}
		
		return min; 
	}
	
	
}


/*
 What if the cost of these operations is different ?  
 
 With Levenshtein distance, cost of substitutions is 2. 
 
 */


