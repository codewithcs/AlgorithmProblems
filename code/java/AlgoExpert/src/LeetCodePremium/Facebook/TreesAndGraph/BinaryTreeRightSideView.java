package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a binary tree, imagine yourself standing on the right side of it, return the
values of the nodes you can see ordered from top to bottom.
 */

public class BinaryTreeRightSideView {
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=1 ; i<=size ; i++) {
                TreeNode current = queue.poll();
                if(i==1) {
                    list.add(current.val);
                }
                if(current.right !=null){
                    queue.add(current.right);
                }
                if(current.left != null){
                    queue.add(current.left);
                }
            }
        }

        return list;
    }

    // Recursive DFS.
    List<Integer> rightside = new ArrayList();

    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) {
            rightside.add(node.val);
        }

        // First calling the dfs on the right subtree.
        if (node.right != null) {
            helper(node.right, level + 1);
        }
        if (node.left != null) {
            helper(node.left, level + 1);
        }
    }

    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return rightside;
        helper(root, 0);
        return rightside;
    }

}
