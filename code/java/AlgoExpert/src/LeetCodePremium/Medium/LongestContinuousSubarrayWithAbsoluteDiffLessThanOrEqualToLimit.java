package LeetCodePremium.Medium;

import java.util.Collections;
import java.util.PriorityQueue;

/*
Given an array of integers nums and an integer limit, return the size of the
longest non-empty subarray such that the absolute difference between any two
elements of this subarray is less than or equal to limit.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // Brute Force: O(n^4)
    public int longestSubarray1(int[] nums, int limit) {
        int maxSize = 1;

        for(int i=0 ; i< nums.length-1; i++){
            for(int j= i+1; j< nums.length ; j++){
                // Check every pair of [i, j].
                boolean found = true;

                for(int x = i; x < j; x++){
                    for(int y = i+1; y<= j; y++){
                        if(Math.abs(nums[x] - nums[y]) > limit){
                            found = false;
                            break;
                        }
                    }

                    if(!found) {
                        break;
                    }
                }

                if(found){
                    if(maxSize < j - i +1){
                        maxSize = j - i +1;
                    }
                }

            }
        }

        return maxSize;
    }

    // O(n^2) time in the worst case and O(1) space.
    public int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length, left = 0, min = nums[0], max = nums[0], res = 0;

        for (int i = 0; i < len; i++) {
            int diff = Math.max(Math.abs(nums[i] - min), Math.abs(max - nums[i]));
            if (diff <= limit) {
                min = Math.min(nums[i], min);
                max = Math.max(nums[i], max);
                res = Math.max(res, i - left + 1);
            } else {
                int right = i - 1;
                min = max = nums[i];
                while (left < right && Math.abs(nums[i] - nums[right]) <= limit) {
                    min = Math.min(nums[right], min);
                    max = Math.max(nums[right], max);
                    right--;
                }
                left = right + 1;
            }
        }

        return res;
    }



    public int longestSubarray2(int[] nums, int limit) {
        int start = 0;
        int end = 0;
        int res = 1;

        PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>(Collections.reverseOrder());

        while (start <= end && end < nums.length) {
            minQ.offer(nums[end]);
            maxQ.offer(nums[end]);
            int minNum = minQ.peek();
            int maxNum = maxQ.peek();
            if (maxNum - minNum <= limit) {
                end++;
                res = Math.max(res, end - start);
            } else {
                minQ.remove(nums[start]);
                maxQ.remove(nums[start]);
                start++;
                end++; // When ">limit" you also need to change your end, if you do not do so, you will push the same number twice.
            }
        }
        return res;
    }
}
