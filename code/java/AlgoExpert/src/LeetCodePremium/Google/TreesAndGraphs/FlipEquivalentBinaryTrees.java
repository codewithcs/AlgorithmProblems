package LeetCodePremium.Google.TreesAndGraphs;

/*
For a binary tree T, we can define a flip operation as follows:
Choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only
if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2,
return true if the two trees are flip equivalent or false otherwise.

Constraints:

The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].
 */

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FlipEquivalentBinaryTrees {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) return false;
        return flip(root1, root2);
    }

    public boolean flip(TreeNode root1, TreeNode root2){
        if(root1.left == null && root2.left == null && root1.right == null && root2.right == null){
            return true;
        }  // Case: 0, 0

        int countChildNodesFirstTree = 0;
        if(root1.left != null || root1.right != null){
            countChildNodesFirstTree ++;
            if(root1.left != null && root1.right != null ){
                countChildNodesFirstTree++;
            }
        }

        int countChildNodesSecondTree = 0;
        if(root2.left != null || root2.right != null){
            countChildNodesSecondTree  ++;
            if(root2.left != null && root2.right != null ){
                countChildNodesSecondTree ++;
            }
        }

        if(countChildNodesFirstTree  != countChildNodesSecondTree ) return false;

        // Case: (2, 2)
        if(root1.left!= null && root1.right!= null && root2.left != null && root2.right != null){

            if(root1.left.val == root2.left.val && root1.right.val == root2.right.val){
                return flip(root1.left, root2.left) &&
                        flip(root1.right, root2.right) ;
            }

            if(root1.left.val != root2.left.val){
                invert(root1);
            }

            if(root1.left.val != root2.left.val && root1.right.val != root2.right.val){
                return false;
            }

            return flip(root1.left, root2.left) &&
                    flip(root1.right, root2.right) ;
        }

        // Case: (1, 1)
        if(root1.left != null && root2.left != null){
            if(root1.left.val == root2.left.val){
                return flip(root1.left, root2.left);
            } else {
                return false;
            }
        } else if(root1.right != null && root2.right != null){
            if(root1.right.val == root2.right.val){
                return flip(root1.right, root2.right);
            } else {
                return false;
            }
        } else {
            if(root1.left == null){
                invert(root1);
            } else {
                invert(root2);
            }

            if(root1.left.val == root2.left.val){
                return flip(root1.left, root2.left);
            } else {
                return false;
            }
        }
    }

    public void invert(TreeNode root){
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    // Approach 2:
    public boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        return ( (flipEquiv2(root1.left, root2.left) && flipEquiv2(root1.right, root2.right) )|| (flipEquiv2(root1.left, root2.right) && flipEquiv2(root1.right, root2.left)));
    }

    // NOTE: Nodes in the tree are unique in values and >=0, <= 99 : Clarifying questions to ask.
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);

        flipEquiv3(root1, root2);
    }

    public static boolean flipEquiv3(TreeNode root1, TreeNode root2){
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        dfs(root1, vals1);
        dfs(root2, vals2);
        System.out.println("vals1 is : " + vals1);
        System.out.println("vals2 is : " + vals2);
        return vals1.equals(vals2);
    }

    public static void dfs(TreeNode root, List<Integer> list){
        if(root != null){
            list.add(root.val);
            int left = root.left != null ? root.left.val : Integer.MIN_VALUE;
            int right = root.right != null ? root.right.val : Integer.MIN_VALUE;

            if(left < right){
                dfs(root.left, list);
                dfs(root.right, list);
            } else {
                dfs(root.right, list);
                dfs(root.left, list);
            }

            list.add(null); // Encode both when we enter and leave a particular node.
        }
    }

}
