package LeetCodePremium.Easy;

/*
Given the array nums consisting of 2n elements in the form [x1, x2,..., xn, y1, y2,..., yn].

Return the array in the form [x1, y1, x2, y2,..., xn, yn].
 */
public class ShuffleTheArray {

    // O(n) time and O(n) space
    public int[] shuffle(int[] nums, int n) {
        int[] result = nums.clone();
        int x=1;
        int y= nums.length%2 == 0 ? nums.length/2 : nums.length/2+ 1 ;
        for(int i=1; i< result.length-1; i++){
            if(i%2 == 1){
                result[i] = nums[y++];
            } else{
                result[i] = nums[x++];
            }
        }
        return result;
    }

    // O(1) space solution:
    // https://leetcode.com/problems/shuffle-the-array/discuss/675956/In-Place-O(n)-Time-O(1)-Space-With-Explanation-and-Analysis

    // Using Bit manipulation.
    public int[] shuffle2(int[] nums, int n) {
        for (int i = 0; i < n; ++i) {
            nums[i + n] |= (nums[i] << 10);
        }
        for (int i = 0; i < n; ++i) {
            nums[i * 2] = (nums[i + n] & 0xFFC00) >> 10;     // 11111111110000000000 == 0xFFC00
            nums[i * 2 + 1] = nums[i + n] & 0x003FF;        // 00000000001111111111 == 0x003FF
        }
        return nums;
    }

    public int[] shuffle3(int[] nums, int n) {
        int k=0, temp=0, j=0;

        for(int i=0 ; i < 2*n ; ){
            k= n + j; //
            temp = nums[k];
            while(k > i+1){ // this is used to swap the element towards right
                nums[k] = nums[--k];
            }
            nums[k] = temp;
            i+=2; //After each correction i need to increamnt by2
            j++; // J is increased by 1
        }

        return nums;
    }

}
