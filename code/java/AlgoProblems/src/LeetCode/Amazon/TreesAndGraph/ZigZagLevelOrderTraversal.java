package LeetCode.Amazon.TreesAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the zigzag level order traversal
of its nodes' values. (ie, from left to right, then right to left for
the next level and alternate between).


 */
public class ZigZagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        if(root == null){
            return list;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.add(root);

        List<Integer> subList = new ArrayList<>(); subList.add(root.val);

        while(!stack1.isEmpty() || !stack2.isEmpty()){
            subList = new ArrayList<>();

            while(!stack1.isEmpty()){
                TreeNode current = stack1.pop(); subList.add(current.val);
                if(current.left != null ) stack2.add(current.left);
                if(current.right != null ) stack2.add(current.right);
            }

            if(!subList.isEmpty()){
                list.add(subList);
            }

            subList = new ArrayList<>();

            while(!stack2.isEmpty()){
                TreeNode current = stack2.pop(); subList.add(current.val);
                if(current.right != null) stack1.add(current.right);
                if(current.left != null ) stack1.add(current.left);
            }

            if(!subList.isEmpty()){
                list.add(subList);
            }
        }

        return  list;
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Approach 2: Using DFS
    protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<Integer>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 == 0)
                results.get(level).add(node.val);
            else
                results.get(level).add(0, node.val);
        }

        if (node.left != null) DFS(node.left, level + 1, results);
        if (node.right != null) DFS(node.right, level + 1, results);
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        DFS(root, 0, results);
        return results;
    }

    // Implement queue technique with a delimiter: See Tushar Roy video.
}
