package LeetCodePremium.Facebook.ArrayAndString;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int swaps = 0;
        while(true){
            swaps = 0;
            int k = nums.length-1;
            int i = k-1;

            while(i>=0){
                if(nums[i] == 0 && nums[k] != 0){
                    int temp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = temp;
                    swaps++;
                }
                i--; k--;
            }

            if(swaps == 0) break;
        }
    }

    // Cannot start with a pointer pointing to start of an array and another at the end of the array.
    // We need to maintain order of other elements.
    // If we swap an element with a 0, then order of other elements does not change.

    // Slow and Fast Pointer.
    // Bring all non k elements to the front of array keeping their relative order same.
    public void moveZeroes2(int[] nums){
        int lastNonZeroFoundAt = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }

        for(int i= lastNonZeroFoundAt ; i< nums.length; i++){
            nums[i] = 0;
        }
    }


    public void numZeroes3(int[] nums){
        for(int lastNonZeroFoundAt = 0, current=0; current < nums.length; current++){
            if(nums[current] != 0){
                swap(nums, lastNonZeroFoundAt, current);
                lastNonZeroFoundAt++;
            }
        }
    }

    public void swap(int[] nums, int i, int j){
        int third = nums[i];
        nums[i] = nums[j]; nums[j] = third;
    }
}
