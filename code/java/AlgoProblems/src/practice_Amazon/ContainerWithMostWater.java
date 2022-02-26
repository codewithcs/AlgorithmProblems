package practice_Amazon;

/*
You are given an integer array height of length n.

There are n vertical lines drawn such that the two endpoints
of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container,
such that the container contains the most water.

Return the maximum amount of water a container can store.
Notice that you may not slant the container.
 */

import javafx.util.Pair;

public class ContainerWithMostWater {

    // O(n) solution. 2 Pointer approach.
    public int maxArea2(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length -1 ;

        while(left < right){
            if(height[left] < height[right]){
                maxArea = Math.max(maxArea, height[left] * (right-left));
                left++;
            } else {
                maxArea = Math.max(maxArea, height[right] * (right-left));
                right--;
            }
        }

        return maxArea;
    }

    // Time Limit Exceeded.
    public int maxArea3(int[] height) {
        int maxArea =  0;
        for(int i=0; i< height.length; i++){
            for(int j= i+1; j< height.length; j++){
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j-i) );
            }
        }
        return maxArea;
    }


        // Wrong Approach. See what the problem is.
        public int maxArea(int[] height) {
            int[] leftMaxesIndex = new int[height.length];
            leftMaxesIndex[0] = 0;

            for(int i=1; i< height.length; i++){
                if(height[i] < leftMaxesIndex[i-1]){
                    leftMaxesIndex[i] = i;
                } else {
                    leftMaxesIndex[i] = leftMaxesIndex[i-1];
                }
            }

            int[] rightMaxesIndex = new int[height.length] ;
            rightMaxesIndex[height.length-1] = height.length-1 ;

            for(int i=height.length-2; i>=0 ; i--){
                if(height[i] < leftMaxesIndex[i+1]){
                    rightMaxesIndex[i] = i;
                } else {
                    rightMaxesIndex[i] = rightMaxesIndex[i+1];
                }
            }

            int maxArea = 1;
            for(int i=0; i< height.length; i++) {
                maxArea = Math.max(maxArea, Math.min(height[rightMaxesIndex[i]], height[leftMaxesIndex[i]]) * (rightMaxesIndex[i] - leftMaxesIndex[i]));
            }

            return maxArea;
        }
}