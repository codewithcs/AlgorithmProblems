package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ContinuousMedian {
	public static void main(String[] args) {
		
	}
	
	static class ContinuousMedianHandler {
		Heap lowers = new Heap(Heap::MAX_HEAP_FUNC, new ArrayList<Integer>()); // kind of injecting the functionality for the interface.
		Heap greaters = new Heap(Heap::MIN_HEAP_FUNC, new ArrayList<Integer>()) ; 
		// Lower half: Max heap, Greater Half: Min heap. 
		
	    double median = 0;

	    public void insert(int number) {
	    	if(lowers.length == 0 || number < lowers.peek()) {
	    		lowers.insert(number);
	    	} else {
	    		greaters.insert(number);
	    	}
	    	rebalanceHeaps();
	    	updateMedian(); 
	    }
	    
	    public void rebalanceHeaps() {
	    	if(lowers.length - greaters.length == 2) { // No need to check for > 2 as we always check whether the heaps are imbalanced or not. 
	    		greaters.insert(lowers.remove());
	    	} else if( greaters.length - lowers.length == 2) {
	    		lowers.insert(greaters.remove());
	    	}
	    }
	    
	    public void updateMedian() {
	    	if(lowers.length == greaters.length ) {
	    		median = ((double)lowers.peek() + (double)greaters.peek()) / 2; 
	    	} else if(lowers.length > greaters.length) {
	    		median = lowers.peek(); 
	    	} else {
	    		median = greaters.peek(); 
	    	}
	    }
	    

	    public double getMedian() {
	      return median;
	    }
	    
	    // Also solve this using Priority queue and Insertion Sort logic. 
	    static class Heap {
	    	List<Integer> heap = new ArrayList<>(); // Storing heap elements in a list. 
	    	
	    	BiFunction<Integer, Integer, Boolean> comparisonFunc;
	    	
	    	int length; // size of the heap. 
	    	
	    	public Heap(BiFunction<Integer, Integer, Boolean> func, List<Integer> array) {
	    		heap = buildHeap(array); 
	    		comparisonFunc = func ; 
	    		length = heap.size(); 
	    	}
	    	
	    	public List<Integer> buildHeap(List<Integer> array) {
	    		int firstParentIdx = (array.size()-2) / 2;
	    		
	    		for(int currentIdx = firstParentIdx ; currentIdx >= 0 ; currentIdx--) {
	    			siftDown(currentIdx, array.size()-1, array); 
	    		} 
	    		return array; 
	    	}
	    	
	    	public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
	    		int childOneIdx = currentIdx * 2 + 1; 
	    		while(childOneIdx <= endIdx) {
	    			int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx*2 + 2 : -1 ; 
	    			int idxToSwap; 
	    			
	    			if(childTwoIdx != -1 ) {
	    				if(comparisonFunc.apply(heap.get(childTwoIdx), heap.get(childOneIdx))) {
	    					idxToSwap = childTwoIdx ; 
	    				} else {
	    					idxToSwap = childOneIdx; 
	    				}
	    			} else {
	    				idxToSwap = childOneIdx ; 
	    			}
	    		
	    			if(comparisonFunc.apply(heap.get(idxToSwap), heap.get(currentIdx))) {
	    				swap(currentIdx, idxToSwap, heap); 
	    				currentIdx = idxToSwap; 
	    				childOneIdx = currentIdx * 2 + 1; 
	    			} else {
	    				return ; 
	    			}
	    		}
	    	}
	    	
	    	
	    	public void siftUp(int currentIdx, List<Integer> heap) {
	    		int parentIdx = (currentIdx - 1 )/2;
	    		while(currentIdx > 0) {
	    			if(comparisonFunc.apply(heap.get(currentIdx), heap.get(parentIdx))) {
	    				swap(currentIdx, parentIdx, heap); 
	    				currentIdx = parentIdx; 
	    				parentIdx = (currentIdx-1)/2; 
	    			} else {
	    				return; 
	    			}
	    		}
	    	}
	    	
	    	public int peek() {
	    		return heap.get(0) ; 
	    	}
	    	
	    	public int remove() {
	    		swap(0, heap.size()-1, heap); 
	    		int valueToRemove = heap.get(heap.size()-1); 
	    		heap.remove(heap.size()-1); 
	    		length-- ; 
	    		siftDown(0, heap.size()-1, heap) ; 
	    		return valueToRemove; 
	    	}
	    	
	    	public void insert (int value) {
	    		heap.add(value); 
	    		length++; 
	    		siftUp(heap.size()-1, heap); 
	    	}
	    	
	    	public void swap(int i, int j, List<Integer> heap) {
	    		Integer temp = heap.get(j) ; 
	    		heap.set(j, heap.get(i));
	    		heap.set(i, temp); 
	    	}
	    	
	    	public static Boolean MAX_HEAP_FUNC(Integer a, Integer b) {
	    		return a > b; 
	    	}
	    	
	    	public static Boolean MIN_HEAP_FUNC(Integer a, Integer b) {
	    		return a < b; 
	    	}
	    	
	    }
	    
	    
	  }
}