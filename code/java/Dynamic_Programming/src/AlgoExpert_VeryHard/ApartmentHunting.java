package AlgoExpert_VeryHard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApartmentHunting {

	public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
		
		int[] maxDistances = new int[blocks.size()] ; Arrays.fill(maxDistances, -1);
		
		for(int i=0 ; i<blocks.size(); i++) {
			
			int max = -1; 

			for(int j=0 ; j < reqs.length ; j++ ) { // find max for a particular required building. Running Maximum
				
				int minDistances = Integer.MAX_VALUE; 
				String current = reqs[j] ; 
							
    			for(int k=0 ; k<blocks.size(); k++) { // find mins. 
    				if(blocks.get(k).get(current)) {
        				minDistances = Math.min(minDistances, Math.abs(i-k) );  
    				}
				}
				
    			max = Math.max(max, minDistances); 
    			
			}
			
			maxDistances[i] = max; 
		
		}
		
		
		// return index of maxDistances[] which has minimum value. 
		int smallestIndex = 0; 
		for( int i=1; i < maxDistances.length ; i++) {
			if(maxDistances[i] < maxDistances[smallestIndex]) {
				smallestIndex = i; 
			}
		}
		
	   return smallestIndex; 
	}
	
	public static int apartmentHunting2(List<Map<String, Boolean>> blocks, String[] reqs) {
		
		int[] maxDistanceAtBlocks = new int[blocks.size()] ; 
		Arrays.fill(maxDistanceAtBlocks, -1);
		
		int[][] closestReqDistance = new int[reqs.length][blocks.size()] ; 
		for(int[] array: closestReqDistance) {
			Arrays.fill(array, Integer.MAX_VALUE);
		}
		
		for(int i=0 ; i<reqs.length ; i++ ) {
			String current = reqs[i] ; 
			int previousFoundIdx = Integer.MAX_VALUE; 
					
			for(int j=0 ; j<blocks.size() ; j++) {
				
				if(blocks.get(j).get(current)) {
					closestReqDistance[i][j] = 0;
					previousFoundIdx = j ; 
				} else {
					closestReqDistance[i][j] = Math.min(closestReqDistance[i][j],  Math.abs(previousFoundIdx-j)); 
				}  							// can avoid Math.min here as in the first iteration we can have a value near MAX value. 
				
			}	
		}
		
		for(int i=0 ; i<reqs.length ; i++ ) {
			String current = reqs[i] ; 
			int previousFoundIdx = Integer.MAX_VALUE; 
					
			for(int j=blocks.size()-1 ; j>=0 ; j--) {
				
				if(blocks.get(j).get(current)) {
					closestReqDistance[i][j] = 0;
					previousFoundIdx = j ; 
				} else {
					closestReqDistance[i][j] = Math.min(closestReqDistance[i][j], Math.abs(previousFoundIdx-j));  
				}							// need to take min here. 
					
			}	
		}
		
		for(int i=0 ; i<blocks.size(); i++) {
			int max = -1; 

			for(int j=0 ; j < reqs.length ; j++ ) { 
    			max = Math.max(max, closestReqDistance[j][i]); 
			}
			
			maxDistanceAtBlocks[i] = max; 
		
		}
		
		
		// return index of maxDistances[] which has minimum value. 
		int smallestIndex = 0; 
		for( int i=1; i < maxDistanceAtBlocks.length ; i++) {
			if(maxDistanceAtBlocks[i] < maxDistanceAtBlocks[smallestIndex]) {
				smallestIndex = i; 
			}
		}
		
	   return smallestIndex; 
	}
	
}

/*
 In the test case, for a block we will be given all of the required building status as either true or false. 
 */

