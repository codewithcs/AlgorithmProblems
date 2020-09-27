package AlgoExpert_Medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverse {
	
	public static void main(String[] args) {
		int[][] array = { {1} , {2}  , {3} , {4} , {5}  , {6} } ;// {{1, 2, 3, 4} , {8, 7, 6, 5} }; //{ {1, 2, 3, 4} , { 10, 11, 12, 5 }, {9, 8, 7, 6} } ; 
	
		System.out.println(spiralTraverse(array));
	}

	public static List<Integer> spiralTraverse(int[][] array) {
	    // Write your code here.

		List<Integer> list = new ArrayList<>() ;  
		
		int columnLeft = 0; int columnRight = array[0].length -1; 
		int rowTop = 0 ; int rowBottom = array.length-1; 
	
		int visited = 0; 
		
		System.out.println( "Total elements : " + array[0].length * array.length);
		
		while(visited < array[0].length * array.length ) {
					
			// Case 1: Ends row wise  
			
			if(rowTop == rowBottom) {
				
				for(int i=columnLeft ; i<=columnRight ; i++) { // Also includes when all 4 pointers are equal. 
					list.add(array[rowTop][i] ) ;
					visited++; 
					System.out.println("visited : " + visited);
				}
				
			}
			
			// Case 2: End Column Wise 
			else if(columnLeft==columnRight) {
				
				for(int i=rowTop ; i<=rowBottom ; i++) {
					list.add(array[i][columnLeft] ) ;
					visited++; 
					System.out.println("visited : " + visited);
				}
			}
			
			// Case 3: Ends Normally. 
			else {
						
				for(int i=columnLeft ; i<=columnRight ; i++) { // left to right 
					list.add(array[rowTop][i]) ; 
					visited++ ; 
					System.out.println("visited : " + visited);
				}			
				
				rowTop++ ; 
				
				for(int i=rowTop ; i<=rowBottom ; i++) { // top to bottom
					list.add(array[i][columnRight]) ; 
					visited++ ; 
					System.out.println("visited : " + visited);
				}
				
				columnRight-- ; 
			
				
				for(int i=columnRight ; i>=columnLeft ; i--) { // right to left
					list.add(array[rowBottom][i]) ; 
					visited++ ; 			
					System.out.println("visited : " + visited);
				}
				
				rowBottom--; 
				
				for(int i=rowBottom ; i>=rowTop ; i--) { // bottom to up 
					list.add(array[i][columnLeft]) ; 
					visited++ ; 
					System.out.println("visited : " + visited);
				}
			
				columnLeft++; 
			
			}
		}
							
		return list; 
			
	  }
	
	// Having 2 for-loops does not mean O(n^2).
	// What decides the complexity is the logic . 
	
	// O(n) time | O(n) space where n is the total number of elements in the array.
	public static List<Integer> spiralTraverse2(int[][] array) {
		
		if(array.length ==0 ) return new ArrayList<>();
		
		List<Integer> result = new ArrayList<>() ; 
		
		spiralFill(array, 0, array.length-1, 0, array[0].length-1, result) ; 
		
		return result ;
	}
	
	// A lot cleaner, O(n) 
	public static void spiralFill(int[][] array, int startRow, int endRow, int startCol, int endCol, List<Integer> result) {
		
		if(startRow > endRow || startCol > endCol) {
			return ; 
		}

		for(int col= startCol ; col<= endCol ; col++) {
			result.add(array[startRow][col]) ; 
		}
		
		for(int row=startRow+1; row<=endRow; row++) {
			result.add(array[row][endCol]); 
 		}
		
		for(int col=endCol-1; col>=startCol; col--) {
			if(startRow == endRow ) break ; 
			/*
			 Edge case when there is a single row in the middle of the matrix. 
			 We don't want to double count the values in this row, which we have already 
			 counted in the first for-loop. 
			 */
			result.add(array[endRow][col]) ; 
		}
		
		for(int row=endRow-1; row>= startRow+1; row--) {
			if(startCol==endCol) break ; 
			/*
			 Handle the edge case when there is a single column in the middle of the matrix. 
			 We don't to double count the values in this column, which we have already
			 counted in the second for-loop. 
			 */
			
			result.add(array[row][startCol]) ; 
		}
		
		spiralFill(array, startRow+1, endRow-1, startCol+1, endCol-1, result); 
	}
	

	
}







