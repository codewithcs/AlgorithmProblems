package LeetCodePremium.Google.ArrayAndString;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Constraints:
n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105
 */

import java.util.Arrays;
// See the stack approach for knowledge.
public class TrappingRainWater {
    // O(n) time and O(1) space.
    public int trap(int[] height) {
        if(height.length == 0 || height.length == 1 || height.length == 2) return 0;

        int total = 0;
        int[] leftMax = new int[height.length];

        Arrays.fill(leftMax, 0);
        int leftMost = 0;

        // Find the left maximum of this building. ( building >= height of this building)
        for(int i=0 ; i<leftMax.length; i++){
            if(height[i] <= leftMost){
                leftMax[i] = leftMost;
            } else {
                leftMax[i] = 0;
                leftMost = height[i];
            }
        }

        int rightMost = 0;
        int[] rightMax = new int[height.length]; // Can avoid this.
        Arrays.fill(rightMax, 0);

        for(int i=rightMax.length-1 ; i >= 0; i--){
            if(height[i] <= rightMost){
                rightMax[i] = rightMost;
            } else {
                rightMax[i] = 0;
                rightMost = height[i];
            }
        }

        for(int i=0; i<height.length; i++){
            int min = Math.min(leftMax[i], rightMax[i]);
            if(min != 0){
                total += min - height[i];
            }
        }

        return total;
    }
}
