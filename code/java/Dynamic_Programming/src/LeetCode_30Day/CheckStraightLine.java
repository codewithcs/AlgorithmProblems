package LeetCode_30Day;

public class CheckStraightLine {

	 public boolean checkStraightLine(int[][] coordinates) {
	        
	        int x1 = coordinates[0][0] ; int x2 = coordinates[1][0] ; 
	        int y1 = coordinates[0][1] ; int y2 = coordinates[1][1] ; 
	        
	        double slope = (double)( y2 - y1 ) / (double)( x2 - x1 ) ;
	           
	        for(int i=2 ; i<coordinates.length ; i++){
	            
	            double currentSlope = (double)(coordinates[i][1] - y1) / (double)(coordinates[i][0] - x1) ; 
	            
	            if(currentSlope != slope ){
	                return false ; 
	            }
	                
	            
	        }
	        
	        return true ; 
	    }
	
	
}
