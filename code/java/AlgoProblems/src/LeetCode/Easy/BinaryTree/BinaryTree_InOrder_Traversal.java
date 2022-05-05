package LeetCode.Easy.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree_InOrder_Traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    public void traverse(TreeNode root, List<Integer> result){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while(current!= null || !stack.isEmpty() ){
            while(current != null){
                stack.add(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);
            current = current.right ;
        }

    }
}
