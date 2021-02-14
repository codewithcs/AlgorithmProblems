package LeetCodePremium.Medium;

/*
Given a binary array nums, you should delete one element from it.
Return the size of the longest non-empty subarray containing only 1's in the resulting array.
Return 0 if there is no such subarray.

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int[] lengths = new int[nums.length];
        // lengths[i]: Length of chain ending at index i.
        int maxLength = 0;

        for(int i=0; i< nums.length; i++){
            if(i==0){
                if(nums[i] == 1){
                    lengths[i] = 1;
                }
            } else {
                if(nums[i] == 1){
                    if(nums[i-1] == 1){
                        lengths[i] = 1 + lengths[i-1];
                    } else {
                        lengths[i] = 1;
                    }
                }
            }
            maxLength = Math.max(maxLength, lengths[i]);
        }

        // Have to delete at least 1 element
        if(maxLength == nums.length) {
            return maxLength - 1;
        }

        for(int i=1; i< nums.length-1 ; i++){
            if(nums[i] == 0){
                if(nums[i+1] == 1 && nums[i-1] == 1){
                    int currentLength = 1 + lengths[i-1]; // have to delete the 0, that is why we add just 1 and not 2.
                    for(int j=i+2; j< nums.length; j++){
                        if(nums[j] != 1){
                            break;
                        } else {
                            currentLength++; // continuous chain
                        }
                    }

                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        return maxLength;
    }
}
