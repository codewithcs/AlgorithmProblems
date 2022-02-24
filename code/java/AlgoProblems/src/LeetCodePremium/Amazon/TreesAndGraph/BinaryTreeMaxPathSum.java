package LeetCodePremium.Amazon.TreesAndGraph;

/*
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any node sequence from some starting node to
any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

Constraints:
The number of nodes in the tree is in the range [0, 3 * 104].
-1000 <= Node.val <= 1000
 */

// Amazing Solution.
public class BinaryTreeMaxPathSum {
    int answer;

    public int maxPathSum(TreeNode root) {
        answer = Integer.MIN_VALUE;
        helper(root);
        return answer;
    }

    public int helper(TreeNode root){
        if(root == null) return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        answer = Math.max(answer, left + right + root.val);
        return Math.max(0, root.val + Math.max(left, right));
    }
// The subproblems are considering the solution by always including the root node.
// If we don't get a +ve answer by considering the root of the subtree, we return 0 to its parent as
// we don't want the parent to add in a -ve value.

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
