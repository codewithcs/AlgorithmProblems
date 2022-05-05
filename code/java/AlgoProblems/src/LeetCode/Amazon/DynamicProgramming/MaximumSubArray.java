package LeetCode.Amazon.DynamicProgramming;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Follow up: If you have figured out the O(n) solution, try coding another solution using the
divide and conquer approach, which is more subtle.
 */

public class MaximumSubArray {

    // O(n) solution.
    public int maxSubArray(int[] nums) {
        int[] maxSum = new int[nums.length];
        int max = nums[0];
        maxSum[0] = nums[0];

        for(int i=1 ; i<nums.length ; i++){
            maxSum[i] = Math.max(nums[i], maxSum[i-1] + nums[i]);
            max = Math.max(max, maxSum[i]);
        }

        return max;
    }

    // Divide And Conquer
    public int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for(int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    // Starting Point.
    public int maxSubArray2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
}
