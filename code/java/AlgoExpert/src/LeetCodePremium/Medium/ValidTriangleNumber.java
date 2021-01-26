package LeetCodePremium.Medium;

/*
Given an array consists of non-negative integers,
your task is to count the number of triplets chosen from the array that
can make triangles if we take them as side lengths of a triangle.

Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 */

import java.util.Arrays;

public class ValidTriangleNumber {

    // O(n^3) time and O(1) space.
    public int triangleNumber(int[] nums) {
        int count = 0;

        for(int i=0; i< nums.length-2 ; i++){
            int first = nums[i];
            for(int j=i+1;  j <nums.length-1; j++){
                int second = nums[j];
                for(int k=j+1; k< nums.length; k++){
                    int third = nums[k];
                    if(first + second> third && first+third > second && second+third> first){
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    // Using Binary Search, O(n^2*log n) time and O(log n) space.
    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);

        return 0;
    }

    // Linear Scan, O(n^2) time and O(log n) space.
    public int triangleNumber3(int[] nums) {
        Arrays.sort(nums);

        return 0;
    }
}
