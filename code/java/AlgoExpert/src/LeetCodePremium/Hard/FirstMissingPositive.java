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
}