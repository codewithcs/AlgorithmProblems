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
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                list.add(num);
            }
        }
        return list;
    }

    // O(n) time and O(1) space.
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i=0 ; i< nums.length ; i++){
            int newIndex = Math.abs(nums[i]) - 1;
            if(nums[newIndex] < 0 ) {
                list.add(newIndex + 1);
            } else {
                nums[newIndex] = -nums[newIndex];
            }
        }
        return list;
    }
}
