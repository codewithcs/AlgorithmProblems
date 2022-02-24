package LeetCodePremium.Google.DP;

public class BestTimeToBuyAndSellStock {
    // Brute Force: O(n^2)

    // Find the largest peak following the smallest peak.
    // Keep a running minimum and see if currentValue - min > currentMaxProfit
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            }
            else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
