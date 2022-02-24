package LeetCodePremium.Facebook.Design;

import java.util.Stack;

// Also see Iterative In-Order Traversal.
public class BinarySearchTreeIterator2 {
    Stack<TreeNode> stack;

    public BinarySearchTreeIterator2(TreeNode root) {
        stack = new Stack();
        pushLeft(root);
    }
    // Iterative In-Order Traversal.
    public int next() {
        TreeNode node = stack.pop();
        if(node.right != null) {
            pushLeft(node.right);
        }
        return node.val;
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }

    private void pushLeft(TreeNode root) {
        while(root != null) {
            stack.add(root);
            root = root.left;
        }
    }
}
