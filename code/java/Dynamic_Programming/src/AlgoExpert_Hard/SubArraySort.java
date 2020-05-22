package AlgoExpert_Hard;

import java.util.Arrays;

public class SubArraySort {
	public static void main(String[] args) {
		
		int[] array = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 } ; 
		subarraySort(array); 
		//System.out.println(subarraySort(array).toString());
		
	}
	
	public static void subArraySort(int[] array) {// assuming sorting means increasing. 
		
		int min = Integer.MAX_VALUE ;
		int max = Integer.MIN_VALUE ; 
		int currentMax = Integer.MIN_VALUE ;
		int currentMin = Integer.MAX_VALUE ;
		int minIndex = -1; int maxIndex = -1; 
		
		for(int i=0 ; i<array.length ; i++) {
			
				if(array[i]>array[i+1] ) {
					currentMin = array[i+1] ; 
					currentMax = array[i];
					
					if(currentMax > max ) {
						max = currentMax ;
					}
					if(currentMin < min ) {
						min = currentMin ; 
					}
					i++ ;  
				}
			
		}
		
		for(int i=0 ; i<array.length ; i++ ) {
			if(array[i] > min) {
				minIndex = i ;  break ; 
			}
		}
		
		for(int i=array.length-1 ; i>=0 ; i-- ) {
			if(array[i] < max) {
				maxIndex = i ;  break ; 
			}
		}
	}
	
	
	public static void subarraySort(int[] array) {
		
		// Either the array would be increasing or decreasing 
		
		// Increasing 
		int largest = -1 ; 
		int smallest = -1; int i=0 ; int min = -1; int max = -1; int key = 0; 
		
		while( i <array.length-1 ) {
			
	
			if(array[i] < largest) {
				max = i;
				i++ ; 
				continue; 
			}
			
			if(array[i+1] < array[i]) {
				largest = array[i] ; key++ ; 
				
				if (key==1)
					min = Arrays.binarySearch(Arrays.copyOfRange(array, 0, i+1), array[i+1]) ;
				
				i= i+2; 
			} else {
				i++; 
				continue ; 
			}
			
			if(array[i] > largest) {
				largest = array[i] ; i++ ; 
			}
		}
		
		
		System.out.println("Min is : " + min);
		System.out.println("Max is: " + max);
		
	  }
}
