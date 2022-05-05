package LeetCode.Facebook.SimilarQuestions;

public class CountNumberOfSubsetsWithAGivenDifference {
    public int count(int[] arr, int difference) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int[][] T = new int[arr.length + 1][sum + 1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = 1;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - arr[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j - arr[i - 1]] + T[i - 1][j];
                } else {
                    T[i][j] = T[i - 1][j];
                }
            }
        }

        if(sum - difference < 0) {
            return 0;
        } else {
            int subsetSum = (sum - difference) / 2;
            return T[arr.length][subsetSum];
        }
    }
}
