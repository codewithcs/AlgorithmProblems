package AlgoExpert_Medium;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class MinMaxStackConstruction {

	public static void main(String[] args) {
		
	}

	  static class MinMaxStack {
		  
		List<Map<String, Integer>> minMaxStack = new ArrayList<Map<String, Integer>>() ; 
		List<Integer> stack = new ArrayList<Integer>(); 
		  
		// O(1) time | O(1) space
	    public int peek() {
	      return stack.get(stack.size()-1); 
	    }
	    
	    // O(1) time | O(1) space
	    public int pop() {
	      minMaxStack.remove(minMaxStack.size()-1); 
	      return stack.remove(stack.size()-1); 
	    }

	    // O(1) time | O(1) space
	    public void push(Integer number) {
	    	Map<String, Integer> newMinMax = new HashMap<>(); 
	    	newMinMax.put("min", number);
	    	newMinMax.put("max", number) ; 
	    	
	    	if(!minMaxStack.isEmpty()) {
	    		Map<String, Integer> lastMinMax = 
	    				new HashMap<>(minMaxStack.get(minMaxStack.size()-1)); 
	    		newMinMax.replace("min", Math.min(lastMinMax.get("min"), number) ) ;
	    		newMinMax.replace("max", Math.max(lastMinMax.get("max"), number) ) ;
	    	}
	    	minMaxStack.add(newMinMax) ;
	    	stack.add(number); 
	    }

	    // O(1) time | O(1) space
	    public int getMin() {
	      return minMaxStack.get(minMaxStack.size()-1).get("min");
	    }

	 
	    // O(1) time | O(1) space
	    public int getMax() {
	      return minMaxStack.get(minMaxStack.size()-1).get("max");
	    }
	    
	  }
	
}
// All class methods should run in constant time and constant space. 

