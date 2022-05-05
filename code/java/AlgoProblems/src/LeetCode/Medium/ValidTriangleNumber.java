package LeetCode.Medium;

/*
Given an array consists of non-negative integers,
your task is to count the number of triplets chosen from the array that
can make triangles if we take them as side lengths of a triangle.

Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 */

import java.util.Arrays;

public class ValidTriangleNumber {

    // O(n^3) time and O(1) space.
    public int triangleNumber(int[] nums) {
        int count = 0;

        for(int i=0; i< nums.length-2 ; i++){
            int first = nums[i];
            for(int j=i+1;  j <nums.length-1; j++){
                int second = nums[j];
                for(int k=j+1; k< nums.length; k++){
                    int third = nums[k];
                    if(first + second> third && first+third > second && second+third> first){
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    // Using Binary Search, O(n^2*log n) time and O(log n) space.
    // We can sort because the numbers can be in any order.
    // The ordering doesn't matter.
    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i=0; i< nums.length-2; i++){
            int k = i+2;

            for(int j=i+1; j<nums.length-1 && nums[i] != 0; j++){
                k = binarySearch(k, nums.length-1, nums, nums[i] + nums[j]);
                count += k-j-1;
            } // We can start from the previous value of k.
        }
        return count;
    }

    public int binarySearch(int left, int right, int[] nums, int value){
        while(right >= left && right< nums.length){
            int mid = left + (right-left)/2;
            if(nums[mid] < value){ // Valid Triangle for this mid.
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }

    // Linear Scan, O(n^2) time and O(log n) space.
    // Sorting takes O(log n) space.
    public int triangleNumber3(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }

    // Similar to 3 Number Sum: Nice Solution.
    // Starting from the end is a clever way.
    public static int triangleNumber4(int[] A) {
        Arrays.sort(A); // O(n*logn)

        int count = 0, n = A.length;

        // O(n^2) time and O(log n) space overall.
        for (int i=n-1; i>=2 ; i--) { // Can start from i=2 as well using the same inner logic.
            int left = 0, right = i-1;
            while (left < right) {
                if (A[left] + A[right] > A[i]) {
                    count += right-left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count;
    }
}
