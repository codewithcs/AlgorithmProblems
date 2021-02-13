package LeetCodePremium.Easy;

import java.util.*;

/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice
and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime?
You may assume the returned list does not count as extra space.
 */
public class FindAllNumbersDisappearedInAnArray {

    // O(n) space and O(n) time.
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(int i=0; i< nums.length ; i++){
            set.add(nums[i]);
        }

        for(int i=1 ; i<= nums.length; i++){
            if(!set.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers2(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        // Iterate over each of the elements in the original array
        for (int i = 0; i < nums.length; i++) {
            // Treat the value as the new index
            int newIndex = Math.abs(nums[i]) - 1;
            // Important to take absolute value as the element can already be negative. 

            // Check the magnitude of value at this new index
            // If the magnitude is positive, make it negative
            // thus indicating that the number nums[i] has
            // appeared or has been visited.
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        for(int num: nums){
            System.out.print(num + ", ");
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that have positive magnitude in the array
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }

        return result;
    }
}
