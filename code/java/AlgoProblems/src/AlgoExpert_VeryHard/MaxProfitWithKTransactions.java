package AlgoExpert_VeryHard;

import java.util.Deque;
import java.util.LinkedList;

public class MaxProfitWithKTransactions {

    // O(nk) time | O(nk) space
    public static int maxProfitWithKTransactions(int[] prices, int k){
        if(prices.length == 0){
            return 0;
        }

        int[][] profits = new int[k+1][prices.length]; // by default all values will be 0.

        for(int t=1; t < k+1; t++){
            int maxThusFar = Integer.MIN_VALUE;

            for(int d=1; d<prices.length ; d++){
                maxThusFar = Math.max(maxThusFar, profits[t-1][d-1] - prices[d-1]);
                profits[t][d] = Math.max(profits[t][d-1], maxThusFar + prices[d]);
            }
        }
        return profits[k][prices.length-1];
    }

    // O(nk) time | O(n) space
    public static int maxProfitWithKTransactions2(int[] prices, int k){
        if(prices.length == 0){
            return 0;
        }

        int[] evenProfits = new int[prices.length];
        int[] oddProfits = new int[prices.length]; // 0 by default.

        int[] currentProfits;
        int[] previousProfits;

        for(int t=1; t < k+1; t++){
            int maxThusFar = Integer.MIN_VALUE;

            if(t%2 == 1){
                currentProfits = oddProfits;
                previousProfits = evenProfits;
            } else {
                currentProfits = evenProfits;
                previousProfits = oddProfits;
            }

            for(int d=1; d<prices.length; d++){
                maxThusFar = Math.max(maxThusFar, previousProfits[d-1]-prices[d-1]);
                currentProfits[d] = Math.max(currentProfits[d-1], maxThusFar + prices[d]);
            }
        } // If we change current[] then odd[] and even[] will change depending on t.

        return k%2 == 0 ? evenProfits[prices.length-1] : oddProfits[prices.length-1];
    }

    /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
    public int maxProfitSlowSolution(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices.length];

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i-1][m]);
                }
                T[i][j] = Math.max(T[i][j-1], maxVal);
            }
        }
        printActualSolution(T, prices);
        return T[K][prices.length - 1];
    }

    // Building the solution.
    public void printActualSolution(int T[][], int prices[]) {
        int i = T.length - 1;
        int j = T[0].length - 1;

        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j-1; k >= 0; k--) {
                    if (T[i-1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }

        while(!stack.isEmpty()) {
            System.out.println("Buy at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at price " + prices[stack.pollFirst()]);
        }

    }
}
