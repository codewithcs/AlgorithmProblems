package LeetCode.Facebook.Others;

public class BinaryTreeTilt {
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

    public int findTilt(TreeNode root) {
        if(root==null || (root.left == null && root.right == null)) {
            return 0;
        }

        return helper(root);
    }

    public int helper(TreeNode node){
        int tilt = getTilt(node);

        if(node.left!= null){
            tilt += helper(node.left);
        }

        if(node.right != null){
            tilt += helper(node.right);
        }
        return tilt;
    }

    public int getTilt(TreeNode node){
        if(node.left == null && node.right == null){
            return 0;
        }
        int left= 0; int right=0;
        if(node.left !=null){
            left += getSumOfSubTreeNodes(node.left);
        }
        if(node.right !=null){
            right += getSumOfSubTreeNodes(node.right);
        }
        return Math.abs(left-right);
    }

    public int getSumOfSubTreeNodes(TreeNode node){
        if(node.left == null && node.right == null){
            return node.val;
        }

        int sum = node.val;

        if(node.left != null){
            sum = sum + getSumOfSubTreeNodes(node.left);
        }

        if(node.right != null){
            sum = sum + getSumOfSubTreeNodes(node.right);
        }

        return sum;
    }

    // Post Order DFS Traversal.
    private int totalTilt = 0;

    protected int valueSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = this.valueSum(node.left);
        int rightSum = this.valueSum(node.right);
        int tilt = Math.abs(leftSum - rightSum);
        this.totalTilt += tilt;

        // return the sum of values starting from this node.
        return node.val + leftSum + rightSum;
    }

    public int findTilt2(TreeNode root) {
        this.totalTilt = 0;
        this.valueSum(root);
        return this.totalTilt;
    }
}
