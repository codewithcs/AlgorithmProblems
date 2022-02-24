package AlgoExpert_ExtremelyHard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RightSmallerThan {
    // O(n^2) time | O(n) space where n is the length of the array.
    public static List<Integer> rightSmallerThan(List<Integer> array){
        List<Integer> output = new ArrayList<>(Collections.nCopies(array.size(), 0));

        for(int i=0 ; i<array.size()-1 ; i++){
            for(int j=i+1; j< array.size(); j++){
                if(array.get(j) < array.get(i)){
                    output.set(i, output.get(i) + 1); // can also use add by having a local var for count and keeping the list empty initially.
                }
            }
        }

        return output;
    }

    // Solution 2:
    /*
    Average Case: O(n*logn) time, O(n) space (bst is balanced)
    Worst Case: When the created bst is like a linked list, O(n^2) time | O(n) space
     */
    public static List<Integer> rightSmallerThan2(List<Integer> array){
        if(array.size() == 0){
            return new ArrayList<>();
        }

        int lastIdx = array.size()-1;
        // Set the root node of the bst.
        SpecialBST bst = new SpecialBST(array.get(lastIdx), lastIdx, 0);

        for(int i=array.size()-2 ; i>=0 ; i--){
            bst.insert(array.get(i), i);
        }

        List<Integer> rightSmallerCounts = new ArrayList<>(array);
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
                if(value > this.value){
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