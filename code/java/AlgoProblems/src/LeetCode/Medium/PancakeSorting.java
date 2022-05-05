package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of integers arr, sort the array by performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 1 <= k <= arr.length.
Reverse the sub-array arr[0...k-1] (0-indexed).

For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3,
we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.

Return an array of the k-values corresponding to a sequence of pancake flips that sort arr.
Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

Constraints:
1 <= arr.length <= 100
1 <= arr[i] <= arr.length
All integers in arr are unique (i.e. arr is a permutation of the integers from 1 to arr.length).
 */

public class PancakeSorting {

    // gets accepted, 5%.
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();

        // Find the largest number not at correct index.
        int index = findIncorrectIndex(arr);

        while(index != -1){
            reverse(arr, index);
            result.add(index+1);
            result.add(arr[0]); // Do this before the 2nd reversal as we are adding an array element which can
                                // change after the 2nd reversal.
            reverse(arr, arr[0]-1);
            index = findIncorrectIndex(arr);
        }

        // If index is -1 then it means that every element is at their
        // correct position and hence the array is sorted.
        return result;
    }

    // Find the incorrect index of the maximum element in the array.
    public int findIncorrectIndex(int[] arr){
        int max = 0;
        int maxIndex = -1;

        // We calculate both max number and its index.
        // See approach 2.
        for(int i=0 ; i<= arr.length-1 ; i++){
            if(arr[i] != i+1){
                if(arr[i] > max){
                    max = arr[i];
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }

    public void reverse(int[] arr, int index){
        int left =0 ;
        int right = index;
        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++; right--;
        }
    }

    // Approach 2: array value in [1, arr.length], 100%
    // This approach makes use of the given constraint.
    // We already know the max number, we just need to find its index.
    public List<Integer> pancakeSort2(int[] arr) {
        List<Integer> result = new ArrayList<>();

        for(int i= arr.length; i>=1; i--){
            int index = findIndex(i, arr);

            if(index != i-1){ // if i is at wrong index.
                flip(arr, index+1);
                result.add(index+1);
                flip(arr, i);
                result.add(i);
            }
        }

        return result;
    }

    public void flip(int[] arr, int k){
        int start = 0; int end = k-1;
        while(start< end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++; end--;
        }
    }

    public int findIndex(int num, int[] arr){
        int index = -1;
        for(int i=0; i< arr.length; i++){
            if(arr[i] == num ){
                index = i;
                break;
            }
        }
        return index;
    }

    /*
    //----------------------------------------------------------------------------------------------------------
    /// This is not Pancake Sort. [1, 5, 6, 7, 8, 2, 3, 4], but sorts the array.
    public static List<Integer> notPancakeSort2(int[] arr) {
        List<Integer> result = new ArrayList<>();

        // First I reverse sort the numbers and then do a final flip of the whole array.
        for(int i=0 ; i< arr.length ; i++){
            int maxNumberIndex = findMaximumIndex(i, arr);
            if(maxNumberIndex == -1 ) {
                continue;
            }
            reverse(arr, i, maxNumberIndex);
            result.add(maxNumberIndex+1);
        }

        reverse(arr, 0, arr.length-1);
        result.add(arr.length);
        return result;
    }

    public static int findMaximumIndex(int start, int[] arr){
        int max = arr[start];
        int maxIndex = -1;

        for(int i=start+1 ; i<= arr.length-1 ; i++){
            if( i + arr[i] != arr.length) {
                if(arr[i] > max){
                    max = arr[i];
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }

    public static void reverse(int[] arr, int start, int index){
        int left = start ;
        int right = index;

        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++; right--;
        }
    }
    //----------------------------------------------------------------------------------------------------------

     */
}