package DP;

import java.util.Arrays;

public class CoinChange {

	public static int coin_change_bottomUp(int[] coins, int total, int[][] answer) {
		
		
		if(answer[coins.length][total] > -1 ) {
			return answer[coins.length][total] ; 
		}
			
		for ( int i=1 ; i<= coins.length ; i++ ) {
			for ( int j=1 ; j<= total; j++ ) {
				answer[i][j] = answer[i-1][j] ; // When ith item is not included
				
				if (j-coins[i-1] >= 0)
				answer[i][j] += answer[i][j-coins[i-1]] ;				
			}	
		}
		
		return answer[coins.length][total]; 
		
	}
	
	
public static int coin_change_TopDown(int[] coins, int total, int[][] answer2) {
		
		if(total == 0 ) { // Base Cases  
			return 1;
		} else if ( total < 0 ) {
			return 0 ; 
		}
		
		if (coins.length == 0)
			return 0 ; 
		
		if(answer2[coins.length-1][total-1] > -1 ) {
			return answer2[coins.length-1][total-1] ; 
		}
			
		
		int first = coin_change_TopDown(coins, total-coins[0], answer2) ; // take the first coin
		
		int second = coin_change_TopDown(Arrays.copyOfRange(coins, 1, coins.length), total, answer2); // don't take the first coin. 
		
		answer2[coins.length-1][total-1] = first + second ;
		
		return answer2[coins.length-1][total-1]; 
		
	}
	
	
public static int coin_change_minimum_topDown (int[] coins, int total, int[] answer) {
	
	if(total == 0 ) {
		return 0;
	} else if ( total < 0 ) {
		return -1 ; // to make the net effect to be 0.  
	}
	
	if (coins.length == 0)
		return 0 ; 
	
	if(answer[total-1] > -1 ) {
		return answer[total-1] ;  // In top down total-1, represents the total as we dont include the base case in the final cacche. 
	}
		
	// can create a loop.
	
	int min = 1000; 
	
	for (int i=coins.length-1 ; i>=0 ; i--) {
		min = Math.min(min, 1 + coin_change_minimum_topDown(coins, total-coins[i], answer )) ;
	} // this will handle the case when the change cannot be made. 
		
	answer[total-1] = min ;
	
	return answer[total-1]; 

}


public static int coin_change_minimum_bottomUp (int[] coins, int total, int[] answer) {
	
	// initialise answer[] with a large value. Update the code.
	
	for ( int i=1 ; i< answer.length ; i++ ) { // answer[i] represents a total which is a subproblem.  
				
		for ( int j=0 ; j<coins.length ; j++ ) { // We go from lowest element to highest because we are building the solution of smaller subproblems first. 			
			if ( i-coins[j] >= 0)
			 answer[i] = 1 + answer[i-coins[j]] ; // If we are not sorting the coins array, then we take the minimum of current min and 1+...
			
		}	  
	
	}
		
	return  answer[total]; 
 
}


	public static void main(String[] args) {
		
		int coins[] = {1, 2, 4, 5}; 
		
		int total = 15 ; 
		
		int answer[][] = new int[coins.length+1][total+1] ;
				
		for (int[] row : answer)	
			Arrays.fill(row, -1);
				
		for (int j=0 ; j<= total ; j++ ) {
			answer[0][j] = 0; 
		}
		
		for (int j=0 ; j<= coins.length  ; j++ ) {
			answer[j][0] = 1; 
		}
		
		
		System.out.println("Number of ways are : " + coin_change_bottomUp(coins, total, answer) );
		
		int answer2[][] = new int[coins.length][total] ;
		
		for (int[] row : answer2)	
			Arrays.fill(row, -1);
			
		
		System.out.println("Number of ways are : " + coin_change_TopDown(coins, total, answer2) );

		int answer3[] = new int[total];  
		
		Arrays.fill(answer3, -1);
		
		System.out.println("Top Down: Minimum number of coins are : " + coin_change_minimum_topDown(coins, total, answer3) );
		
		int answer4[] = new int[total+1];  
		Arrays.fill(answer4, -1);
		answer4[0] = 0; 
		System.out.println("Bottom Up: Minimum number of coins are : " + coin_change_minimum_bottomUp(coins, total, answer4));

		
		
	}
	
}
