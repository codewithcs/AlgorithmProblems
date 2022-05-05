package LeetCode.Facebook.Recursion;

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

// It takes N steps to generate a single permutation. Since there are in total N! possible permutations, at most it would take
// us N * N! steps to generate all permutations, simply assuming there is no overlapping effort (which is not true).
// O(N) space for Hash table and O(N*N!) space to hold the results.
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
    /*
    We need to worry about [1, 1, 2] being repeated twice as using the above algorithm it will only be generated once.
     */
}
