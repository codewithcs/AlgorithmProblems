package LeetCodePremium.Medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?
 */
public class FindAllDuplicatesInAnArray {

    // O(n) space and O(n) time. 
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i=0 ; i< nums.length ; i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
            } else {
                list.add(nums[i]);
            }
         }
        return list;
    }
}
