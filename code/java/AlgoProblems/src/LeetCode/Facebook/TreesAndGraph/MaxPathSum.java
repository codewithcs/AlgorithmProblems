package LeetCode.Facebook.TreesAndGraph;

public class MaxPathSum {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if(root.left == null && root.right == null){
            return root.val ;
        }

        helper(root);
        return max;
    }

    public int helper(TreeNode node){
        if(node == null) return 0;
        int left = helper(node.left);
        int right =  helper(node.right);

        max = Math.max(max, node.val + left + right);
        // To consider the case of -ve nodes we have to do node.val+ left+ right

        // We have to ensure that we don't return -ve value to the parent.
        return Math.max(0, node.val + Math.max(left, right));
        // IMP Point: We have pass maximum of left and right because if we add the parent to the path
        // then the path either needs left or right and will add the maximum one.
    }
}
