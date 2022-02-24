package DP;
import java.util.List; 
import java.util.ArrayList; 

public class PalindromicDecompositions {

	public static void main(String[] args) {
		System.out.println(partition("aab"));
	}
	
	 public static List<List<String>> partition(String s) {
		 
		    List<List<String>> decompositions = new ArrayList(); 

		    decomposeString(0, s, new ArrayList<>(), decompositions);

		    return decompositions; 
		  }

		  private static void decomposeString( int workingIndex, String s, List<String> partialDecomposition, List<List<String>> decompositions) {
			  
		    if ( workingIndex == s.length() ) { // Base Case 
		      decompositions.add(new ArrayList<>(partialDecomposition));
		      return;
		    }

		    for ( int i = workingIndex; i < s.length(); i++ ) {
		      
		      if ( isPalindrome(workingIndex, i, s) ) {

		        String palindromicSnippet = s.substring(workingIndex, i + 1);
		        partialDecomposition.add(palindromicSnippet);

		        decomposeString(i + 1, s, partialDecomposition, decompositions);

		        partialDecomposition.remove(partialDecomposition.size() - 1); // Since we are filling in the subsets, we need to backtrack and need to remove the element we just filled in. 
		        
		      }
		    }
		    
		  }

		  /*
		    Checks if the region from left (inclusive) to right (inclusive) is
		    a palindromic.
		  */
		  private static boolean isPalindrome( int left, int right, String s ) {
			  
		    while (left < right) {
		      
		    	if (s.charAt(left) != s.charAt(right)) {
		    		return false;
		    	}

		    	left++;
		    	right--;
		    }

		    	return true;
		  }
	
}
