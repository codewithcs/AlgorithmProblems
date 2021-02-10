package LeetCodePremium.Medium;

/*
Given preorder and inorder traversal of a tree,
construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

class TreeNode{
    int val;
    TreeNode left; TreeNode right;
    TreeNode(){}
    TreeNode(int val){ this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val; this.left = left; this.right = right;
    }
}
public class ConstructBinaryTreeFromPreOrderAndInOrderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }
}
