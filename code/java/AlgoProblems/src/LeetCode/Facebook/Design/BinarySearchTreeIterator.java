package LeetCode.Facebook.Design;

import java.util.ArrayList;
import java.util.List;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){
        this.val = val;
    }
}

public class BinarySearchTreeIterator {
    List<Integer> list = new ArrayList<>();
    int index;

    public BinarySearchTreeIterator(TreeNode root) {
        index = 0;
        traverse(root);
    }

    // O(n) time, n: number of nodes in the BST.
    // O(n) space in worst case.
    public void traverse(TreeNode node){
        if(node == null) return;
        traverse(node.left);
        list.add(node.val);
        traverse(node.right);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    // Controlled Recursion
}
