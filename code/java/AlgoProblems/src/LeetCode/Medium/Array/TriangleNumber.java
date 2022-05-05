package LeetCode.Medium.Array;

/*
Given an integer array nums,
return the number of triplets chosen from the array
that can make triangles if we take them
as side lengths of a triangle.

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000
 */

import java.util.Arrays;

public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        // O(n*log n) and Recursive space.
        Arrays.sort(nums);

        int count = 0;

        // O(n^2) time.
        for(int i=nums.length-1; i>=2 ; i--){
            int left = 0;
            int right = i-1;

            while(left < right){
                if(nums[i] < nums[left] + nums[right]){
                    count = count + right - left;
                    right--;
                } else {
                    left++;
                }
            }

        }

        return count;
    }
}
