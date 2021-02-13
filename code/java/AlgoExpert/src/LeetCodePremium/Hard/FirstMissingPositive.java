package LeetCodePremium.Hard;

import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted integer array nums,
find the smallest missing positive integer.

Constraints:
0 <= nums.length <= 300
-2^31 <= nums[i] <= 2^31 - 1

Follow up: Could you implement an algorithm that runs in O(n) time
and uses constant extra space ?
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if(nums.length ==0 ){
            return 1;
        }

        int max = nums[0];
        for(int i=1; i< nums.length; i++){
            if(nums[i] > max){
                max = nums[i];
            }
        }

        if(max <= 0) {
            return 1;
        }

        Set<Integer> set = new HashSet<>();

        for(int i=0; i< nums.length; i++){
            set.add(nums[i]);
        }

        for(int i=1; i<= max; i++){
            if(!set.contains(i)){
                return i;
            }
        }

        return max+1;
    }

    // O(n) time and O(1) space. Makes use of "uniqueness" and missing elements.
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;

        if(nums.length ==0 ){
            return 1;
        }

        boolean is1Present = false;

        for (int num : nums) {
            if (num == 1) {
                is1Present = true;
                break;
            }
        }

        if(!is1Present) {
            return 1;
        }

        for(int i=0; i< nums.length; i++){
            if(nums[i] <= 0 || nums[i] > n){
                nums[i] = 1;
            }
        }

        for(int i=0; i< nums.length ; i++){
            int newIndex = Math.abs(nums[i]) - 1;

            if(nums[newIndex] > 0){
                nums[newIndex] = -nums[newIndex];
            }
        }

        for(int i=0; i< nums.length; i++){
            if(nums[i] > 0){
                return i+1;
            }
        }

        return n+1 ;
    }
}