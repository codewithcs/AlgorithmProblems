package LeetCodePremium.Amazon.TreesAndGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree {

    // Depth First Search.
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        TreeNode left = root.left ;
        TreeNode right = root.right;

        Stack<TreeNode> leftSubTree = new Stack<>();
        Stack<TreeNode> rightSubTree = new Stack<>();

        if(left == null && right != null ) return false;
        if(left != null && right == null ) return false;
        if(left == null && right == null) return true;

        if(left.val != right.val) return false;

        leftSubTree.push(root.left); rightSubTree.push(root.right);

        while(!leftSubTree.isEmpty() && !rightSubTree.isEmpty()){

            TreeNode current1 = leftSubTree.pop();
            TreeNode left1 = current1.left;
            TreeNode right1 = current1.right;

            TreeNode current2 = rightSubTree.pop();
            TreeNode left2 = current2.left;
            TreeNode right2 = current2.right;

            if(left1!=null && right2 != null) {
                if(left1.val == right2.val){
                    leftSubTree.push(left1);
                    rightSubTree.push(right2);
                } else {
                    return false;
                }
            } else if( (left1 != null && right2 == null ) || (left1==null && right2 != null ) ) {
                return false;
            }

            if(right1!=null && left2 != null) {
                if(left2.val == right1.val){
                    leftSubTree.push(left2);
                    rightSubTree.push(right1);
                } else {
                    return false;
                }
            } else if( ( right1 != null && left2 == null ) || ( right1==null && left2 != null )  ) {
                return false;
            }
        }

    //    if( !leftSubTree.isEmpty() || !rightSubTree.isEmpty() ) return false;
        // Don't need this check as we have extra checks on line 39 and 50.

        return true;
    }

    // Approach 2 using a single queue:
    // Nice Approach using BFS: Interesting Approach.
    boolean isSymmetric2(TreeNode root) {
        if(root==null) return true;

        Queue<TreeNode> q = new LinkedList<>();
        // Can have null elements in a linked list.
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    // Approach 3:
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    boolean isSymmetric4(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right); // Do not pass in just root as we don't to check for it and it will create unnecessary recursive calls.
    }

        static class TreeNode{
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
