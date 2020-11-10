package LeetCodePremium.Facebook.TreesAndGraph;

/*
Given a binary tree, return the vertical order traversal of its nodes' values.
(ie, from top to bottom, column by column).

If two nodes are in the same row and column,
the order should be from left to right.
 */

import javafx.util.Pair;

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

    // Using BFS and without sorting.
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> output = new ArrayList();
        if (root == null) {
            return output;
        }

        Map<Integer, ArrayList> columnTable = new HashMap();
        // Pair of node and its column offset
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
        int column = 0;
        queue.offer(new Pair(root, column));

        int minColumn = 0, maxColumn = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.getKey();
            column = p.getValue();

            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable.put(column, new ArrayList<Integer>());
                }
                columnTable.get(column).add(root.val);
                minColumn = Math.min(minColumn, column);
                maxColumn = Math.max(maxColumn, column);

                queue.offer(new Pair(root.left, column - 1));
                queue.offer(new Pair(root.right, column + 1));
            }
        }

        for(int i = minColumn; i < maxColumn + 1; ++i) {
            output.add(columnTable.get(i));
        }

        return output;
    }

    // Using DFS
    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
    int minColumn = 0, maxColumn = 0;

    private void DFS(TreeNode node, Integer row, Integer column) {
        if (node == null)
            return;

        if (!columnTable.containsKey(column)) {
            this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
        }

        this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
        this.minColumn = Math.min(minColumn, column);
        this.maxColumn = Math.max(maxColumn, column);
        // preorder DFS traversal
        this.DFS(node.left, row + 1, column - 1);
        this.DFS(node.right, row + 1, column + 1);
    }

    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> output = new ArrayList();
        if (root == null) {
            return output;
        }

        this.DFS(root, 0, 0);

        // Retrieve the resuts, by ordering by column and sorting by row
        for (int i = minColumn; i < maxColumn + 1; ++i) {

            Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    return p1.getKey() - p2.getKey();
                }
            });

            List<Integer> sortedColumn = new ArrayList();
            for (Pair<Integer, Integer> p : columnTable.get(i)) {
                sortedColumn.add(p.getValue());
            }
            output.add(sortedColumn);
        }

        return output;
    }
}
