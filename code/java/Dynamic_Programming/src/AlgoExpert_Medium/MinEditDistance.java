package AlgoExpert_Medium;

public class MinEditDistance {
	public static void main(String[] args) {
		
		String s1 = "abc" ;
		String s2 = "yabd" ; 
		
		System.out.println(levenshteinDistance(s1, s2));
		
		System.out.println(ld(s1, s2));
		
	}
	
	public static int levenshteinDistance(String str1, String str2) {
	    
		int[][] solution = new int[str1.length()+1][str2.length()+1] ;
		
		for(int i=0 ; i < str2.length()+1 ; i++ ) {
			solution[0][i] = i ; 
		}
		
		for(int i=0 ; i < str1.length()+1 ; i++ ) {
			solution[i][0] = i ; 
		}
		
		return levenshteinDistance(str1, str2, solution) ; 
		
  }
	
	// O(mn) time and O(mn) space. 
	public static int levenshteinDistance(String str1, String str2, int[][] solution){
		
		for(int i=1 ; i<str1.length()+1 ; i++ ) {
		
			for(int j=1; j<str2.length()+1 ; j++) {
	
				if(str1.charAt(i-1) == str2.charAt(j-1) ) {
				solution[i][j] = solution[i-1][j-1] ;			
				} else {				
					solution[i][j]  = 1+ Math.min( solution[i][j-1]  ,Math.min(solution[i-1][j-1], solution[i-1][j]) ); 
				}	
			}
		}
		
		return solution[str1.length()][str2.length()]; 
		
	}
	
	// O(mn) time and O(min(m, n)) space 
	public static int ld (String str1, String str2){ // Can solve this in O(min(m, n)) space. 
		
		// See the AlgoExpert video solution. 
		
		String small = str1.length() < str2.length() ? str1 : str2 ; 
		
		String big = str1.length() >= str2.length() ? str1 : str2; 
		
		int[] evenEdits = new int[small.length()+1] ; 
		int[] oddEdits = new int[small.length()+1]; 
		
		for(int j=0; j<small.length()+1 ; j++) {
			evenEdits[j] = j ; 
		}
		
		int[] currentEdits; 
		int[] previousEdits;
		
		for (int i=1; i<big.length()+1 ; i++) { // O(mn) time, O(min(n, m)) space. 
			
			if(i%2 == 1) {
				currentEdits = oddEdits; 
				previousEdits = evenEdits; 
			} else {
				currentEdits = evenEdits;
				previousEdits = oddEdits; 
			}
			
			currentEdits[0] = i; 
			
			for( int j=1 ; j<small.length()+1 ; j++ ) {
				
				if(big.charAt(i-1) == small.charAt(j-1) ) {
					currentEdits[j] = previousEdits[j-1] ; 
				} else {
					currentEdits[j] = 1 + Math.min(previousEdits[j-1], 
									Math.min(previousEdits[j], currentEdits[j-1] ) ) ;  
				}	
			}	
		}
		
		return big.length() % 2 == 0 ? evenEdits[small.length()] : oddEdits[small.length()] ; 
		
	}

	
	
	
}









