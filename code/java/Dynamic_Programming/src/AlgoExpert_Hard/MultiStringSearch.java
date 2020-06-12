package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.List; 

public class MultiStringSearch {
	public static void main(String[] args) {
		
	}
	// O(bns) time, O(n) space
	public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
	    
		List<Boolean> solution = new ArrayList<>(); 
		for(String smallString: smallStrings) {
			solution.add(isInBigString(bigString, smallString)); 
		}
		
		return solution;
	}
	
	public static boolean isInBigString(String bigString, String smallString) {
		
		for(int i=0 ; i < bigString.length(); i++) {
			if( i + smallString.length() > bigString.length() ) {
				break; 
			}
			if(isInBigString(bigString, smallString, i)) {
				return true; 
			}
		}
		return false; 
	}
	
	public static boolean isInBigString(String bigString, String smallString, int startIdx) {
		int leftBigIdx = startIdx; 
		int rightBigIdx = startIdx + smallString.length() - 1; 
		int leftSmallIdx = 0 ; 
		int rightSmallIdx = smallString.length() - 1; 
		
		while(leftBigIdx <= rightBigIdx ) {
			
			if(bigString.charAt(leftSmallIdx) != smallString.charAt(leftSmallIdx)
					|| bigString.charAt(rightBigIdx) != smallString.charAt(rightSmallIdx) ) {
				return false; 
			}
			
			leftBigIdx++; 
			rightBigIdx--; 
			leftSmallIdx++; 
			rightSmallIdx--; 
		}
		return true; 
	}
	
	
	
}
