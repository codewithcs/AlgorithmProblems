package AlgoExpert_Hard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApartmentHunting2 {
	
	// AlgoExpert Solution
	public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
		
		int[] maxDistanceAtBlocks = new int[blocks.size()]; 
		Arrays.fill(maxDistanceAtBlocks, Integer.MIN_VALUE);
		
		for(int i=0 ; i<blocks.size(); i++ ) {
			for(String req: reqs) {
				int closestReqDistance = Integer.MAX_VALUE; 
				for(int j=0; j < blocks.size() ; j++ ) {
					if(blocks.get(j).get(req)) {
						closestReqDistance = Math.min(closestReqDistance, distanceBetween(i, j)) ;
					}
				}
				maxDistanceAtBlocks[i] = Math.max(maxDistanceAtBlocks[i], closestReqDistance); 
			}
		}
		
		return getIdxAtMinValue(maxDistanceAtBlocks); 
	}

	public static int getIdxAtMinValue(int[] array) {
		int idxAtMinValue = 0 ;
		int minValue = Integer.MAX_VALUE; 
		
		for(int i=0 ; i<array.length; i++) {
			int currentValue = array[i]; 
			if(currentValue<minValue) {
				minValue = currentValue ; 
				idxAtMinValue = i; 
			}
		}
		return idxAtMinValue; 
	}
	
	public static int distanceBetween(int a, int b) {
		return Math.abs(a-b); 
	}
	
	
	
	// Method 2: Pre Compute 
	
	
	
	
}




















