package LeetCodePremium.Google.ArrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Implement next permutation, which rearranges numbers into the
lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the
lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 100
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {

    }

    public static void main(String[] args) {
        nextPermutation2(new int[]{ 3, 2, 1} );
    }
    public static void nextPermutation2(int[] permutation) {
        int length = permutation.length;
        int lastIndex = length - 1;

        List<Integer> list = new ArrayList<>() ;

        list.add(permutation[lastIndex]) ;
        boolean hasDecreasing = false ; // if the array has some decreasing sequence.

        for( int i = lastIndex ; i >=1 ; i-- ) {
            if( permutation[i] <= permutation[i-1] ) {
                hasDecreasing = true;
                continue;
            }

            System.out.println("i is : " + i);

            int indexToSwapWith = lastIndex;  // in case there was no decreasing sequence, then swap with last index.

            for(int j = lastIndex ; j>=i ; j-- ) {
                if(permutation[i-1] < permutation[j]) {
                    indexToSwapWith = j;
                    break;
                } // have to swap with a number just greater than permutation[i-1]
            }

            System.out.println("index to swap with is : " + indexToSwapWith);
            System.out.println("i is : " + i);

            int temp = permutation[i-1] ;
            permutation[i-1] = 	permutation[indexToSwapWith] ;
            permutation[indexToSwapWith] = temp;

            if(hasDecreasing) {
                Arrays.sort(permutation, i, permutation.length);
            }
            break;
        }

        // Print the next permutation.
        for(int i=0 ; i<permutation.length ; i++) {
            System.out.print(permutation[i] + " ");
        }

        System.out.println();
    }

    public void nextPermutation3(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
