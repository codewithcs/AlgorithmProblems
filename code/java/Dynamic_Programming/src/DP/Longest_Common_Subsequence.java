package DP;

import java.util.Arrays;

public class Longest_Common_Subsequence {
	public static void main(String[] args) {
	
		String a = "aab" ; 
		String b = "azb" ; 
		
		int[][] solution1 = new int[a.length()+1][b.length()+1] ;
		for(int[] x : solution1) {
			Arrays.fill(x, -1);
		}
		
		for(int i=0 ; i<a.length(); i++) {
			solution1[0][i] = 0;
			solution1[i][0] = 0; 
		}
		
		lcs_bottomUp(a, b, solution1);
		
		int[][] solution2 = new int[a.length()][b.length()] ; // dont need to store the base cases in the cache. 
		for(int[] x : solution2) {
			Arrays.fill(x, -1);
		}
		
		
		System.out.println("[Top Down] Length of lcs is : " + lcs_topDown(a, b, solution2));  
		
	}
	// We include empty set as a subsequence. 
	
	
	public static int lcs_topDown ( String a, String b, int[][] solution) { // length of lcs
		
		if(a.length() == 0 || b.length() == 0) {
			return 0; 
		}
		
		if(solution[a.length()-1][b.length()-1] != -1 ) {
			return solution[a.length()-1][b.length()-1] ; 
		}
		
		if(a.charAt(a.length()-1) == b.charAt(b.length()-1) ) {
			solution[a.length()-1][b.length()-1] =  1 + lcs_topDown(a.substring(0, a.length()-1), b.substring(0, b.length()-1), solution); 
		} else {					
			solution[a.length()-1][b.length()-1] =  Math.max ( lcs_topDown(a.substring(0, a.length()), a.substring(0, b.length()-1), solution) , lcs_topDown(a.substring(0, a.length()-1),a.substring(0, b.length()), solution) ) ;							
		}
			
		return solution[a.length()-1][b.length()-1];
	}

	public static void lcs_bottomUp (String a, String b, int[][] solution) {
	
		for(int i=1 ; i< b.length()+1; i++ ) {
			for(int j=1 ; j < a.length()+1 ; j++) {
				
				if(a.charAt(j-1) == b.charAt(i-1)) {
					solution[i][j] = 1 + solution[i-1][j-1] ; // match 
				} else {					
					solution[i][j] =  Math.max ( solution[i-1][j] , solution[i][j-1] ) ; // not a match						
				}
				
			}
		}
		
		System.out.println("[Bottom Up] Length of lcs is : " + solution[a.length()][b.length()]);
		
	}
	
}
