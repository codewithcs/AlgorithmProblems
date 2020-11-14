package LeetCodePremium.Amazon.TreesAndGraph;

/*
Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any
two nodes in a tree. This path may or may not pass through the root.

Note: The length of path between two nodes is represented
by the number of edges between them.

Also see my solution when I solved it under Facebook/
 */
public class DiameterOfABinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        return helper(root);
    }

    public int helper(TreeNode root){
        if(root == null) return 0;

        int currentDiameter = 2 + height(root.left, 0) + height(root.right, 0);

        int leftSubTreeDiameter = helper(root.left);
        int rightSubTreeDiameter = helper(root.right);

        return Math.max(currentDiameter, Math.max(leftSubTreeDiameter, rightSubTreeDiameter)) ;
    }

    public int height(TreeNode root, int depth){
        if(root == null) return -1;
        int height = depth;

        if(root.left != null) {
            height = Math.max(height,  height(root.left, depth+1));
        }

        if(root.right != null){
            height = Math.max(height,  height(root.right, depth+1));
        }

        return height;
    }

    // Approach 2: Interesting
    int ans;
    public int diameterOfBinaryTree2(TreeNode root) {
        ans = 1;
        height(root);
        return ans - 1;
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        int L = height(node.left);
        int R = height(node.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1; // depth of the current node. Technically should be called height.
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
}
