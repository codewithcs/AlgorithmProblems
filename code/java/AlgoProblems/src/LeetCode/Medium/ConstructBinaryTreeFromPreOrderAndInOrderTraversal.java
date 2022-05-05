package LeetCode.Medium;

/*
Given pre order and inorder traversal of a tree,
construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

import java.util.HashMap;
import java.util.Map;

class TreeNode{
    int val;
    TreeNode left; TreeNode right;
    TreeNode(){}
    public TreeNode(int val){ this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val; this.left = left; this.right = right;
    }
}

public class ConstructBinaryTreeFromPreOrderAndInOrderTraversal {

    // Approach 1: Simple Recursive Solution.
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    // preStart is the root pointer in pre order traversal.
    // We need inStart and inEnd to separate left and right sub trees.
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // Base Case.
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }

        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        // inIndex-inStart+1 is the length of the left subtree.
        // right subtree root will start from the index = preStart + (length of left sub tree)
        return root;
    }

    // Approach 2:
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i); // Store for inorder[] as we will need to map from pre order to inorder
        }

        TreeNode root = buildTree2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    // Don't need to use preEnd.
    public TreeNode buildTree2(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val); // Rather than iterating, we get the index in O(1).
        int numsLeft = inRoot - inStart;

        root.left = buildTree2(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree2(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }


    // Approach 3:
    // start from first preorder element
    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right) {
            return null;
        }

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index);
        // build right subtree
        root.right = helper(index + 1, in_right);
        return root;
    }

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }
        return helper(0, inorder.length);
    }
}
