package LeetCode.Medium.Tree;

/*
Given a binary tree root, a node X in the tree is named good if
in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tre
 */

public class CountGoodNodesInBinaryTree {
    class TreeNode{
        int val;
        TreeNode left; TreeNode right;
        TreeNode(){}
        TreeNode(int val){ this.val = val ;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val; this.left = left; this.right = right;
        }
    }


    public int goodNodes(TreeNode root) {
        return 0;
    }
}
