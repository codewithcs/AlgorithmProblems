package LeetCode.Amazon.DynamicProgramming;

/*
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.
 */
public class CoinChange2 {

    // Correct Logic.
    public int change(int amount, int[] coins) {
        if(amount == 0){
            return 1;
        }

        if(coins.length == 0){
            return 0;
        }

        int[][] numberOfChanges = new int[coins.length+1][amount+1];

        for(int i=0; i<amount+1; i++){
            numberOfChanges[0][i] = 0;
        }

        for(int i=0; i<coins.length+1; i++){
            numberOfChanges[i][0] = 1;
        }

        for(int i=1; i<coins.length+1; i++){
            for(int j=1; j< amount+1; j++){
                int currentAmount = j;
                if(currentAmount-coins[i-1] >=0 ){
                    numberOfChanges[i][j] = numberOfChanges[i][currentAmount-coins[i-1]] ;
                }
                numberOfChanges[i][j] += numberOfChanges[i-1][j] ;
            }
        }

        return numberOfChanges[coins.length][amount];
    }
}
