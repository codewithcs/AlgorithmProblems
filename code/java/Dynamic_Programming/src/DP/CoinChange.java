package DP;

import java.util.Arrays;

public class CoinChange {

	public static int coin_change_bottomUp(int[] coins, int total, int[][] answer) {
		
		
		if(answer[coins.length][total] > -1 ) {
			return answer[coins.length][total] ; 
		}
			
		for ( int i=1 ; i<= coins.length ; i++ ) {
			for ( int j=1 ; j<= total; j++ ) {
				answer[i][j] = answer[i-1][j] ;
				
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
		
		if(answer2[coins.length][total] > -1 ) {
			return answer2[coins.length][total] ; 
		}
			
		
		int first = coin_change_TopDown(coins, total-coins[0], answer2) ; // take the first coin
		
		int second = coin_change_TopDown(Arrays.copyOfRange(coins, 1, coins.length), total, answer2); // don't take the first coin. 
		
		answer2[coins.length][total] = first + second ;
		
		return answer2[coins.length][total]; 
		
	}
	
	
public static int coin_change_minimum_topDown (int[] coins, int total, int[] answer) {
	
	if(total == 0 ) {
		return 0;
	} else if ( total < 0 ) {
		return -1 ; 
	}
	
	if (coins.length == 0)
		return 0 ; 
	
	if(answer[total] > -1 ) {
		return answer[total] ; 
	}
		
	// can create a loop. 
	int first = 1 + coin_change_minimum_topDown(coins, total-coins[0], answer) ;
	
	int second = 1 + coin_change_minimum_topDown(coins, total-coins[1], answer); 
	
	int third = 1 + coin_change_minimum_topDown(coins, total-coins[2], answer); 
	
	int fourth = 1 + coin_change_minimum_topDown(coins, total-coins[3], answer);
	
	int min = Math.min( Math.min(Math.min(first, second) , third ) , fourth ) ; 
	
	answer[total] = min ;
	
	return answer[total]; 
	
	 
}


public static int coin_change_minimum_bottomUp (int[] coins, int total, int[] answer) {
	
int min=1000; // or max_int
	
	for ( int i=1 ; i< answer.length ; i++ ) {
				
		for ( int j=0 ; j<coins.length ; j++ ) {
		if ( i-coins[j] >= 0)
		 min = 1 + answer[i-coins[j]] ; 
		
		}	
	
		if(min!=1000)
		answer[i] = min ; 
		else 
		answer[i] = 0; 
	
	}
		
	return  min; 
 
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
		
		int answer2[][] = new int[coins.length+1][total+1] ;
		
		for (int[] row : answer2)	
			Arrays.fill(row, -1);
			
		
		System.out.println("Number of ways are : " + coin_change_TopDown(coins, total, answer2) );

		int answer3[] = new int[total+1];  
		
		Arrays.fill(answer3, -1);
		
		System.out.println("Number of ways are : " + coin_change_minimum_topDown(coins, total, answer3) );
		
		Arrays.fill(answer3, -1);
		answer3[0] = 0; 
		System.out.println("Number of ways are : " + coin_change_minimum_bottomUp(coins, total, answer3));

		
		
	}
	
}
