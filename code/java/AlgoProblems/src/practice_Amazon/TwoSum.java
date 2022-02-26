package practice_Amazon;

import java.util.HashMap;
import java.util.Map;

/*
If we just have to return the elements  instead of indices, we can just use a HashSet.
If we have to return the indices, then we have to use a HashMap.

 */

public class TwoSum {

    // O(n^2) time and O(1) space, Brute Force approach.
    public int[] twoSum(int[] nums, int target) {
        int firstIndex = -1;
        int secondIndex = -1;
        boolean found = false;

        for(int i=0; i< nums.length ; i++){
            int first = nums[i];
            if(found) break;
            for(int j = i+1; j< nums.length; j++){
                if(target-first == nums[j]){
                    firstIndex = i;
                    secondIndex = j;
                    found = true;
                    break;
                }
            }
        }

        return new int[]{firstIndex, secondIndex};
    }

    // O(n) time and space.
    public int[] twoSum2(int[] nums, int target) {
        int firstIndex = -1;
        int secondIndex = -1;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i< nums.length ; i++){
            if(map.containsKey(target-nums[i])){
                firstIndex = map.get(target-nums[i]);
                secondIndex = i;
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[]{firstIndex, secondIndex};
    }

    // O(1) space using 2 pointer approach.

}
