package LeetCode.Medium;

/*
Given inorder and postorder traversal of a tree,
construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInOrderAndPostOrderTraversal {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder.length-1, 0, inorder.length - 1, postorder, inorder);
    }

    public  TreeNode helper(int postEnd, int inStart, int inEnd, int[] postorder, int[] inorder) {
        // Base Case.
        if (postEnd < 0 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }

        root.left = helper(postEnd - (inEnd-inIndex) -1, inStart, inIndex - 1, postorder, inorder);
        root.right = helper(postEnd-1, inIndex + 1, inEnd, postorder, inorder);
        return root;
    }

    // Using a Map.
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< inorder.length ; i++){
            map.put(inorder[i], i);
        }

        return helper2(inorder.length-1, 0, inorder.length - 1, postorder, inorder, map);
    }

    public  TreeNode helper2(int postEnd, int inStart, int inEnd, int[] postorder, int[] inorder, Map<Integer, Integer> map) {
        // Base Case.
        if (postEnd < 0 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = map.get(root.val); // Getting the index using a map.

        root.left = helper2(postEnd - (inEnd-inIndex) -1, inStart, inIndex - 1, postorder, inorder, map);
        root.right = helper2(postEnd-1, inIndex + 1, inEnd, postorder, inorder, map);
        return root;
    }
}
