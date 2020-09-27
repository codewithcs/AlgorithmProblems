package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortenPath2 {
	public static String shortenPath(String path) {
	    
		if("/".equals(path)){
			return "/";
		}
		
		boolean isAbsolute = path.charAt(0) == '/' ;
		
		String[] tokens = path.split("/") ;
		List<String> tokenList = Arrays.asList(tokens);
		
		List<String> stack = new ArrayList<>(); 
		
		if(isAbsolute){
			for(String item: tokenList){
			
				if("..".equals(item)){
				
					if(stack.size() != 0) {
						stack.remove(stack.size()-1);
					} else {
						continue ; 
					}
				} 
				else if(".".equals(item)){
					continue; 
				}
				else if("".equals(item)) {
					continue; 
				}
				else {
					stack.add(item) ; 
				}
			}

		}
		
		else { // path is relative
			for(String item: tokenList){
			
				if("..".equals(item) ){	
					if(stack.isEmpty() || stack.get(stack.size()-1).equals("..")){
						stack.add(item); 
					} else {
						stack.remove(stack.size()-1);
						}
				} 
				
				else if(".".equals(item)){
					continue; 
				}
				else if("".equals(item)) {
					continue; 
				}
				
				else {
					stack.add(item) ; 
				}
			
			}
		
		}
		
		if(stack.isEmpty()){
			return "/" ;
		}
		
		// final logic here. 
		if(isAbsolute){
			stack.add(0, "") ; 
		}
		
		System.out.print("Stack is : " + stack);
		return String.join("/", stack); 
  }
}
