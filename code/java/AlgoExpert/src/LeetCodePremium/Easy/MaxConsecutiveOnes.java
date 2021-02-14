package LeetCodePremium.Easy;

/*
Given a binary array, find the maximum number
of consecutive 1s in this array.

Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int length = 0;
        int max = 0;
        for(int i=0; i< nums.length; i++){
            if(i==0){
                length = nums[i] == 1 ? 1 : 0;
            } else {
                if(nums[i] == 0){
                    length = 0;
                } else {
                    if(nums[i-1] == 1){
                        length++;
                    } else {
                        length = 1;
                    }
                }
            }

            max = Math.max(max, length);
        }
        return max;
    }
}
