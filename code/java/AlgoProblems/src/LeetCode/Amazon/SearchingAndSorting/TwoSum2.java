package LeetCode.Amazon.SearchingAndSorting;

/*
Given an array of integers that is already sorted in ascending order, find two numbers such that
they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2.

Note:
Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.

Constraints:
2 <= nums.length <= 3 * 104
-1000 <= nums[i] <= 1000
nums is sorted in increasing order.
-1000 <= target <= 1000
 */

public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] index = new int[] {-1, -1};
        int i=0 ; int j = numbers.length-1;

        while( i< j){
            if(numbers[i] + numbers[j] == target){
                index[0] = i;
                index[1] = j; break;
            } else if(numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        index[0] += 1; index[1] += 1;
        return index ;
    }
}
/*
Do we need to consider if numbers[low] + numbers[high] overflows?
The answer is no. Even if adding two elements in the array may overflow,
 because there is exactly one solution, we will reach the solution first.
 */
