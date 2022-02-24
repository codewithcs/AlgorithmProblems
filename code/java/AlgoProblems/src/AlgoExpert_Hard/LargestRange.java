package AlgoExpert_Hard;
import java.util.Map; 
import java.util.HashMap; 

public class LargestRange {
	public static void main(String[] args) {
		int[] array = { 1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 } ;
		largestRange(array);
	}
	
	public static void largestRange(int[] array) {
		
		Map<Integer, Boolean> map = new HashMap<>() ;
		
		for(int num: array) {
			map.put(num, true) ; // true means that this number can be explored. 
		}
		
		int left = -1; int right = -1 ; 
		int min = -1; int max = -1 ; 
		
		int length = -1; 
		
		for(int num: array ) {
			
			if(Boolean.TRUE.equals(map.get(num))) {
				
				int current = num-1 ; 
				left = num ; 
				right = num ; 
				map.replace(num, false) ; 
				
				while(Boolean.TRUE.equals(map.get(current))) {
					left = current ; 
					current-- ; 
				}
				
				current = num +1 ; 
				while(Boolean.TRUE.equals(map.get(current))) {
					right = current ; 
					current++ ;
				}
				
			}
			
			if(right-left+1 > length) {
				length = right-left+1; 
				min = left ; 
				max = right ;  
			}
			
			
		}
		System.out.println("Minimum Left Index is : " + min);
		System.out.println("Maximum Right Index is : " + max);
		
	}
	
}
