package AlgoExpert_Medium;

public class WaysToMakeChange {

	public static void main(String[] args) {
		
		int[] array = { 1, 5 } ; 
		int n = 6;
		
		System.out.println(	numberOfWaysToMakeChange(n, array) ) ; 
		
	}
	
	public static int numberOfWaysToMakeChange(int n, int[] denoms) {

		int[][] solution = new int[denoms.length+1][n+1] ; 
		
		for(int i=0 ; i<n +1 ; i++) {
			solution[0][i] = 0 ; 
		}
		
		for(int i=0 ; i<denoms.length ; i++) {
			solution[i][0] = 1 ; 
		}
		
		for ( int i=1 ; i<denoms.length+1; i++) {
			
			for(int j=1 ; j < n+1 ; j++) {
				solution[i][j] = solution[i-1][j] ; // don't take this coin.
				
				if( j-denoms[i-1] >=0 ) { // take this coin only when this is +ve. 
					solution[i][j] += solution[i][j-denoms[i-1]] ;  
				}
				
			}
			
		}	
		
		return solution[denoms.length][n];
  }
	
}
