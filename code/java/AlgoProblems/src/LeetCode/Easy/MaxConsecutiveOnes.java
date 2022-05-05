package LeetCode.Easy;

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

    // Simpler version of above, both are O(n) time and O(1) space.
    public int findMaxConsecutiveOnes2(int[] nums) {
        int length = 0;
        int max = 0;
        for(int i=0; i< nums.length; i++){
            if(nums[i] == 1){
                length++;
            } else {
                max = Math.max(max, length);
                length = 0;
            }
        }

        max = Math.max(max, length);
        return max;
    }

    // Bit different logic.
    public int findMaxConsecutiveOnes3(int[] nums) {
        int max_ones, current_ones;
        max_ones = current_ones = 0;

        for (int number: nums) {
            if (number == 1) {
                current_ones++;
                if (current_ones > max_ones) max_ones = current_ones;
            } else {
                current_ones = 0;
            }
        }

        return max_ones;
    }
}
