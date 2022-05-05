package LeetCode.Amazon.Others;

/*
Given a binary tree where every node has a unique value, and a target key k,
find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the
binary tree to reach any leaf of the tree.
Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row.
The actual root tree given will be a TreeNode object.

Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
 */
public class ClosestLeafInABinaryTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val ;
            this.left = left;
            this.right = right;
        }
    }

    public int findClosestLeaf(TreeNode root, int k){
        return 0;
    }

}
