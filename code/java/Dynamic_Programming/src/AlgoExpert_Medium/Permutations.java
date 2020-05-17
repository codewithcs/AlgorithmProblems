package AlgoExpert_Medium;

import java.util.List;

import java.util.ArrayList; 

public class Permutations {
	
	public static void main(String[] args) {
		List<Integer> array = new ArrayList<>() ;
		array.add(1) ; array.add(2) ; array.add(3) ; 
		System.out.println( getPermutations( array ) ) ; 
	}
	
	public static List<List<Integer>> getPermutations(List<Integer> array) {
	    
		if(array.isEmpty()) {
			return new ArrayList<List<Integer>>() ; 
		}
		
		List<List<Integer>> list = new ArrayList<List<Integer>>() ;
		
		List<Integer> current = new ArrayList<>() ; 
		
		return permutations(array, current, list) ; 
			
	  }
	
	public static List<List<Integer>> permutations ( List<Integer> array, List<Integer> current, List<List<Integer>> list ) {

		if(current.size() == array.size()) {
			List<Integer> added = new ArrayList<>(current) ; // Imp to use new operator as current will be overwritten in subsequent calls.
			list.add(added);
			return list; 
		}
		
		
		for(int i=0 ; i<array.size(); i++) {
			
			int currentNumber = array.get(i) ;
		
			if(!current.contains(currentNumber)) { // Constraints. 
					
				current.add(currentNumber) ; // Choose
					
				permutations(array, current, list) ;  // Explore
					
				current.remove(current.size()-1) ; // Unchoose
				
			}
				
		}
			
		
	 return list ; 
		
	}
}
