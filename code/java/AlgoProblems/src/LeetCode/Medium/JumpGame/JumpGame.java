package LeetCode.Medium.JumpGame;

/*
You are given an integer array nums.
You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Constraints:
1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5

Follow Up:
What if negative integers are also present?

 */
public class JumpGame {

    // DP approach.
    public boolean canJump(int[] nums) {
        boolean[] canReach = new boolean[nums.length];

        for(int i=0; i< nums.length ; ) {
            int newIndex = i + nums[i];

            if (newIndex >= nums.length - 1) {
                return true;
            }

            for (int j = newIndex; j < nums.length && j >= i; j--) {
                int maxIndex = j + nums[j];

                if (maxIndex >= nums.length - 1) {
                    return true;
                }

                for (int k = maxIndex; k >= j; k--) {
                    canReach[k] = true;
                }
            }

            if (nums[newIndex] != 0) {
                i = newIndex;
            } else {
                i++;
            }

            if (!canReach[i]) { // Important Check.
                // can put this check after line 46 as well.
                // this is because canReach can be false
                // only when nums[newIndex] is 0.
                // And i points to the next element which cannot be reached.
                break;
            }
        }

        return false;
    }

    // Alternative DP.
    // canReach[i] means that we can reach the index i.
    // O(n^2) time and O(n) space.
    public boolean canJump2(int[] nums) {
        boolean[] canReach = new boolean[nums.length];
        canReach[0] = true;
        for(int i=0; i< nums.length ; ) {
            if(!canReach[i]) {
                break;
            }

            int newIndex = i + nums[i];

            if (newIndex >= nums.length - 1) {
                return true;
            }

            for (int j = newIndex; j < nums.length && j >= i; j--) {
                int maxIndex = j + nums[j];

                if (maxIndex >= nums.length - 1) {
                    return true;
                }

                for (int k = maxIndex; k >= j; k--) {
                    canReach[k] = true;
                }
            }

            if (nums[newIndex] != 0) {
                i = newIndex;
            } else {
                i++;
            }
        }

        return false;
    }

    // Greedy Solution.
    // Check the furthest index we can jump to.
    public boolean canJump3(int[] nums) {
        int furthest = 0;

        for(int i=0; i< nums.length; i++){
            if(i > furthest) {
                return false;
            }
            furthest = Math.max(furthest, i+nums[i]);
        }

        return true;
    }
}
