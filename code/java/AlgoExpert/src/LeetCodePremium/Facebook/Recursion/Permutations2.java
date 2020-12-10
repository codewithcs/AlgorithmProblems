package LeetCodePremium.Facebook.Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a collection of numbers, nums, that might contain duplicates,
return all possible unique permutations in any order.

Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        traverse(result, new ArrayList<Integer>(), nums, map);
        return result;
    }

    public void traverse(List<List<Integer>> result, List<Integer> current, int[] nums, Map<Integer, Integer> map){
        if(current.size() == nums.length){
            result.add(new ArrayList<>(current));
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){ // Can also iterate over the key set as well. 
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if(count == 0){ // No need to remove the key from the map when the count becomes 0. If we do a remove(), then it would lead to an exception.
                continue;
            }
            current.add(num);
            map.put(num, count-1);
            traverse(result, current, nums, map);
            map.put(num, count);
            current.remove(current.size()-1);
        }
    }
}
