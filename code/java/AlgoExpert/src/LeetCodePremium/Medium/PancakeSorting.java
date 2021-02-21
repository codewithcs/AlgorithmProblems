package LeetCodePremium.Medium;

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
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();

        // Find the largest number not at correct index.
        int index = findIncorrectIndex(arr);

        while(index != -1){
            reverse(arr, index);
            result.add(index+1);
            reverse(arr, arr[0]-1);
            result.add(arr[0]);
            index = findIncorrectIndex(arr);
        }

        return result;
    }

    public int findIncorrectIndex(int[] arr){
        int max = 0;
        int maxIndex = -1;

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
}
