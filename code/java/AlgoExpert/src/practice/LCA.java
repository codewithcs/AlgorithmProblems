package practice;

public class LCA {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        return helper(root, p, q);
    }

    //  Deal with the current node.
    public TreeNode helper(TreeNode node, TreeNode p, TreeNode q){
        if(node == null ) return null ;

        // Base Cases
        if(node.val == p.val){
            return node;
        } else if(node.val == q.val){
            return node;
        }

        // Recursion
        TreeNode left = helper(node.left, p, q);
        TreeNode right = helper(node.right, p, q);

        // Steps after the recursion.
        if(left!= null && right != null) return node;
        if(left != null) return left ;
        if(right!= null) return right;

        return null;
    }
}
