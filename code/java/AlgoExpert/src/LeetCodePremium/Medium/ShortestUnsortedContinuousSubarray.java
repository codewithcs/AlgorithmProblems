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

// Good Question, Also on Algo Expert.
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
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
}
