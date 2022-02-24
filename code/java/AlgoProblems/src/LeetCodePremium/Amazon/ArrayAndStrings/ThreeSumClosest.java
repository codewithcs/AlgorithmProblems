package LeetCodePremium.Amazon.ArrayAndStrings;

/*
Given an array nums of n integers and an integer target,
find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.

You may assume that each input would have exactly one solution.
 */

import java.util.Arrays;

public class ThreeSumClosest {

    /// For optimization, find the minDiff, and return target-diff.
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int closestSum = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;

        for(int i=0 ; i< nums.length -1 ; i++ ){
            int left = i+1;
            int right = nums.length -1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if( sum < target) {
                    left++;
                } else if(sum > target) {
                   right--;
                } else {
                    return sum;
                }

               if( Math.abs(sum-target) < minDiff ){
                   minDiff = Math.abs(sum-target);
                   closestSum = sum;
                }
            }
        }

        return closestSum;
    }

    // 2nd approach:
    public int threeSumClosest2(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, size = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < size && diff != 0; ++i) {
            int left = i + 1, right = size - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (sum < target)
                    ++left;
                else
                    --right;
            }
        }
        return target - diff;
    }

}
