package LeetCodePremium.Amazon;

import java.util.HashMap;
import java.util.Map;

/* Return indices of the 2 numbers, Assume 1 solution and may not use the same element twice.
 Can return answer in any order. */
public class TwoSum {
// Easy category.
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5 };
        int[] output = twoSum(array, 6);
        System.out.println(output[0] + ", " + output[1]);
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0 ; i<nums.length ; i++){
            int required = target - nums[i];

            if(map.containsKey(required)){
                return new int[]{ map.get(required), i};
            } else {
                map.put(nums[i], i);
            }

        }

        return new int[] {0, 0};
    }
}
