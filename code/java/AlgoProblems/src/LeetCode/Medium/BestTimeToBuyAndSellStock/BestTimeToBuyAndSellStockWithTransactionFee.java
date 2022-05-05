package LeetCode.Medium.BestTimeToBuyAndSellStock;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day,
and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions
as you like, but you need to pay the transaction fee for each transaction.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).

Constraints:
1 <= prices.length <= 5 * 10^4
1 <= prices[i] < 5 * 10^4
0 <= fee < 5 * 10^4
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    // Transaction Fee comes into picture only when we are selling.
    // As (buy, sell) makes 1 complete transaction.
    public int maxProfit(int[] prices, int fee) {
        if(prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(0, prices[1] - prices[0] - fee);
        dp[1][1] = Math.max(-prices[0], -prices[1]);

        for(int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[prices.length-1][0];
    }

    // Better Space Complexity.
    // As we are only dependent on values from the previous array.
    public int maxProfit2(int[] prices, int fee) {
        if(prices.length < 2) return 0;
        int[] first = new int[2];
        int[] second = new int[2];

        first[0] = 0;
        first[1] = -prices[0];
        second[0] = Math.max(0, prices[1] - prices[0]-fee);
        second[1] = Math.max(-prices[0], -prices[1]);

        for(int i = 2; i < prices.length; i++) {
            first[0] = Math.max(second[0], second[1] + prices[i]-fee);
            first[1] = Math.max(second[1], second[0] - prices[i]);
            second = first;
            first = new int[2];
        }

        return second[0];
    }

    public int maxProfit3(int[] prices, int fee) {
        if(prices.length < 2) return 0;

        int[] first = new int[2];
        int[] second = new int[2];

        first[0] = 0;
        first[1] = -prices[0];

        for(int i = 1; i < prices.length; i++) {
            second[0] = Math.max(first[0], first[1] + prices[i] - fee);
            second[1] = Math.max(first[1], first[0] - prices[i] );
            first = second;
            second = new int[2];
        }

        return first[0];
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/1975657/What-is-a-Transaction

    // When we subtract the transaction fee when buying a share.
    public int maxProfit4(int[] prices, int fee) {
        if(prices.length < 2) return 0;

        int[] first = new int[2];
        int[] second = new int[2];

        first[0] = 0;
        first[1] = -prices[0] -fee;

        for(int i = 1; i < prices.length; i++) {
            second[0] = Math.max(first[0], first[1] + prices[i] );
            second[1] = Math.max(first[1], first[0] - prices[i] - fee);
            first = second;
            second = new int[2];
        }

        return first[0];
    }

}
