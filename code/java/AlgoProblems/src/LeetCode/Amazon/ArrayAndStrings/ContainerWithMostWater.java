package LeetCode.Amazon.ArrayAndStrings;

public class ContainerWithMostWater {

    // O(n^2) solution: Take every pair
    public int maxArea2(int[] height){

        int maxArea = 0;

        for(int i=0 ; i< height.length-1; i++){
            for(int j=i+1 ; j< height.length; j++){
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j-i));
            }
        }

        return maxArea;
    }

    // O(n) solution
    public int maxArea(int[] height) {
        int maxArea = 0;

        int left = 0;
        int right = height.length - 1;

        while(left < right ){
            if(height[left] < height[right]){
                maxArea = Math.max(maxArea, (right-left) * height[left] );
                left++;
            } else {
                maxArea = Math.max(maxArea, (right-left) * height[right] );
                right--;
            }

        }

        return maxArea;
    }

}
