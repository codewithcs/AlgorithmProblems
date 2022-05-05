package LeetCode.Medium.JumpGame;

import java.util.Arrays;

import static java.lang.Integer.max;

/*
Given an array of non-negative integers nums, you are initially
positioned at the first index of the array.

Each element in the array represents your maximum jump
length at that position.

Your goal is to reach the last index
in the minimum number of jumps.

You can assume that you can
always reach the last index.

Constraints:
1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
 */
public class JumpGame2 {
    // Here we don't need to worry if we cannot reach the final index
    // It is assumed that we can reach the final index.

    public int jump(int[] nums) {
        int[] minJumps = new int[nums.length];
        Arrays.fill(minJumps, Integer.MAX_VALUE);
        minJumps[0] = 0;

        for(int i=0; i < nums.length ; i++){
            int maxJump = Math.min(i + nums[i], nums.length-1);

            for(int j=i+1; j<=maxJump; j++){
                minJumps[j] = Math.min(minJumps[j], minJumps[i] + 1);
            }
        }

        return minJumps[nums.length-1];
    }

    /*  O(1) space and O(n) time.
        We keep track of the farthest index we can reach.
        We can reach the index currEnd from index 0 with the current value of jumps.
        We are using boundaries which specify the minimum jumps we can take to reach a window
        from the starting index 0.
     */
    public int jump2(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);

            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;

                if (curEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }


    // Easy to understand BFS:
    int jump2(int A[], int n) {
        if(n<2){
            return 0;
        }
        int level=0,currentMax=0,i=0,nextMax=0;

        while(currentMax-i+1>0){		//nodes count of current level>0
            level++;
            for(;i<=currentMax;i++){	//traverse current level , and update the max reach of next level
                nextMax = max(nextMax,A[i]+i);
                if(nextMax>=n-1)return level;   // if last element is in level+1,  then the min jump=level
            }
            currentMax=nextMax;
        }
        return 0;
    }

    public int jump3(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;

        while (position != 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }

        return steps;
    }

    public int jump4(int[] nums) {
        int minJumps = 0;

        int currentEnd = 0;
        int farthest = 0;

        if(nums.length == 1) return 0;

        for(int i=0; i< nums.length; i++){
            farthest = Math.max(farthest, i + nums[i]);

            if(i == currentEnd){
                minJumps++ ;
                currentEnd = farthest;

                if(currentEnd >= nums.length-1){
                    break;
                }
            }
        }

        return minJumps;
    }
}
