package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors; 

public class ShortenPath {
	public static void main(String[] args) {
		
		System.out.println(shortenPath("/foo/../test/../test/../foo//bar/./baz"));
		
	}
	
	public static String shortenPath(String path) {
		
		boolean startsWithPath = path.charAt(0) == '/'; 
		String[] tokensArr = path.split("/"); 
		List<String> tokensList = Arrays.asList(tokensArr); 
		List<String> filteredTokens = tokensList.stream().filter(token -> isImportantToken(token)).collect(Collectors.toList()) ;
		
		List<String> stack = new ArrayList<>(); 
		
		if(startsWithPath) { 
			stack.add("");  /// important line that does the trick. 
		}
		
		for(String token: filteredTokens) {
			if(token.equals("..")) {
				
				if(stack.isEmpty() || stack.get(stack.size()-1).equals("..")) { // Relative
					stack.add(token); 
				} else if (!stack.get(stack.size()-1).equals("")) { // Absolute and Relative 
					stack.remove(stack.size()-1) ; 
				}
				
			} else {
				stack.add(token); 
			}
		}
		// Here we don't have any explicit continue statements. 
		
		if(stack.size() == 1 && stack.get(0).equals("")) {
			return "/" ; 
		}
		
		return String.join("/", stack); 
    }
	
	public static boolean isImportantToken(String token) {
		return token.length() > 0 && !token.equals("."); 
	}
	
	
}
