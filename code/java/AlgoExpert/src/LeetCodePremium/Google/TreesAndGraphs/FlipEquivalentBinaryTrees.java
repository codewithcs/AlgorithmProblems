package LeetCodePremium.Google.TreesAndGraphs;

/*
For a binary tree T, we can define a flip operation as follows:
Choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only
if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2,
return true if the two trees are flip equivalent or false otherwise.

Constraints:

The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].
 */

public class FlipEquivalentBinaryTrees {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;

        if(root1 == null || root2 == null) return false;

        if(root1.val != root2.val) return false;

        return flip(root1, root2);
    }

    public boolean flip(TreeNode root1, TreeNode root2){
        if(root1.left == null && root2.left == null && root1.right == null && root2.right == null){
            return true;
        }  // Case: 0, 0

        int countLeft = 0;
        if(root1.left != null || root1.right != null){
            countLeft ++;
            if(root1.left != null && root1.right != null ){
                countLeft++;
            }
        }

        int countRight = 0;
        if(root2.left != null || root2.right != null){
            countRight ++;
            if(root2.left != null && root2.right != null ){
                countRight++;
            }
        }

        if(countLeft != countRight) return false;

        // Case: (2, 2)
        if(root1.left!= null && root1.right!= null && root2.left != null && root2.right != null){

            if(root1.left.val == root2.left.val && root1.right.val == root2.right.val){
                return flip(root1.left, root2.left) &&
                        flip(root1.right, root2.right) ;
            }

            if(root1.left.val != root2.left.val){
                invert(root1);
            }

            if(root1.left.val != root2.left.val && root1.right.val != root2.right.val){
                return false;
            }

            return flip(root1.left, root2.left) &&
                    flip(root1.right, root2.right) ;
        }


        // Case: (1, 1)
        if(root1.left != null && root2.left != null){
            if(root1.left.val == root2.left.val){
                return flip(root1.left, root2.left);
            } else {
                return false;
            }
        }

        else if(root1.right != null && root2.right != null){
            if(root1.right.val == root2.right.val){
                return flip(root1.right, root2.right);
            } else {
                return false;
            }
        }

        else {
            if(root1.left == null){
                invert(root1);
            } else {
                invert(root2);
            }

            if(root1.left.val == root2.left.val){
                return flip(root1.left, root2.left);
            } else {
                return false;
            }

        }

    }

    public void invert(TreeNode root){
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
    }
}
