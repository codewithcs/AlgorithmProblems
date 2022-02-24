package DP;

import java.util.Arrays;

public class DecodeString {
	
	// total ways to decode a string. 
	
	public static void main(String[] args) {
		System.out.println( numDecodings("124356") ); 
	}
	
	
	public static int numDecodings(String s) {
	    int[] dp = new int[s.length()];
	    Arrays.fill(dp, -1);

	    return numDecodings(s, 0, dp);
	  }

	  public static int numDecodings(String s, int decodePointer, int[] dp) {
		  
	    if (decodePointer >= s.length()) {
	      return 1; // "" is a valid decomposition
	    }

	    // Subproblem already solved and has a value
	    if (dp[decodePointer] > -1) {
	      return dp[decodePointer];
	    }

	    int totalDecompositions = 0;
	    
	    for (int i = 1; i <= 2; i++) {
	    	
	      if (decodePointer + i <= s.length()) {
	    	  
	        String snippet = s.substring(decodePointer, decodePointer + i); 
	        // In substring(), beginIndex is inclusive while endIndex is exclusive. 
	        
	        System.out.println("snippet: " + snippet);
	        
	        System.out.println();
	        
	        if (isValid(snippet)) {
	          totalDecompositions += numDecodings(s, decodePointer + i, dp);
	        }
	        
	      }
	      
	    }

	    // Record subproblem answer to decompositions from (decodePointer)...(s.length - 1)
	    dp[decodePointer] = totalDecompositions;

	    return dp[decodePointer];
	    
	  }

	  public static boolean isValid(String s) {
		  
	    if (s.length() == 0 || s.charAt(0) == '0') {
	      return false;
	    } 

	    int value = Integer.parseInt(s);

	    return value >= 1 && value <= 26;
	    
	  }
	
}
