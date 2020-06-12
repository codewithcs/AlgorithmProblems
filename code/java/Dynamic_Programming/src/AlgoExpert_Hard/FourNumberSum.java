package AlgoExpert_Hard;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class FourNumberSum {

	public static void main(String[] args) {
		
		int[] array = {7, 6, 4, -1, 1, 2 } ; 
		
		System.out.println(fourNumberSum(array, 16));
		
	}
	 
	public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
		
		
		Map<Integer, List<Integer[]>> map = new HashMap<> () ;
		List<Integer[]> result =  new ArrayList<>() ; 
		
		for( int i=1 ; i<array.length-1 ; i++ ) { // no need to care about last and first element.  
			
			int current = array[i] ;
			
			for(int j=i+1 ; j<array.length; j++ ) {
				
				int diff = targetSum - (current + array[j]) ; 
				
				if(map.containsKey(diff)) {
										
					for(Integer[] pair: map.get(diff)) {
						result.add(new Integer[] {current, array[j], pair[0], pair[1]  }) ; 
					}
	
				}
				
			}
			
			for(int k=i-1 ; k>=0 ; k--) { // Add to HashMap here 
				
				Integer currentKey = current + array[k] ; 
				Integer[] pair = new Integer[]{ current, array[k] } ;
				
				if(map.containsKey(currentKey)) {
					map.get(currentKey).add(pair) ; 
				} else {
					List<Integer[]> pairGroup = new ArrayList<>() ; 
					pairGroup.add(pair) ; 
					map.put (currentKey, pairGroup ); 
				}
				
			}
			
		}

	    return result;
	  }
	
}
