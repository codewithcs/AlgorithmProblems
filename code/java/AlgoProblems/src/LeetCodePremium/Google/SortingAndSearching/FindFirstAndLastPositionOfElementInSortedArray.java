package LeetCodePremium.Google.SortingAndSearching;

/*
Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity ?

Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */

public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int i = -1;
        int j = -1;
        boolean firstOccurence = false;

        for(int k=0 ; k<nums.length ; k++){
            if(!firstOccurence){
                if(nums[k] == target){
                    i = k;
                    j = k;
                    firstOccurence = true;
                }
            } else {
                if(nums[k] == target){
                    j = k;
                }
            }

        }
        return new int[] {i, j};
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target) {
        int n = nums.length;
        int first_pos = n;
        int low = 0; int high = n-1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                first_pos = mid;
                high = mid - 1;
            }
            else {
                low = mid+1;
            }
        }

        return first_pos;
    }

    public int[] searchRange2(int[] nums, int target) {
        int first = extremeInsertionIndex(nums, target);
        int last = extremeInsertionIndex(nums, target+1) -1;

        if(first <= last){
            return new int[]{ first, last };
        }

        return new int[] { -1, -1 };
    }

}
