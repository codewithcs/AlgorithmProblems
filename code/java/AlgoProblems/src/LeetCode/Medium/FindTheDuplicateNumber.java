package LeetCode.Medium;

/*
Given an array of integers nums containing n + 1 integers where each integer
is in the range [1, n] inclusive.

There is only one repeated number in nums,
return this repeated number.

Constraints:

2 <= n <= 3 * 10^4
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for
precisely one integer which appears two or more times.


Follow up:
How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem without modifying the array nums?
Can you solve the problem using only constant, O(1) extra space?
Can you solve the problem with runtime complexity less than O(n2)?
 */

import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {

    // O(n) time and O(n) space ; O(n*log n) time and O(n) space, sorting takes O(log n) space
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i< nums.length; i++){
            if(set.contains(nums[i])){
                return nums[i];
            } else{
                set.add(nums[i]);
            }
        }
        return 0;
    }

    public int findDuplicate2(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
