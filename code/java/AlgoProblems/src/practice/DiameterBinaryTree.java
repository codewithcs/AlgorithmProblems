package practice;

public class DiameterBinaryTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null )) return 0;
        return findDiameter(root);
    }

    public int findDiameter(TreeNode node){
        if(node.right == null && node.left == null) return 0;

        int leftHeight=0; int rightHeight = 0;

        if(node.left != null){
            leftHeight = 1 + findHeight(node.left, 0); // correct way of writing, do not pass 1 since initial height is always 0.
        }
        if(node.right != null){
            rightHeight = 1 + findHeight(node.right, 0);
        }

        int leftDiameter = 0; int rightDiameter = 0;

        if(node.left != null){
            leftDiameter = findDiameter(node.left);
        }

        if(node.right != null){
            rightDiameter = findDiameter(node.right);
        }

        return Math.max(leftHeight+rightHeight, Math.max(leftDiameter, rightDiameter));
    }

    public int findHeight(TreeNode node, int currentHeight){
        if(node.left == null && node.right == null) return currentHeight;

        int leftHeight = 0;
        int rightHeight=0;

        if(node.left != null){
            leftHeight = findHeight(node.left, 1+ currentHeight);
        }

        if(node.right!=null){
            rightHeight = findHeight(node.right, 1+currentHeight);
        }

        return Math.max(leftHeight, rightHeight);
    }
}
