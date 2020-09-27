package DP;

import java.util.ArrayList;
import java.util.List;

public class PermuteAString {

	public static void main(String[] args) {
	
		System.out.println( permute (new int[] { 1, 2, 3} ) );
		
		permutation("", "abcdef") ;  
	}
	
	private static void permutation(String prefix, String str) {
		
	    int n = str.length();
	    
	    if (n == 0) System.out.println(prefix);
	    
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n) );
	    }
	}
	
	// Solution based on Cracking The Coding Interview book.  SEE THIS 
	public static ArrayList<String> permutation(String s) {
	    // The result
	    ArrayList<String> res = new ArrayList<String>();
	    // If input string's length is 1, return {s}
	    if (s.length() == 1) {
	        res.add(s);
	    } else if (s.length() > 1) {
	        int lastIndex = s.length() - 1;
	        
	        // Find out the last character
	        String last = s.substring(lastIndex);
	        
	        // Rest of the string
	        String rest = s.substring(0, lastIndex);
	        
	        // Perform permutation on the rest string and
	        // merge with the last character
	        
	        res = merge(permutation(rest), last);
	    }
	    return res;
	}

	/**
	 * @param list a result of permutation, e.g. {"ab", "ba"}
	 * @param c    the last character
	 * @return     a merged new list, e.g. {"cab", "acb" ... }
	 */
	public static ArrayList<String> merge(ArrayList<String> list, String c) {
	    ArrayList<String> res = new ArrayList<>();
	    // Loop through all the string in the list
	    for (String s : list) {
	        // For each string, insert the last character to all possible positions
	        // and add them to the new list
	        for (int i = 0; i <= s.length(); ++i) {
	            String ps = new StringBuffer(s).insert(i, c).toString();
	            res.add(ps);
	        }
	    }
	    return res;
	}
	
	
	// Back to Back SWE solution. Storing every combination as an array list and in an array list. 
	public static List<List<Integer>> permute(int[] originalArray) {
		
	    List<List<Integer>> allPermutations = new ArrayList<>();
	    
	    generateAllPermutations(new ArrayList<>(), originalArray, allPermutations);
	    
	    return allPermutations;
	    
	  }

	  private static void generateAllPermutations( List<Integer> runningChoices, int[] originalArray, List<List<Integer>> allPermutations) {
		  
	    if (runningChoices.size() == originalArray.length) {
	      allPermutations.add(new ArrayList<>(runningChoices));
	      return;
	    }

	    /*
	      Every stack frame of this function call represents the expression of trying (almost) all items in every "slot" in the array.
	      The recursion stops when we are choosing on 1 past the final "slot".
	    */
	    
	    for (int i = 0; i < originalArray.length; i++) { // won't work when there are duplicates. 
	      
	      int choice = originalArray[i]; 

	      
	      // Skip if element already exists in 'runningChoices'
	      if (runningChoices.contains(choice)) { 	// Good thinking 
	        continue;
	      }

	      // Can use this approach when we have to avoid duplicating. 
	      
	      // 1.) Choose - Add the item to the 'runningChoices'
	      runningChoices.add(choice);

	      
	      // 2.) Explore - Recurse on the choice
	      generateAllPermutations(runningChoices, originalArray, allPermutations);

	      
	      // 3.) Unchoose - We have returned from the recursion, remove the choice we made.
	      // The next iteration will try another item in the "slot" we are working on.
	      runningChoices.remove(runningChoices.size() - 1);
	      
	      
	    }
	  }
	
}