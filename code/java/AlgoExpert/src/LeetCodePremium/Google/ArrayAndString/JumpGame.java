package LeetCodePremium.Google.ArrayAndString;

/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

Constraints:
1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5
 */

public class JumpGame {
    enum Index{
        GOOD, BAD, UNKNOWN
    }

    // Values cannot be -ve, which means we cannot be stuck in a cycle but we can get stuck at the index with value 0.
    public boolean canJump(int[] nums) {
        if(nums.length == 1){
            return true;
        }
        return check(0, nums[0], nums);
    }

    // Time Limit Exceeded: O(2^n) time and O(n) space (Stack frames).
    // There are 2^n (upper bound) ways of jumping from the first position to the last,
    // where n is the length of array nums.
    public boolean check(int currentIdx, int value, int[] nums){
        if(currentIdx == nums.length-1){
            return true;
        }

        if(value == 0){
            return false;
        }

        boolean b1 = false;

        for(int i=1; i<= value ; i++){ // Minor optimization: Go in decreasing order.
            int nextIdx = currentIdx + i;
            if(nextIdx >= 0 && nextIdx < nums.length){
                b1 = b1 || check(nextIdx , nums[nextIdx], nums); // if b1 is true, return true.
            }
        }

        return b1;
    }


    // Top Down DP.
    Index[] memo;

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump3(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }

    // Solving Bottom Up. Can also solve by using a cache and checking cache[0] == Index.GOOD.
    public boolean canJump4(int[] nums) {
        int lastPosition = nums.length - 1;

        for(int j = nums.length-1; j>=0 ; j--){
            if(j + nums[j] >= lastPosition){
                lastPosition = j;
            }
        }

        return lastPosition == 0;
    }
}
