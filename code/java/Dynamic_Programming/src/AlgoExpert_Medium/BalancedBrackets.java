package AlgoExpert_Medium;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.ArrayList; 

public class BalancedBrackets {

	public static void main(String[] args) {
		
		System.out.println(balancedBrackets("()()[{()})]")); // ")[]}"
		
	}
	
	public static boolean balancedBrackets(String str) {
	
		Deque<Character> stack = new ArrayDeque<>(); 
		List<Character> closing = new ArrayList<>() ; 
		closing.add('}'); closing.add(')'); closing.add(']') ; 
		
		Map<Character, Character> map = new HashMap<>() ; 
		map.put('{' , '}') ;
		map.put('(' , ')') ;
		map.put('[' , ']') ;

		
		for(int i=0; i < str.length() ; i++ ) {
			
			if(closing.contains(str.charAt(i))) {
								
				if(!stack.isEmpty() && str.charAt(i) == map.get(stack.peek() )) {
					stack.pop(); 
					continue;
				} else {
					return false ; 
				}
						
			}
			else {
			stack.push(str.charAt(i));
			}
		}
		
		System.out.println(stack.size());
		
		if(stack.isEmpty()) {
			return true ; 
		} else {
		return false;
		}
	}
	
}
