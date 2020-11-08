package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    // Not an in-place solution, makes use of a dummy node.
    public void flatten(TreeNode root) {
        if(root == null){
            return ;
        }

        TreeNode start = new TreeNode(-1);
        TreeNode current = start;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            current.right = node;
            current.left = null;
            current = node;
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }

        root = start.right ;
    }

    // in-place solution.
    // O(n) time complexity, O(1) space.
    // We process each node of the tree at most twice.
    public void flatten2(TreeNode root) {
        // Handle the null scenario
        if (root == null) {
            return;
        }
        TreeNode node = root;

        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }


    private TreeNode flattenTree(TreeNode node) {
        // Handle the null scenario
        if (node == null) {
            return null;
        }

        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        }

        // Recursively flatten the left subtree
        TreeNode leftTail = this.flattenTree(node.left);

        // Recursively flatten the right subtree
        TreeNode rightTail = this.flattenTree(node.right);

        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        // We need to return the "rightmost" node after we are
        // done wiring the new connections.
        return rightTail == null ? leftTail : rightTail;
    }

    // O(n) time and space.  O(n) space occupied by the recursion stack.
    // O(n) space since we process each node of the tree exactly once.
    public void flatten3(TreeNode root) {
        this.flattenTree(root);
    }

}
