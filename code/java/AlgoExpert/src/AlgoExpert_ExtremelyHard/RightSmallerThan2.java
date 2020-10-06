package AlgoExpert_ExtremelyHard;

import java.util.ArrayList;
import java.util.List;

public class RightSmallerThan2 {
    public static List<Integer> rightSmallerThan(List<Integer> array){

        if(array.size() == 0){
            return new ArrayList<Integer>();
        }

        List<Integer> rightSmallerCounts = new ArrayList<>(array);
        int lastIdx = array.size() -1 ;
        SpecialBST bst = new SpecialBST(array.get(lastIdx));
        rightSmallerCounts.set(lastIdx, 0);

        for(int i= array.size()-2 ; i>=0 ; i--){
            bst.insert(array.get(i), i, rightSmallerCounts);
        }
        return rightSmallerCounts;
    }

    static class SpecialBST{
        public int value;
        public int leftSubtreeSize;
        public SpecialBST left;
        public SpecialBST right;

        public SpecialBST(int value){
            this.value = value;
            leftSubtreeSize = 0;
            left = null;
            right = null;
        }

        public void insert(int value, int idx, List<Integer> rightSmallerCounts){
            insertHelper(value, idx, rightSmallerCounts, 0);
        }

        public void insertHelper(int value, int idx, List<Integer> rightSmallerCounts, int numSmallerAtInsertTime){
            if(value < this.value){
                leftSubtreeSize++;
                if(left == null){
                    left = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime); // Set only when new node is created.
                } else {
                    left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubtreeSize;

                if(value > this.value){
                    numSmallerAtInsertTime++;
                }
                if(right == null){
                    right = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            }
        }
    }

}
