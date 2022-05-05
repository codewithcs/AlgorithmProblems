package LeetCode.Facebook.SimilarQuestions;

/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Constraints:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    public static void main(String[] args) {
    }

    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
            return;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (S > sum || S < -sum) {
            return 0;
        }

        int[][] dp = new int[nums.length][sum*2+1];

        dp[0][nums[0]+sum] = 1; dp[0][-nums[0]+sum] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i-1][j+sum] != 0) {
                    int n = nums[i];
                    dp[i][j+sum+n] += dp[i-1][j+sum]; // to push the value >=0 we add +sum as indices are non-negative.
                    dp[i][j+sum-n] += dp[i-1][j+sum];
                }
            }
        }

        return dp[nums.length-1][S+sum];
    }

    public int findTargetSumWays3(int[] nums, int S) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        if( S > sum || S < -sum){
            return 0;
        }

        int[][] dp = new int[nums.length+1][sum*2+1];
        dp[0][0+sum] = 1; // shifting to the right by "sum" as indices >= 0.

        for(int i=1; i<=nums.length; i++){
            for(int j=0; j<=sum*2; j++){
                int n = 0;
                if (j-nums[i-1] >=0 ){
                    n += dp[i-1][j-nums[i-1]];
                }

                if (j+nums[i-1] <= 2*sum){
                    n += dp[i-1][j+nums[i-1]];
                }

                dp[i][j] = n;
            }
        }

        return dp[nums.length][S+sum];
    }
}
