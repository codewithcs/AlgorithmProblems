package AlgoExpert_Hard;
import java.util.List; 
import java.util.ArrayList;
import java.util.Arrays; 

public class ZigZagTraverse {
	public static void main(String[] args) {
		
		Integer[][] array = { {1, 3, 5 } , {2, 4, 6} } ;
		
		List<List<Integer>> list = new ArrayList<>();
		
		for(Integer[] item: array) {
			list.add(Arrays.asList(item)) ;
		}
		
		System.out.println(zigzagTraverse(list));
	}
	
	public static List<Integer> zigzagTraverse(List<List<Integer>> array) {

		List<Integer> order = new ArrayList<>() ;
		
		int numOfColumns = array.get(0).size() ; 
		int numOfRows = array.size(); 
		
		int i=0, j = 0 ; // i: row, j: column 
		
		boolean down = true ; 
		
		while (order.size() <= numOfColumns * numOfRows && i<numOfRows && j<numOfColumns ) {
			
			
			order.add(array.get(i).get(j)) ; 
			
			if(down) { // going down 
					
				if(i==(numOfRows-1)) {
					j++ ; 
					down = !down ; 
				} else if(j==0 ) {
					i++ ;
					down = !down ; 
				} else {			
					i++ ; j-- ; 			
				}
				
			} 
			
			else {  // going up
				
				if( j==(numOfColumns-1)) {
					i++ ; 
					down = !down; 
				} else if(i==0 ) {
					j++; 
					down = !down; 
				} else {
					i-- ; 
					j++ ; 
				}
				
			}
		
		}
		
		return order;
	}
	
}