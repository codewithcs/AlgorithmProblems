package LeetCodePremium.Facebook.SimilarQuestions;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Note:
1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i=0; i< nums.length ; i++){
            sum += nums[i];
        }
        if(sum % k != 0) {
            return false;
        }

        sum /= k;

        // find k subsets of sum equal to sum/k.

        return false;
    }
}
