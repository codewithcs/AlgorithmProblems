package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {

	public static void main(String[] args) {
		int[][] items = { {1, 2}, {4, 3}, {5, 6}, {6, 7} } ; 
		int capacity = 10; 
		long startTime = System.nanoTime();
		System.out.println(knapsackProblem(items, capacity));
		long stopTime = System.nanoTime();
		System.out.println("Time : "  + (stopTime - startTime));

		startTime = System.nanoTime();
		System.out.println(knapsackProblem2(items, capacity));
		stopTime = System.nanoTime();
		System.out.println("Time : "  + (stopTime - startTime));

	}
	
	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
		int rows = items.length; 
	    int columns = capacity; 
	    	     
	    int[][] cache = new int[rows+1][columns+1] ; 
	    
	    for(int i=0; i<columns; i++) {
	    	cache[0][i] = 0 ; 
	    }
	    
	    for(int i=0; i<rows; i++) {
	    	cache[i][0] = 0 ; 
	    }
	    
	    for(int i=1 ; i<rows+1; i++) {
	    	for(int j=1 ; j<columns+1 ; j++) {  		
	    		int itemValue = items[i-1][0];
	    		int itemWeight = items[i-1][1] ;     
	    		cache[i][j] = cache[i-1][j] ; // don't take the item.  
	    		
	    		if(j-itemWeight>=0) { // can merge the two ifs. 
	    			if(cache[i][j] < itemValue + cache[i-1][j-itemWeight] ) {
	    				cache[i][j] = itemValue + cache[i-1][j-itemWeight] ;
	    			}
	    		}
	    	}
	    }
	    
	    int total = cache[rows][columns] ; 
	    
	    List result = new ArrayList<List<Integer>>();
	    result.add(Arrays.asList(total));
	    //result.add(buildSolution(cache, items));
	    return result;
	  }
	
	public static List<Integer> buildSolution(int[][] cache, int[][] items) {
		
		int i= cache.length-1; 
		int j= cache[0].length-1; 
		List<Integer> list = new ArrayList<>() ; 
		
		while(i>=1 && j>=0) {
			// Take the (i-1)th item. 
			if( j-items[i-1][1] >= 0 ) { 
				if(cache[i-1][j] < items[i-1][0] + cache[i-1][j-items[i-1][1]] ) { 
					// Only add the item only when it is actually included in cache[i][j]
					list.add(0, i-1) ;  
					j = j-items[i-1][1] ;
				} 
			} 
			
			i--; 
		}
		
		return list; 
	} // cache[4] is my last row and items[3] is my last item. 

	// Solution 2: O(nc) time and space
	public static List<List<Integer>> knapsackProblem2(int[][] items, int capacity) {
		
		int[][] knapsackValues = new int[items.length+1][capacity+1] ; 
		
		for(int i=1; i<items.length+1 ; i++) {
			int currentWeight = items[i-1][1] ; 
			int currentValue = items[i-1][0] ; 
			
			for(int j=0; j<capacity+1; j++) {
				
				if(currentWeight > j) {
					knapsackValues[i][j] = knapsackValues[i-1][j] ; 
				} else {
					knapsackValues[i][j] = 
							Math.max(
								knapsackValues[i-1][j], 
								knapsackValues[i-1][j-currentWeight] + currentValue);
				}
			} 
		}
		
		return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]) ;
	}

	public static List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int weight) {
		
		List<List<Integer>> sequence = new ArrayList<>() ; 
		List<Integer> totalWeight = new ArrayList<>() ; 
		
		totalWeight.add(weight) ;
		sequence.add(totalWeight) ; 
		sequence.add(new ArrayList<Integer>()) ; 
		
		int i = knapsackValues.length -1 ;
		int c = knapsackValues[0].length - 1; 
		
		while(i>0) {
			if(knapsackValues[i][c] == knapsackValues[i-1][c]) {
				i-- ;
			} else { // > , cannot be < because we found max when filling the cache. 
				sequence.get(1).add(0, i-1) ; 
				c -= items[i-1][1] ; 
				i-- ; 
			} 
			
			if(c==0) {
				break; 
			}
		}
		
		return sequence ; 
	}
}
