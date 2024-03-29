package LeetCode.Facebook.SimilarQuestions;

// Partition Equal Subset Sum:
// Given a non-empty array nums containing only positive integers,
// find if the array can be partitioned into two subsets such that
// the sum of elements in both subsets is equal.
//
// Constraints:
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100

// Bottom Up approach.
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

    // Top Down with Memoization.
    public boolean canPartition2(int[] nums) {
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd, it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) {
            return false;
        }
        int subSetSum = totalSum / 2;
        int n = nums.length;
        Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
        return dfs(nums, n - 1, subSetSum, memo);
    }

    public boolean dfs(int[] nums, int n, int subSetSum, Boolean[][] memo) {
        // Base Cases
        if (subSetSum == 0) {
            return true;
        }
        if (n == 0 || subSetSum < 0) {
            return false;
        }
        // check if subSetSum for given n is already computed and stored in memo
        if (memo[n][subSetSum] != null) {
            return memo[n][subSetSum];
        }
        boolean result = dfs(nums, n - 1, subSetSum - nums[n - 1], memo) || dfs(nums, n - 1, subSetSum, memo);

        // store the result in memo
        memo[n][subSetSum] = result;

        return result;
    }
}
