package LeetCode.Medium;

import java.util.Arrays;

/*
Given an array of n integers nums and an integer target,
find the number of index triplets i, j, k with 0 <= i < j < k < n that
satisfy the condition nums[i] + nums[j] + nums[k] < target.

Follow up: Could you solve it in O(n^2) runtime?

Constraints:
n == nums.length
0 <= n <= 300
-100 <= nums[i] <= 100
-100 <= target <= 100
 */
public class Three_3SumSmaller {

    // O(n^2) + O(n*log n) = O(n^2) time
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums); int count = 0;

        for(int i=0; i< nums.length-2; i++){
            count += twoSumSmaller(i+1, target-nums[i], nums);
        }

        return count;
    }

    public int twoSumSmaller(int startIndex, int target, int[] nums){
        int count = 0;
        int left = startIndex;
        int right = nums.length-1;

        while(left< right){
            if(nums[left] + nums[right] < target){
                count += right-left; // current number of triplets. We add (right-left) as left < right. i< j < k
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    // O(n^2*log n) time.
    public int threeSumSmaller2(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        for (int j = startIndex; j < nums.length - 1; j++) {
            int k = binarySearch(nums, j, target - nums[j]);
            sum += k - j;
        }
        return sum;
    }

    private int binarySearch(int[] nums, int startIndex, int target) {
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2; // Important Condition.
            if (nums[mid] < target) { // Go towards right half.
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
