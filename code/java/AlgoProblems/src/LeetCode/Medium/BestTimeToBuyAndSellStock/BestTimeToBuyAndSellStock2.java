package LeetCode.Medium.BestTimeToBuyAndSellStock;

/*
You are given an integer array prices where prices[i] is the price of a
given stock on the ith day.

On each day, you may decide to buy and/or sell the stock.
You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

Constraints:
1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4
*/

import java.util.ArrayList;
import java.util.List;

public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        int buyingIndex = -1;
        int sellingIndex = -1;

        for(int i=0; i< prices.length-1 ; ){

            if(i==0 && prices[i] < prices[i+1] || (i>0 && prices[i] <= prices[i-1] && prices[i] < prices[i+1] ) ){
                buyingIndex  = i;

                int k = buyingIndex;
                while(i< prices.length-1 && k < prices.length && prices[i+1] > prices[k]){
                    k++;
                    i++;
                }

                sellingIndex = i;

                maxProfit = maxProfit + prices[sellingIndex] - prices[buyingIndex];
            } else {
                i++;
            }

        }


        return maxProfit ;
    }

    // Smarter way, deals with continuous strictly increasing sequence.
    // O(n) time and O(1) space.
    public int maxProfit2(int[] prices) {
        int profit =0;

        for(int i=0;i<prices.length-1 ; i++ ){
            if(prices[i]<prices[i+1] ){
                profit += prices[i+1] - prices[i];
            }
        }
        return profit;
    }

    public int maxProfit3(int[] prices) {
        int i = 0, buy, sell, profit = 0, N = prices.length - 1;
        while (i < N) {
            while (i < N && prices[i + 1] <= prices[i]) i++;
            buy = prices[i];

            while (i < N && prices[i + 1] > prices[i]) i++;
            sell = prices[i];

            profit += sell - buy;
        }

        return profit;
    } // [2, 2, 5], [1, 2, 3, 4, 5], [1, 4, 2]

    //  Forming the solution as well,

   //  BONUS:
    // With this approach, we still can calculate on which buying days and selling days, we reach the max profit:

    public Pair<List<Pair<Integer, Integer>>, Integer> buysAndSells(int[] prices) {
        int i = 0, iBuy, iSell, profit = 0, N = prices.length - 1;

        List<Pair<Integer, Integer>> buysAndSells = new ArrayList<>();

        while (i < N) {
            while (i < N && prices[i + 1] <= prices[i]) i++;
            iBuy = i;

            while (i < N && prices[i + 1] > prices[i]) i++;
            iSell = i;

            profit += prices[iSell] - prices[iBuy];
            Pair<Integer, Integer> bs = new Pair<>(iBuy, iSell);
            buysAndSells.add(bs);
        }

        return new Pair<>(buysAndSells, profit);
    }

    public class Pair<T1, T2> {
        public T1 fst;
        public T2 snd;
        public Pair(T1 f, T2 s) {
            fst = f;
            snd = s;
        }
    }


}