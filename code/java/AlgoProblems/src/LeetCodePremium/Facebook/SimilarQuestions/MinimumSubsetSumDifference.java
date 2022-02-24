package LeetCodePremium.Facebook.SimilarQuestions;

public class MinimumSubsetSumDifference {
    public int canPartition(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        boolean[][] T = new boolean[arr.length + 1][sum + 1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - arr[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j - arr[i - 1]] || T[i - 1][j];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for(int i=0; i<= sum/2; i++){
            if(T[arr.length][i]){
                min = Math.min(min, sum - 2*i);
            }
        }

        return min;
    }
}
