package AlgoExpert_Easy;

import java.util.ArrayList;
import java.util.List;

public class BranchSums {

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
// Do a pre-order traversal.
    public static List<Integer> branchSums(BinaryTree root) {
        // Write your code here.
        List<Integer> sums = new ArrayList<>();
         branchSums(root, sums, root.value);
         return sums;
    }

    public static void branchSums(BinaryTree root, List<Integer> sums, int currentSum){

        // add to the list when it is a leaf node.
        if(root.left == null && root.right == null){
            sums.add(currentSum);
            return ;
        }

        if(root.left != null)
        branchSums(root.left, sums, currentSum + root.left.value);

        if(root.right != null)
        branchSums(root.right, sums, currentSum + root.right.value);
    }

    public static void branchSums2(BinaryTree root, List<Integer> sums, int currentSum){

        if(root == null) return ;

        currentSum += root.value;

        if(root.left == null && root.right == null){
            sums.add(currentSum);
            return ;
        }

        branchSums2(root.left, sums, currentSum );
        branchSums2(root.right, sums, currentSum );
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
