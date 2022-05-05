package LeetCode.Easy.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree_Post_Order_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();

        // We will have a pointer to the recently popped node
        TreeNode current = root, previous = null;

        while(current != null || !stack.isEmpty()) {
            // Keep on iterating towards the leftmost node
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            // If there is no right child
            // or right child is the one that we recently visited
            // it means we have traversed all the nodes of stack.peek()

            if(stack.peek().right == null || stack.peek().right == previous) {
                // we will update the prev node
                previous = stack.pop();
                ans.add(previous.val);
            } else {
                // Otherwise we will visit the right child.
                current = stack.peek().right;
            }
        }

        return ans;
    }

}
