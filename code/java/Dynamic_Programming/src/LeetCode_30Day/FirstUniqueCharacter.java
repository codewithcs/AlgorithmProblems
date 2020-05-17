package LeetCode_30Day;

import java.util.HashMap;

public class FirstUniqueCharacter {

	public static void main(String[] args) {
		System.out.println(firstUniqChar("leetcode"));
	}
	
	public static int firstUniqChar(String s) {
	        
		HashMap<Character, Integer> count = new HashMap<Character, Integer>();
	    int n = s.length();
	    
	    // build hash map : character and how often it appears
	    for (int i = 0; i < n; i++) {
	        char c = s.charAt(i);
	        count.put(c, count.getOrDefault(c, 0) + 1);
	    }
	    
	    // find the index
	    for (int i = 0; i < n; i++) {
	        if (count.get(s.charAt(i)) == 1) 
	            return i;
	    }
	    return -1;
	        
	}
	
	
}
