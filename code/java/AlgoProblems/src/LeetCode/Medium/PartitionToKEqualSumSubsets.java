package LeetCode.Medium;

/*
Given an integer array nums and an integer k,
return true if it is possible to divide this array into
k non-empty subsets whose sums are all equal.

Constraints:
1 <= k <= nums.length <= 16
1 <= nums[i] <= 10^4
The frequency of each element is in the range [1, 4].
 */

import java.util.Arrays;

/*
Good Post:
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews
https://stackoverflow.com/questions/31575691/what-is-a-bitmask-and-a-mask/31575755#31575755
 */

public class PartitionToKEqualSumSubsets {

    // Approach 1: TLE.
    public boolean canPartitionKSubsets3(int[] arr, int k) {
    /* Get the sum of all items in the array. We will use this to
      divide by k to get the sum that each bucket needs to hit */
        int sumOfAllArrayItems = 0;
        for (int num : arr) {
            sumOfAllArrayItems += num;
        }

    /*
      1.) k can not be negative or 0 because we can not fill 0
      or negative buckets
      2.) k can not be greater than the length of the array,
      we can't partition more buckets than there are elements
      in the array
      3.) sumOfAllArrayItems % k must be 0. If it is not then
      we would have to have to fill buckets to a floating point
      sum which would be impossible with only integers
    */
        if (k <= 0 || k > arr.length || sumOfAllArrayItems % k != 0) {
            return false;
        }

        return canPartition(0, arr, new boolean[arr.length], k, 0, sumOfAllArrayItems / k);
    }

    private boolean canPartition(int iterationStart, int[] arr, boolean[] used, int k,
                                 int inProgressBucketSum, int targetBucketSum) {
    /*
      If we have filled all k - 1 buckets up to this point and we are now on
      our last bucket, we can stop and be finished.

      Example:
      arr = [4, 3, 2, 3, 5, 2, 1]
      k = 4
      targetBucketSum = 5
      If we get to the point in our recursion that k = 1 that means we have filled
      k - 1 buckets (4 - 1 = 3). 3 buckets have been filled, each a value of 5 meaning
      we have "eaten" 15 "points" of value from an array that sums to 20.
      This means we have 5 "points" to extract from the array and that for sure will fill
      the last bucket. So at the point there is 1 bucket left, we know we can complete the
      partitioning (we don't have to though, we just want to know whether we can or not).
    */
        if (k == 1) {
            return true;
        }

    /* Bucket full. continue the recursion with k - 1 as the new k value, BUT the
      targetBucketSum stays the same. We just have 1 less bucket to fill.
    */
        if (inProgressBucketSum == targetBucketSum) {
            return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
        }

    /*
      Try all values from 'iterationStart' to the end of the array ONLY if:

      1.) They have not been used up to this point in the recursion's path
      2.) They do not overflow the current bucket we are filling
    */
        for (int i = iterationStart; i < arr.length; i++) {
            if (!used[i] && inProgressBucketSum + arr[i] <= targetBucketSum) { // 2nd condition avoids some recursive calls.
                used[i] = true;
        /*
          See if we can partition from this point with the item added
          to the current bucket progress
        */
                if (canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
                    return true;
                }
                used[i] = false;
            }
        }

    /*
      Partitioning from this point is impossible. Whether we are at the
      top level of the recursion or deeper into it.
    */
        return false;
    }


    // Approach 2:
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length < k) return false;
        int sum = 0, max = Integer.MIN_VALUE;
        for(int num : nums){
            sum += num;
            max = Math.max(max, num);
        }
        if(sum % k != 0 || max > sum / k) return false;
        sum /= k;
        return k == 1 || dfs(nums, 0, k, 0, sum, new boolean[nums.length], 0);
    }

    // Optimized Approach.
    private boolean dfs(int[] nums, int count, int k, int cur, int sum, boolean[] visited, int start){
        if(cur == sum){
            if(++count == k - 1) return true;
            else{
                return dfs(nums, count, k, 0, sum, visited, 0);
            }
        }else if(cur < sum){
            for(int i = start; i < nums.length; i++){
                if(visited[i]) continue;
                visited[i] = true;
                if(dfs(nums, count, k, cur + nums[i], sum, visited, i + 1)) return true;
                visited[i] = false;
            }
        }
        return false;
    }


    // Approach 3.
    public boolean canPartitionKSubsets2(int[] A, int k) {
        if (k > A.length) return false;
        int sum = 0;
        for (int num : A) sum += num;
        if (sum % k != 0) return false;

        boolean[] visited = new boolean[A.length];

        Arrays.sort(A);

        return dfs(A, 0, A.length - 1, visited, sum / k, k);
    }

    public boolean dfs(int[] A, int sum, int st, boolean[] visited, int target, int k) {
        if (k == 0) {
            return true;
        }
        if (sum == target && dfs(A, 0, A.length - 1, visited, target, k - 1)) {
            return true;
        }
        for (int i = st; i >= 0; --i) {
            if (!visited[i] && sum + A[i] <= target) {
                visited[i] = true;
                if (dfs(A, sum + A[i], i - 1, visited, target, k)) {
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }


    // Fastest Solution: Using bitmasking
    int n, subsetSum;
    int[] memo = new int[1 << 16];
    public boolean canPartitionKSubsets4(int[] nums, int k) {
        n = nums.length;
        subsetSum = Arrays.stream(nums).sum() / k;
        Arrays.fill(memo, -2);
        return dp(nums, (1 << n) - 1) == 0;
    }

    int dp(int[] nums, int mask) {
        if (memo[mask] != -2) {
            return memo[mask];
        }
        if (mask == 0) {
            return 0;
        }

        for (int i = 0; i < n; ++i) {
            if (((mask >> i) & 1) == 0){
                continue;
            }
            int newMask = mask ^ (1 << i);
            int remain = dp(nums, newMask);
            if (remain == -1) {
                continue;
            }
            if (remain + nums[i] <= subsetSum) {
                return memo[mask] = (remain + nums[i]) % subsetSum;
            }
        }

        return memo[mask] = -1;
    }
}