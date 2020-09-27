package AlgoExpert_Medium;

import java.util.List; 
import java.util.ArrayList;
import java.util.Arrays; 

public class MinHeapConstruction {

	public static void main(String[] args) {
		Integer[] array = { 7, 4, 9, 10, 45, 23, 43, 12 , 5, 2, 6, 50};
		new MinHeap(Arrays.asList(array)) ; 
	}
	
	static class MinHeap {
		    List<Integer> heap = new ArrayList<>(); // Creating a list to store the elements. 
	
		    public MinHeap(List<Integer> array) { 
		      heap = buildHeap(array); 
		    }
	
		    // O(n) time | O(1) space 
		    public List<Integer> buildHeap(List<Integer> array) { // initially we have some heap, and now we insert or remove items from this heap. 
		      
		      int firstParentIdx = (array.size()-2)/2; 
		      
		      for(int currentIdx = firstParentIdx ; currentIdx >=0 ; currentIdx-- ) {
		    	  siftDown(currentIdx, array.size()-1, array) ; 
		      }
		     
		      return array;
		    
		    }
	
		    
		    // O(logn) time | O(1) space 
		    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
		    	int childOneIdx = currentIdx*2 + 1; 
		    	
		    	while(childOneIdx <= endIdx ) {
		    		
		    		int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1 ;  // second child may not exist and will be -1. 
		    		int idxToSwap;
		    		
		    		if(childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)) {
		    			idxToSwap = childTwoIdx ; 
		    		} else {
		    			idxToSwap = childOneIdx; 
		    		}
		    		
		    		if(heap.get(idxToSwap) < heap.get(currentIdx)) {
		    			swap(currentIdx, idxToSwap, heap) ; 
		    			currentIdx = idxToSwap;
		    			childOneIdx = currentIdx * 2 + 1; 
		    		} else {
		    			return ; // If the control comes here then the while loop may go into an infinite loop. (Case possible when all the heap elements are equal). 
		    		}	    		
		    		
		    	}	    	
		    }
	
		    // O(logn) time | O(1) space 
		    public void siftUp(int currentIdx, List<Integer> heap) {
		    	int parentIdx = (currentIdx - 1) /2 ; 
		    	
		    	while(currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
		    		swap(currentIdx, parentIdx, heap); 
		    		currentIdx = parentIdx; 
		    		parentIdx = (currentIdx -1)/2 ; 
		    	}
		    	
		    }
	
		    public int peek() {
		      return heap.get(0);
		    }
	
		    public int remove() { 
		      swap(0, heap.size()-1, heap); 
		      int valueToRemove = heap.get(heap.size()-1); 
		      heap.remove(heap.size()-1); 
		      siftDown(0, heap.size()-1, heap); 
		      return valueToRemove; 
		    }
	
		    public void insert(int value) {
		    	heap.add(value); 
		    	siftUp(heap.size()-1, heap) ; 
		    }
		    
		    public void swap(int i, int j, List<Integer> heap) {
		    	Integer temp = heap.get(j) ; 
		    	heap.set(j,  heap.get(i)) ; 
		    	heap.set(i, temp); 
		    }
	  }
	
}
