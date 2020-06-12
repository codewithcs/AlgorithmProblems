package AlgoExpert_Hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumbersInPi {
	public static void main(String[] args) {
		String pi = "3141" ;
		String[] numbers = { "3", "141", "31"} ; 
		
		System.out.println(numbersInPi(pi, numbers));

	//	System.out.println(numbersInPi2(pi, numbers));

	}
	public static int numbersInPi(String pi, String[] numbers) {
		List<String> list = Arrays.asList(numbers) ; 
		
		Map<String, Integer> cache = new HashMap<>() ;
		
		return numbersInPiHelper(pi, list, cache);
	}
	
	public static int numbersInPiHelper(String suffix, List<String> list, Map<String, Integer> cache) {
		
		int globalMin = Integer.MAX_VALUE;
				
		// Base Case
		if(list.contains(suffix)) {
			return 0; 
		}
		
		if(cache.containsKey(suffix)) { // check whether catch is filled for this item. 
			return cache.get(suffix) ; 
		} 
		
		for(int i=0 ; i<suffix.length()-1 ; i++ ) {
			String currentPrefix = suffix.substring(0, i+1) ; 
			String currentSuffix = suffix.substring(i+1, suffix.length()); 
			
			if(list.contains(currentPrefix)) {
				int min = 1 + numbersInPiHelper(currentSuffix, list, cache) ;
				
				if (globalMin == 0) { 
					globalMin = min ;
				}
				else if(globalMin == Integer.MAX_VALUE || min != 0 ) { // if globalMin is Integer.MAX_VALUE, then min can be 0. 
					globalMin = Math.min(globalMin, min ) ; 
				}
				
				cache.put(suffix, globalMin) ; 
			} 
		}

		System.out.println("globalMin is : " + globalMin);
		if(globalMin == Integer.MAX_VALUE || globalMin == 0) {
			return -1; 
		} 
		
		return cache.get(suffix) ; 
	}
	
	
	// AlgoExpert solution : O(n^3 + m)
	public static int numbersInPi2(String pi, String[] numbers) {
		Set<String> numbersTable = new HashSet<>(); 
		for(String number: numbers) {
			numbersTable.add(number); 
		}
		
		Map<Integer, Integer> cache = new HashMap<>() ; 
		int minSpaces = getMinSpaces(pi, numbersTable, cache, 0); 
		
		return minSpaces == Integer.MAX_VALUE ? -1 : minSpaces ; 	
 	}

	public static int getMinSpaces(String pi, Set<String> numbersTable, Map<Integer, Integer> cache, int idx) {
		
		if(idx == pi.length()) {
			return -1; 
		}
		
		if(cache.containsKey((idx))) {
			return cache.get(idx) ; 
		}
		
		int minSpaces = Integer.MAX_VALUE ; 
		
		for(int i= idx; i< pi.length() ; i++ ) {
			String prefix = pi.substring(idx, i+1) ; 
			
			if(numbersTable.contains(prefix)) {
				int minSpacesInSuffix = getMinSpaces(pi, numbersTable, cache, i+1) ;
				
				// Handle int overflow. 
				if(minSpacesInSuffix == Integer.MAX_VALUE) {
					minSpaces = Math.min(minSpaces, minSpacesInSuffix) ; 
				} else {
					minSpaces = Math.min(minSpaces, minSpacesInSuffix + 1) ; 
				}
			}
		}
		
		cache.put(idx, minSpaces) ; 
		return cache.get(idx) ; 
	}
	
	
}
