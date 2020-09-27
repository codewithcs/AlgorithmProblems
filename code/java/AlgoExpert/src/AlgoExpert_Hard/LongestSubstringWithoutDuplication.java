package AlgoExpert_Hard;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutDuplication {
	public static void main(String[] args) {
		System.out.println( longestSubstringWithoutDuplication("clementisacap") );
	}
	
	// O(n) time, O(min(n, A)) space
	public static String longestSubstringWithoutDuplication(String str) {
		
		if(str.equals("")){
            return str;
        }
		
		Map<Character, Integer> lastSeen = new HashMap<>(); 
		int[] longest = {0, 1}; 
		// end index and last index where last index is not included 

		int startIdx = 0; 
		System.out.println("startIdx is : " + startIdx);

		for(int i=0; i<str.length(); i++) {
			
			char c = str.charAt(i); 
			if(lastSeen.containsKey(c)) {
				System.out.println("c is " + c );
				startIdx = Math.max(startIdx, lastSeen.get(c) + 1); 
				System.out.println("startIdx is : " + startIdx);
				System.out.println();
			}
			
			if(longest[1]-longest[0] < i+1-startIdx) {
				longest = new int[] { startIdx, i+1} ; 
			}
						
			lastSeen.put(c, i) ; 
		}
		
		String result = str.substring(longest[0], longest[1]) ; 
		
		return result;
	}
}
