package LeetCodePremium.Facebook.SortingAndSearching;

/*
A peak element is an element that is strictly greater than its neighbors.
Given an integer array nums, find a peak element, and return its index.
If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞.

Constraints:
1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.

Follow up: Could you implement a solution with logarithmic complexity?
 */
public class FindPeakElement {
    // O(n) time
    public int findPeakElement(int[] nums) {
        if(nums.length == 1 || nums[0] > nums[1]) return 0;

        if(nums[nums.length-1] > nums[nums.length-2]) return nums.length-1;

        for(int i=1; i< nums.length-1 ; i++){
            if(nums[i] > nums[i-1] && nums[i] > nums[i+1]) return i;
        }

        return -1; // ask the interviewer what to return here.
    }

    // O(logn) time ? 
    public int findPeakElement2(int[] nums){
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end-start)/2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start; // can also return end here as nums.length >=1
    }
}
