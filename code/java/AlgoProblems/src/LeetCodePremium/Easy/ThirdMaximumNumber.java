package LeetCodePremium.Easy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
Given integer array nums, return the third maximum number in this array.
If the third maximum does not exist, return the maximum number.

Constraints:
1 <= nums.length <= 10^4
-2^31 <= nums[i] <= 2^31 - 1
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 31) -1 == Integer.MAX_VALUE);
    }

    // O(n) time and O(n) space.
    public int thirdMax(int[] nums) {
        // Put the input integers into a HashSet.
        Set<Integer> setNums = new HashSet<>();

        // O(n)
        for (int num : nums) setNums.add(num);

        // Find the maximum, O(n)
        int maximum = Collections.max(setNums);

        // Check whether or not this is a case where we
        // need to return the *maximum*.
        if (setNums.size() < 3) {
            return maximum;
        }

        // Otherwise, continue on to finding the third maximum.
        setNums.remove(maximum); // O(1)
        int secondMaximum = Collections.max(setNums);
        setNums.remove(secondMaximum);
        return Collections.max(setNums);
    }


    // O(n) time and O(1) space.
    public int thirdMax2(int[] nums) {
        Set<Integer> set = new HashSet<>(); // holds <=3 items at a time.

        // O(n) in total.
        for(int num: nums){
            set.add(num); // O(1)
            if(set.size() > 3){
                int min = Collections.min(set); // O(1)
                set.remove(min);
            }
        }

        return set.size() < 3 ? Collections.max(set) : Collections.min(set);
    }

    // O(n) time and O(1) space. Concept of "visited" by using a set.
    public int thirdMax3(int[] nums) {
        Set<Integer> seenMaximums = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            Integer curMaximum = maxIgnoringSeenMaximums(nums, seenMaximums);
            if (curMaximum == null) {
                return Collections.max(seenMaximums);
            }
            seenMaximums.add(curMaximum);
        }

        return Collections.min(seenMaximums);
    }

    private Integer maxIgnoringSeenMaximums(int[] nums, Set<Integer> seenMaximums) {
        Integer maximum = null;
        for (int num : nums) {
            if (seenMaximums.contains(num)) {
                continue;
            }
            if (maximum == null || num > maximum) {
                maximum = num;
            }
        }
        return maximum;
    }

    // Running 3 maximums.
    public int thirdMax4(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        // loop the array
        for(int num  : nums){
            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if(num == max1){
                // skip the equal values.
            }else if(num > max2){
                max3 = max2;
                max2 = num;
            }else if(num == max2){
            }
            else if(num > max3){
                max3 = num;
            }else if(num == max3){
            }
        }

        // if less than three max values
        if(max3 == Long.MIN_VALUE){
            return (int)max1;
        }

        return (int)max3;
    }
}
