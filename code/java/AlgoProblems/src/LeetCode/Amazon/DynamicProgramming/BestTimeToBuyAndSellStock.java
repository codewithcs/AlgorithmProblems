package LeetCode.Amazon.DynamicProgramming;

/*
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
design an algorithm to find the maximum profit.
Note that you cannot sell a stock before you buy one.
 */

public class BestTimeToBuyAndSellStock {

    // O(n) approach
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int min = prices[0];
        int maxProfit = 0;

        for(int i=1; i< prices.length ; i++){
            if(prices[i]-min >=0){
                maxProfit = Math.max(maxProfit, prices[i]-min);
            }
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices){
        int maxProfit = 0;
        for(int i=0 ; i< prices.length ; i++){
            int current = prices[i]; /// buying price
            for(int j=i+1; j< prices.length; j++){
                if(prices[j]-current >= 0){
                    maxProfit = Math.max(maxProfit, prices[j]-current);
                }
            }
        }
        return maxProfit;
    }
}
