package AlgoExpert_Hard;

import java.util.Arrays;

public class Knapsack2 {

	static int[] values = new int[] {894, 260, 392, 281, 27};
	static int[] weights = new int[] {8, 6, 4, 0, 21};
	static int W = 30;
	
	public static void main(String[] args) {
		int[][] cache = new int[values.length+1][W+1] ; 

		for(int[] array: cache) {
			Arrays.fill(array, -1);
		}
		long startTime = System.nanoTime();
		System.out.println(knapsack(5, 30));
		long stopTime = System.nanoTime();
		
		System.out.println("Time : "  + (stopTime - startTime));
		System.out.println();
		
		startTime = System.nanoTime();
		System.out.println(knapsackTopDown(5, 30, cache));
		stopTime = System.nanoTime();
		System.out.println("Time : "  + (stopTime - startTime));
	}
	
	private static int knapsack(int i, int W) {
	    if (i <= 0) {
	        return 0;
	    }
	    if (weights[i-1] > W) {
	        return knapsack(i-1, W);
	    } else {
	        return Math.max(knapsack(i-1, W), knapsack(i-1, W - weights[i-1]) + values[i-1]);
	    }
	}
	
	private static int knapsackTopDown(int i, int W, int[][] cache) {
	    if (i <= 0) {
	        return 0;
	    }
	    if(cache[i][W] != -1 ) {
	    	return cache[i][W] ; 
	    }
	    
	    int max = -1; 
	    if (weights[i-1] > W) {
	        max = knapsackTopDown(i-1, W, cache);
	    } else {
	        max = Math.max(knapsackTopDown(i-1, W, cache), knapsackTopDown(i-1, W - weights[i-1], cache) + values[i-1]);
	    }
	    
	    cache[i][W] = max; 
	    
	    return cache[i][W] ; 
	}
	
	
}
