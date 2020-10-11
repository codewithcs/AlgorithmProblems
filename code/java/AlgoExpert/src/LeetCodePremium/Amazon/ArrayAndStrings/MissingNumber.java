package LeetCodePremium.Amazon.ArrayAndStrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given an array nums containing n distinct numbers in the range [0, n], return the only number
in the range that is missing from the array.
Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 - Unique numbers
 - 0 <= nums[i] <= n
 - n: length of the array.
 */
public class MissingNumber {

    // O(nlogn) time, O(1) space
    // Since the numbers are distinct and only 1 number is missing, this works.
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        return nums.length*(nums.length+1)/2 - sum;
    }

    // Using HashSet, O(n) time and space..
    public int missingNumber2(int[] nums){
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    // Bit manipulation: O(N) time and O(1) space
    public int missingNumber3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
