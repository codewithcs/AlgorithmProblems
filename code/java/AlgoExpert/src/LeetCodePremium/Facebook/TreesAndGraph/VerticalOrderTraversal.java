package LeetCodePremium.Facebook.TreesAndGraph;

/*
Given a binary tree, return the vertical order traversal of its nodes' values.
(ie, from top to bottom, column by column).

If two nodes are in the same row and column,
the order should be from left to right.
 */

import java.util.*;

public class VerticalOrderTraversal {
    class TreeNode{
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

    class Node{
        TreeNode node;
        int column;
        Node(){}
        Node(TreeNode node, int column){
            this.node = node; this.column = column;
        }
    }

    // Application of Level Order Traversal and a Map.
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=1; i<=size; i++){
                Node current = queue.poll();
                int currentColumn = current.column;
                map.computeIfAbsent(currentColumn, x-> new ArrayList<>()).add(current.node.val);

                if(current.node.left != null){
                    queue.add(new Node(current.node.left, currentColumn-1));
                }
                if(current.node.right!=null){
                    queue.add(new Node(current.node.right, currentColumn+1));
                }
            }

        }

        for(Integer key : map.keySet()){
            list.add(map.get(key));
        }
        return list;
    }
}
