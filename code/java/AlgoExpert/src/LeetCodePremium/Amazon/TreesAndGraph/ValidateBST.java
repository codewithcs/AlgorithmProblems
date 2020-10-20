package LeetCodePremium.Amazon.TreesAndGraph;

import java.util.LinkedList;
import java.util.Stack;

public class ValidateBST {

    // O(N) time: Visit each node exactly once and O(N) space in the worst case.
    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }
    // Can also check for null before hand, that is avoid passing a null node in the recursive call.
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    // Approach 2: O(n) time and space.
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
                        lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        // This is Depth First Search.
        while (!stack.isEmpty()) {
            root = stack.poll(); // get and remove the head.
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;

            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;

            update(root.right, val, upper);
            update(root.left, lower, val);
        }

        return true;
    }

    // Iterate In-Order Traversal: In-Order: Left, Root, Right.
    // O(N) time when the bad element is the rightmost leaf node.
    // O(n) space when the tree is like a list.
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop(); // Backtracking Step. We use a Stack to backtrack, that is stack is storing the previous states.

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }

        return true;
    }
    /*
    No need to keep track of the whole inorder traversal list.
    The last added inorder element is enough to
    ensure at each step that the tree is BST (or not).
    Hence one could merge both steps into one and reduce the used space.
     */

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
