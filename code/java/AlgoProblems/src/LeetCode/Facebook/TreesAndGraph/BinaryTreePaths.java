package LeetCode.Facebook.TreesAndGraph;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.
 */
public class BinaryTreePaths {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if(root==null){
            return list;
        }

        if(root.left == null && root.right == null){
            list.add(String.valueOf(root.val));
            return list;
        }

        helper(root, list, "");
        return list;
    }

    public void helper(TreeNode node, List<String> list, String current){
        if(node.left == null && node.right == null){
            current += node.val;
            list.add(current);
            return ;
        }

        current += node.val + "->";

        if(node.left != null){
            helper(node.left, list, current);
        }

        if(node.right != null){
            helper(node.right, list, current);
        }
    }
}
