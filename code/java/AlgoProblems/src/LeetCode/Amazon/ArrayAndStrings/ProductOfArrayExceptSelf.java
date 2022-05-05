package LeetCode.Amazon.ArrayAndStrings;

/*
Given an array nums of n integers where n > 1,  return an array output such
that output[i] is equal to the product of all the elements of nums except nums[i].

Solve in O(n) and without division.
 */

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {

    }

/*
Constraint: It's guaranteed that the product of the elements of any prefix or
suffix of the array (including the whole array) fits in a 32 bit integer.

Constant space ? The output array does not count as extra space.
 */
// O(n) space would be using 2 separate lists for left and right products.
// O(n) time and O(1) space
    public int[] productExceptSelf(int[] nums) {

        int[] leftProducts = new int[nums.length];
        Arrays.fill(leftProducts, 1);
        int prod = 1 ;

        for(int i=1; i< nums.length; i++){
            prod *= nums[i-1];
            leftProducts[i] = prod ;
        }

        prod =1;

        for(int i=nums.length-2; i>=0 ; i--){
            prod *= nums[i+1];
            leftProducts[i] *= prod ;
        }

        return leftProducts;
    }
}
