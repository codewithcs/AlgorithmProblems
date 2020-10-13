package LeetCodePremium.Amazon.DynamicProgramming;
/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

coins[] array is not sorted.
 */

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;

        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] minCoins = new int[amount+1];
        Arrays.fill(minCoins, amount+1); // Do not initialize with Integer.MAX_VALUE.
        // 0th element will be 0.
        minCoins[0] = 0;

        for(int i=1; i<minCoins.length;i++ ){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<coins.length; j++){
                if(coins[j] <= i ){
                    minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i-coins[j]] );
                }
            }
        }

        if(minCoins[amount] < amount +1 ){
            return minCoins[amount];
        } else {
            return -1;
        }
    }
}
/*
For smaller subproblems, if we are not able to make a change for that value, then we store amount + 1 for that value
and not -1. This is important.
 */
