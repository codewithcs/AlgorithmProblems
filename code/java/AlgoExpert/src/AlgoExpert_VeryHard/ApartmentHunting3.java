package AlgoExpert_VeryHard;

import java.util.List;
import java.util.Map;

public class ApartmentHunting3 {
	// O(br) time | O(b + br) space
	// where b is the number of blocks and r is the number of requirements.
	// Algo Expert Solution 2
	  public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
		  int[][] minDistancesFromBlocks = new int[reqs.length][];
		  
		  for(int i=0; i<reqs.length ; i++) {
			  minDistancesFromBlocks[i] = getMinDistances(blocks, reqs[i]); 
		  }
		  int[] maxDistancesAtBlocks = getMaxDistancesAtBlocks(blocks, minDistancesFromBlocks);
		  return getIdxAtMinValue(maxDistancesAtBlocks); 
	  }

	  public static int[] getMinDistances(List<Map<String, Boolean>> blocks, String req ) {
		  int[] minDistances = new int[blocks.size()]; 
		  int closestReqIdx = Integer.MAX_VALUE; 
		  
		  for(int i=0 ; i<blocks.size(); i++) {
			  if(blocks.get(i).get(req)) {
				  closestReqIdx = i; 
			  }
			  minDistances[i] = distanceBetween(i, closestReqIdx);  // can avoid Math.min() in the first iteration as the distance will be less than or equal to Integer.MAX_VALUE.
		  }
		  for(int i=blocks.size()-1 ; i >=0 ; i--) {
			  if(blocks.get(i).get(req)) {
				  closestReqIdx = i; 
			  }
			  minDistances[i] = Math.min(minDistances[i], distanceBetween(i, closestReqIdx)); 
		  }
		  return minDistances;
	  }
	  
	  public static int[] getMaxDistancesAtBlocks(List<Map<String, Boolean>> blocks, int[][] minDistancesFromBlocks) {
		  int[] maxDistancesAtBlocks = new int[blocks.size()]; 
		  for(int i=0 ; i<blocks.size(); i++) {
			  int[] minDistancesAtBlock = new int[minDistancesFromBlocks.length];
			  
			  for(int j=0 ; j<minDistancesFromBlocks.length; j++) {
				  minDistancesAtBlock[j] = minDistancesFromBlocks[j][i] ; 
			  }
			  maxDistancesAtBlocks[i] = arrayMax(minDistancesAtBlock); 
		  }
		  return maxDistancesAtBlocks; 
	  }
	  
	  public static int getIdxAtMinValue(int[] array) {
		  int idxAtMinValue = 0; 
		  int minValue = Integer.MAX_VALUE; 
		  for(int i=0; i<array.length; i++) {
			  int currentValue = array[i]; 
			  if(currentValue < minValue) {
				  minValue = currentValue ; 
				  idxAtMinValue = i; 
			  }
		  }
		  return idxAtMinValue; 
	  }
	  
	  public static int distanceBetween(int a, int b) {
		  return Math.abs(a-b); 
	  }
	   
	  public static int arrayMax(int[] array) {
		  int max = array[0]; 
		  for(int a: array) {
			  if(a>max ) {
				  max = a; 
			  }
		  }
		  return max;
	  }
}







