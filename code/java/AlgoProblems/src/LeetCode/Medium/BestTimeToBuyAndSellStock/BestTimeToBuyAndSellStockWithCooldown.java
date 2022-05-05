package LeetCode.Medium.BestTimeToBuyAndSellStock;

import java.util.Arrays;

/*
You are given an array prices where prices[i] is the price of a given
stock on the ith day.

Find the maximum profit you can achieve. You may complete as many
transactions as you like (i.e., buy one and sell one share of the
stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day
(i.e., cooldown one day).

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).


 */
public class BestTimeToBuyAndSellStockWithCooldown {

    // Failed approach. incorrect.
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int coolDownIndex = -2;

        for(int i=0; i< prices.length-1 ; i++){
            if(prices[i] < prices[i+1] && i - coolDownIndex > 1){
                maxProfit += prices[i+1] - prices[i];
                coolDownIndex = i+1;
            }
        }
        return maxProfit;
    }


    // Passed 208/210 Test Cases.
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        boolean buying = true;
        return recurse(prices, 0, buying, maxProfit);
    }

    public int recurse(int[] prices, int currentIndex, boolean buying, int maxProfit){
        if(currentIndex>= prices.length ) {
            return maxProfit;
        }

        // Don't need to worry about this if as we are returning the maximum in the end.
//        if(currentIndex == prices.length-1 && buying){
//            return maxProfit;
//        }

        int a;
        if(buying){
            a = recurse(prices, currentIndex + 1, false, maxProfit - prices[currentIndex]);
        } else { // We have option to sell.
            // If we sell at currentIndex, we can only next buy at currentIndex + 2.
            a = recurse(prices, currentIndex + 2, true, maxProfit + prices[currentIndex]);
        }

        int b = recurse(prices, currentIndex+1, buying, maxProfit);
        return Math.max(a, b);
    }


    // DP solution.
    // Bottom Up DP.
    // Here, in the columns, when index is 1, it means that we want to buy a share or just cooldown.
    // When index is 0, it means that we want to sell or just rest.
    public int maxProfit3(int[] prices){
        if(prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(0, prices[1] - prices[0]);
        dp[1][1] = Math.max(-prices[0], -prices[1]);

        for(int i = 2; i < prices.length; i++) {
            // When we are selling, it comes after buying or we do cooldown.
            // So, previous states would be simple cooldown or buying value from [i-1][1]
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }

        return dp[prices.length-1][0];
    }

    // Top Down Approach with Memoization
    // Debug this approach later, maybe?
    public int maxProfit4(int[] prices) {
        if(prices.length == 1){
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int currentIndex = 0;
        int buying = 1;
        int maxProfit = 0;

        return recurse2(prices, dp, currentIndex, buying, maxProfit);
    }

    public int recurse2(int[] prices, int[][] dp, int currentIndex, int buying, int maxProfit){

        if(currentIndex>= prices.length ) {
            return maxProfit;
        }

        if(dp[currentIndex][buying] != -1){
            return dp[currentIndex][buying];
        }

        if(buying == 1){
            int a = recurse2(prices, dp, currentIndex + 1, 0, maxProfit - prices[currentIndex]);
            int b = recurse2(prices, dp, currentIndex+1, buying, maxProfit);
            dp[currentIndex][buying] = Math.max(a, b);

        } else { // we have option to sell.

            int a = recurse2(prices,dp, currentIndex + 2, 1, maxProfit + prices[currentIndex]);
            int b = recurse2(prices, dp, currentIndex+1, buying, maxProfit);
            dp[currentIndex][buying] = Math.max(a, b);
        }

        return dp[currentIndex][buying];
    }





    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75929/7-line-Java%3A-only-consider-sell-and-cooldown
    public int maxProfit5(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        /*
         there can be two types of profit we need to track
         sellProf[i] - profit earned by selling on ith day
         restProf[i] - profit earned by resting on ith day
         */
        int sellProf = 0;
        int restProf = 0;
        int lastProf = 0;

        for(int i = 1; i<prices.length; i++){
            lastProf = sellProf;

            // The current sellProf is either by selling on ith day or by resting on ith day
            sellProf = Math.max(sellProf + prices[i] - prices[i-1], restProf);
            restProf = Math.max(lastProf, restProf);
        }

        return Math.max(sellProf, restProf);
    }
}
