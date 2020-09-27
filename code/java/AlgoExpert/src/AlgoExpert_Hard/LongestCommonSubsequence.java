package AlgoExpert_Hard;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays; 

public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String str1 = "ZXVVYZW" ;
		String str2 = "XKYKZPW" ; 
		
		System.out.println(longestCommonSubsequence("ZXVVYZW", "XKYKZPW"));
	
	}
	
	public static List<Character> longestCommonSubsequence(String str1, String str2) {
	// storing the individual characters of the lcs. 
		
		String[][] lcs = new String[str1.length()][str2.length()] ;  
		
		List<Character> list = new ArrayList<>() ; 
		
		String result = topDown(str1, str2, lcs);
		
		char[] c = result.toCharArray() ;
		
		for(char c1 : c) {
			list.add(c1) ; 
		}
		
		return list ; 
		
	}
	
	public static String topDown(String str1, String str2, String[][] lcs) {
		// storing the individual characters of the lcs. 
			
			if("".equals(str1) || "".equals(str2)) {
				return "" ; 
			}
			
			if(lcs[str1.length()-1][str2.length()-1] != null ) {
				return lcs[str1.length()-1][str2.length()-1] ; 
			}
			System.out.println("Here");
			if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)) {
				lcs[str1.length()-1][str2.length()-1] = topDown(str1.substring(0, str1.length()-1) , str2.substring(0, str2.length()-1), lcs ) + str1.charAt(str1.length()-1) ; 
			} else { // mismatch 
				
				int s1 = topDown(str1.substring(0, str1.length()) , str2.substring(0, str2.length()-1), lcs ).length() ;
				int s2 = topDown(str1.substring(0, str1.length()-1) , str2.substring(0, str2.length()), lcs ).length() ; 
				if(s1 > s2)
					lcs[str1.length()-1][str2.length()-1] = topDown(str1.substring(0, str1.length()) , str2.substring(0, str2.length()-1), lcs ) ;
				else 
					lcs[str1.length()-1][str2.length()-1] = topDown(str1.substring(0, str1.length()-1) , str2.substring(0, str2.length()), lcs ) ; 
			}
						
			return lcs[str1.length()-1][str2.length()-1] ; 
	}
	
}
