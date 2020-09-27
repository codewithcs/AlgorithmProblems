package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {
	public static void main(String[] args) {
		int[] array = {8, 5, 2, 9, 5, 6, 3 };		
		
		int[] heap = buildMaxHeap(array) ;
		
		for(int i=0 ; i<array.length; i++) {
			swap(0, array.length-i-1, heap);
			siftDown(0, array.length-i-2, heap);
		}
		
	}
	    // O(n) time | O(1) space 
	    public static int[] buildMaxHeap(int[] array) { // initially we have some heap, and now we insert or remove items from this heap. 
	      
	      int firstParentIdx = (array.length-2)/2; 
	      
	      for(int currentIdx = firstParentIdx ; currentIdx >=0 ; currentIdx-- ) {
	    	  siftDown(currentIdx, array.length-1, array) ; 
	      }
	     
	      return array;
	    
	    }
	    
	    // O(logn) time | O(1) space 
	    public static void siftDown(int currentIdx, int endIdx, int[] heap) {
	    	int childOneIdx = currentIdx*2 + 1; 
	    	
	    	while(childOneIdx <= endIdx ) {
	    		
	    		int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1 ;  // second child may not exist and will be -1. 
	    		int idxToSwap;
	    		
	    		if(childTwoIdx != -1 && heap[childTwoIdx] > heap[childOneIdx]) {
	    			idxToSwap = childTwoIdx ; 
	    		} else {
	    			idxToSwap = childOneIdx; 
	    		}
	    		
	    		if(heap[idxToSwap] > heap[currentIdx]) {
	    			swap(currentIdx, idxToSwap, heap) ; 
	    			currentIdx = idxToSwap;
	    			childOneIdx = currentIdx * 2 + 1; 
	    		} else {
	    			return ; // If the control comes here then the while loop may go into an infinite loop. (Case possible when all the heap elements are equal). 
	    		}	    		
	    		
	    	}	    	
	    }
	    
	    public static void swap(int i, int j, int[] heap) {
	    	int temp = heap[j] ; 
	    	heap[j] = heap[i] ;  
	    	heap[i] = temp;  
	    }

}
