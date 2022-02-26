package practice_Amazon;

import java.util.*;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeNumberSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        if(nums.length == 0){
            return answer;
        }

        for(int i=0; i< nums.length; i++){
            int firstNumber = nums[i];
            Map<Integer, Integer> map = new HashMap<>();

            for(int j=i+1; j< nums.length; j++){
                int target = -firstNumber-nums[j];
                if(map.containsKey(target)){
                    answer.add(new ArrayList<>(Arrays.asList(i, j, map.get(target))));
                } else {
                    map.put(nums[j], j);
                }
            }

        }

        return answer;
    }
}
