package LeetCode.Google.SortingAndSearching;

import java.util.ArrayList;
import java.util.List;

/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number
of smaller elements to the right of nums[i].

Constraints:
0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 */

// Average Case: O(n*logn) time, Worst Case: O(n^2) time and O(n) space.
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0){
            return new ArrayList<>();
        }

        int lastIdx =  nums.length-1;
        // Set the root node of the bst.
        SpecialBST bst = new SpecialBST(nums[lastIdx], lastIdx, 0);

        for(int i=nums.length-2 ; i>=0 ; i--){
            bst.insert(nums[i], i);
        }

        List<Integer> rightSmallerCounts = new ArrayList<>();
        for(int i=0 ; i<nums.length;i++){
            rightSmallerCounts.add(0);
        }

        // Create the list of same size as of array. Could initialize it with anything.
        getRightSmallerCounts(bst, rightSmallerCounts);
        return rightSmallerCounts;
    }

    public static void getRightSmallerCounts(SpecialBST bst, List<Integer> rightSmallerCounts){
        if(bst == null){
            return ;
        }
        rightSmallerCounts.set(bst.idx, bst.numSmallerAtInsertTime);
        getRightSmallerCounts(bst.left, rightSmallerCounts);
        getRightSmallerCounts(bst.right, rightSmallerCounts);
    }

    static class SpecialBST{
        public int value;
        public int idx;
        public int numSmallerAtInsertTime;
        public int leftSubTreeSize;
        public SpecialBST left;
        public SpecialBST right ;

        public SpecialBST(int value, int idx, int numSmallerAtInsertTime){
            this.value = value;
            this.idx = idx;
            this.numSmallerAtInsertTime = numSmallerAtInsertTime;
            leftSubTreeSize = 0;
            left = null ;
            right = null;
        }

        public void insert(int value, int idx){
            insertHelper(value, idx, 0);
        }

        public void insertHelper(int value, int idx, int numSmallerAtInsertTime){
            if(value < this.value){
                leftSubTreeSize++; // increase the left subtree size of the current node. s
                if(left == null){
                    left = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else{
                    left.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubTreeSize; // need to separate for values equal and strictly greater than the current node.
                // if equal, then for the current value, just add the left subtree size.
                if(value > this.value){ // strictly greater.
                    numSmallerAtInsertTime++;
                }
                if(right == null ){
                    right = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            }
        }
    }
}
