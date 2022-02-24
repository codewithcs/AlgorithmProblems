package DP;

import java.util.ArrayList;
import java.util.List;

public class NMatchedParentheses {

	public static void main(String[] args) {
		
		int n = 5; 
		int left = n ; 
		int right = n ; 
		String s = "" ; 
		
		List<String> list = new ArrayList<>(); 
		
		System.out.println("n is: " + n );
		recursion(left, right, s, list);
		
		System.out.println("Strings are: \n" + list); 
		
		recursionToIteration(left, right, s, list);
		
	}
	
	public static void recursion(int left, int right, String s, List<String> list) {
		
		if(left == 0 && right == 0) { // Base Case 
			list.add(s) ; 
			return ; 
		}
		
		if(left == 0) {  // Mixture of base case and recursive call. 
			recursion(left, right-1, s+")",  list) ;
			return ; 
		}	
		
		if (left == right ) {
			recursion(left-1, right, s + "(",  list) ;
		} else {							// right > left 
			recursion(left-1, right, s + "(", list) ; 
			recursion(left, right-1, s + ")", list) ; 	
		}
		
	}
	
	public static void recursionToIteration(int left, int right, String s, List<String> list) { // avoid this: Does not work currently. 
		
		while( left >=0 && right > 0) {
		
			if(left == 0 ) {		// Base Case 
				while( right > 0 ) {
					s = s + ")" ; right--; 
				}
				 list.add(s) ; 
				 break;
			}
			
			if(left == right ) {
				left-- ; 
				s = s + "(" ; 
			} else {
				
				left--; 
				s = s + "(" ; 
				
				right-- ; 
				s = s.substring(0, s.length()-2) ;  
				s = s + ")" ; 
				
			} // This does not work currently. 
			
			
			
		}
		
	}
}
