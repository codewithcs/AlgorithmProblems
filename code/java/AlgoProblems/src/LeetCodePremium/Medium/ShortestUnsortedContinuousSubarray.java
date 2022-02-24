package LeetCodePremium.Medium;

/*
Given an integer array nums, you need to find one continuous
subarray that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order.

Return the shortest such subarray and output its length.

Constraints:
1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5

Follow up: Can you solve it in O(n) time complexity?
 */

import java.util.Arrays;
import java.util.Stack;

// Good Question, Also on Algo Expert.
public class ShortestUnsortedContinuousSubarray {

    // O(n) time and O(1) space.
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int start = -1; // important initialization.
        int end = -1;

        // When the array is fully sorted, start and end will remain as -1.

        if(nums.length == 1) {
            return 0;
        }

        for(int i=0; i< nums.length ; i++){
            if(i == 0){
                if(!(nums[i] <= nums[i+1])){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            } else if(i== nums.length-1){
                if(!(nums[i] >= nums[i-1])){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            } else {
                if(!(nums[i] >= nums[i-1] && nums[i] <= nums[i+1])){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            }
        }

        for(int i=0; i< nums.length ; i++){
            if(min < nums[i]){
                start = i;
                break;
            }
        }

        for(int i=nums.length-1; i >=0 ; i--){
            if(max > nums[i]){
                end = i;
                break;
            }
        }

        return start == end ? 0 : end-start+1;
    }

    public int findUnsortedSubArray2(int[] nums){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        if(nums.length == 1) {
            return 0;
        }

        // Check for unsorted numbers, and an unsorted number can be a min or max, so we check both.
        for(int i=0; i< nums.length ; i++){
            if(i == 0){
                if(nums[i] > nums[i+1]){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            } else if(i== nums.length-1){
                if(nums[i] < nums[i-1]){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            } else {
                if(nums[i] < nums[i-1] || nums[i] > nums[i+1]){
                    if(nums[i] > max) {
                        max = nums[i];
                    } else if(nums[i] < min){
                        min = nums[i];
                    }
                }
            }
        }

        for(int i=0; i< nums.length ; i++){
            if(min < nums[i]){
                start = i;
                break;
            }
        }

        for(int i=nums.length-1; i >=0 ; i--){
            if(max > nums[i]){
                end = i;
                break;
            }
        }

        return start == end ? 0 : end-start+1;
    }

    // Brute Force: O(n^3) time and O(1) space.
    public int findUnsortedSubArray3(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j <= nums.length; j++) {
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, prev = Integer.MIN_VALUE;

                for (int k = i; k < j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }
                if ((i > 0 && nums[i - 1] > min) || (j < nums.length && nums[j] < max)) {
                    continue;
                }

                int k = 0;
                while (k < i && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k != i) {
                    continue;
                }
                k = j;
                while (k < nums.length && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k == nums.length) {
                    res = Math.min(res, j - i);
                }
            }
        }
        return res;
    }

    // Better Brute Force: O(n^2) time and O(1) space.
    public int findUnsortedSubArray4(int[] nums) {
        int left = nums.length, right = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    right = Math.max(right, j);
                    left = Math.min(left, i);
                }
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }

    // O(n*log n) time and O(n) space.
    public int findUnsortedSubArray5(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }

        return (end - start >= 0 ? end - start + 1 : 0);
    }

    // O(n) time and O(n) space.
    public int findUnsortedSubArray6(int[] nums) {
        Stack< Integer > stack = new Stack <> ();
        int left = nums.length, right = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }

        return right - left > 0 ? right - left + 1 : 0;
    }
}
