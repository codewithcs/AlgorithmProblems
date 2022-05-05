package LeetCode.Easy;

/*
You are given an array prices where prices[i] is the
price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy
one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0.

Constraints:
1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
*/

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];

        for(int i=1; i< prices.length; i++){
            if(prices[i] - min >= 0){
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }
}
