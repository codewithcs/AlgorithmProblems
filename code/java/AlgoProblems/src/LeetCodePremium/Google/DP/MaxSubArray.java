package LeetCodePremium.Google.DP;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int max = sums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i] >= nums[i] + sums[i-1]){
                sums[i] = nums[i];
            } else {
                sums[i] = nums[i] + sums[i-1];
            }
            max = Math.max(max, sums[i]);
        }

        return max;
    }
}
