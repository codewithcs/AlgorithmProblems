package DP;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		int coins[] = {1, 2, 4, 5}; 

		int x[]  = Arrays.copyOfRange(coins, 1, coins.length-1) ;
		// [1, 4)
		
		System.out.println(x[0]);
		System.out.println(x[1]);
		//System.out.println(x[2]); // 5
		

		System.out.println(x.length);
		
	}
	
}
