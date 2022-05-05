package LeetCode.Medium;

/*
Given a binary array, find the maximum number of consecutive 1s in
this array if you can flip at most one 0.

Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

Follow up:
What if the input numbers come in one by one as an infinite stream ?
In other words, you can't store all numbers coming from the stream as
it's too large to hold in memory. Could you solve it efficiently?
 */
public class MaxConsecutiveOnesII {

    // O(n^2) time and O(1) space.
    public int findMaxConsecutiveOnes1(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            // check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // # update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }

    // Sliding window 1.
    public int findMaxConsecutiveOnes2(int[] nums) {
        int longestSequence = 0;
        int left = 0;
        int right = 0;
        int numZeroes = 0;

        // while our window is in bounds
        while (right < nums.length) {

            // add the right most element into our window
            if (nums[right] == 0) {
                numZeroes++;
            }

            // if our window is invalid, contract our window
            while (numZeroes == 2) {
                if (nums[left] == 0) {
                    numZeroes--;
                }
                left++;
            }

            // update our longest sequence answer
            longestSequence = Math.max(longestSequence, right - left + 1);

            // expand our window
            right++;
        }
        return longestSequence;
    }

    // Sliding window 2.
    public int findMaxConsecutiveOnes3(int[] nums) {
        int left=0; int right=0; int k= 1;

        while(right < nums.length){
            if(nums[right] == 0){
                k--;
            }

            if(k< 0){
                if(nums[left] == 0) k++;
                left++;
            }
            right++;
        }

        return right - left;
    }
}
