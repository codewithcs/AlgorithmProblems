package LeetCodePremium.Facebook.SimilarQuestions;

// Partition Equal Subset Sum:
// Given a non-empty array nums containing only positive integers,
// find if the array can be partitioned into two subsets such that
// the sum of elements in both subsets is equal.
//
// Constraints:
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
public class EqualSumPartitionProblem {
    public boolean canPartition(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
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
        return T[arr.length][sum];
    }
}
