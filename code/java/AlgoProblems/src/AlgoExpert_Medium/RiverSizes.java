package AlgoExpert_Medium;

import java.util.List;
import java.util.ArrayList;

public class RiverSizes {

	public static void main(String[] args) {
		
		int[][] matrix =  { { 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0 },
			                { 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0 },
			                { 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1 },
			                { 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0 },
			                { 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1 } 
			              };
		
	System.out.println(riverSizes(matrix));	
		
	}
	
	  public static List<Integer> riverSizes(int[][] matrix) {

	    int rows = matrix.length;
		int columns = matrix[0].length ;  // Ask the interviewer whether there is atleast 1 row.
			
		boolean[][] visited = new boolean[rows][columns] ; 
		
		  List<Integer> list = new ArrayList<>() ; 
		  
		for(int i=0 ; i<rows ; i++) {
			
			for(int j=0; j< columns ; j++ ) {
								
				if(visited[i][j] || matrix[i][j] == 0) {
					visited[i][j] = true; 
					continue;
				}
				visited[i][j] = true ;
				int ways = riverSizesRecursion(i, j, matrix, visited) ;
			 
				list.add(ways) ; 
			
			}
			
		}
		
		  return list;
	  }
	  
		
	  
	  // Particular Single Exploration. 
	  public static int riverSizesRecursion(int row, int column, int[][] matrix, boolean[][] visited) {
		  		  
		  // This function calls happens only when matrix[row][column] is equal to 1. 
		  
		  int right = 0; 
		  int size = 1; 
		  int down = 0 ; 
		  int left = 0; 
		  int up = 0 ; 
		  			  
		  if(row!=0) { // Up
			  if(matrix[row-1][column] == 1 && !visited[row-1][column]) {
				  visited[row-1][column] = true ; 
				  up = riverSizesRecursion(row-1, column, matrix, visited) ;  
			  } 
		  }
		  
		  
		  if(column!=0) { // Left
			  if(matrix[row][column-1] == 1 && !visited[row][column-1]) {
				  visited[row][column-1] = true ; 
				  left = riverSizesRecursion(row, column-1, matrix, visited) ; 
			  } 
		  }
		  
		  
			if(row!=matrix.length-1) { // Down
			  if(matrix[row+1][column] == 1 && !visited[row+1][column]) {
				  visited[row+1][column] = true ; 
				  down = riverSizesRecursion(row+1, column, matrix, visited) ; 
			  } 
		  }	 
			
			if(column!=matrix[0].length-1) { // Right
			  if(matrix[row][column+1] ==1 && !visited[row][column+1]) {
				  visited[row][column+1] = true; 
				  right = riverSizesRecursion(row, column+1, matrix, visited) ; 
			  } 
			}
			
			size = size + up + left + right + down  ; 
				  
		  return size; 	  
	  }
	  
}
