package AlgoExpert_Medium;

import java.util.List;
import java.util.ArrayList; 

public class PowerSet {

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<>() ;
		array.add(1) ; array.add(2) ; array.add(3) ; 
		System.out.println( powerset( array ) ) ;
	}
	
	public static List<List<Integer>> powerset(List<Integer> array) {
		
		List<List<Integer>> list = new ArrayList<List<Integer>>() ;
		list.add(new ArrayList<>() ) ; 
		
		List<Integer> current = new ArrayList<>() ; 
		
		return pset(array, current, list) ; 
	  }
	
	public static List<List<Integer>> pset ( List<Integer> array, List<Integer> current, List<List<Integer>> list ) {
		
		
		for(int i=0 ; i<array.size(); i++) {
			
			int currentNumber = array.get(i) ;
		
			if(!current.contains(currentNumber) ){ // Constraints. 
					
				if(current.size()<1 || currentNumber > current.get(current.size()-1) ) {
					current.add(currentNumber) ; // Choose
					list.add(new ArrayList<>(current)) ;
					pset(array, current, list) ;  // Explore					
					current.remove(current.size()-1) ; // Unchoose
				}
			}
		}
		
	 return list ; 
		
	}
	
}
