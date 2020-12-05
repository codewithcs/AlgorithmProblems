package LeetCodePremium.Facebook.ArrayAndString;

/*
Given a sorted array nums, remove the duplicates in-place such that
each element appears only once and returns the new length.

Do not allocate extra space for another array, you must do this by
modifying the input array in-place with O(1) extra memory.

Constraints:
0 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums.length ==0) return 0;
        int current = 1;
        int previous = 0;

        while(current < nums.length){
            if(nums[previous] != nums[current]){
                previous++;
                nums[previous] = nums[current];
                current++;
            } else {
                current++;
            }
        }

        int length =0;
        for(int i=0; i<=previous; i++){
            length++;
        }

        return length;
    }

    public int removeDuplicates2(int[] nums) {
        if(nums.length ==0) return 0;
        int current = 1;
        int previous = 0;

        while(current < nums.length){
            if(nums[previous] != nums[current]){
                nums[++previous] = nums[current++];
            } else {
                current++;
            }
        }

        int length =0;
        for(int i=0; i<=previous; i++){
            length++;
        }

        return length;
    }
}
