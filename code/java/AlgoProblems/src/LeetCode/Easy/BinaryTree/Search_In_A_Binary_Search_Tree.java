package LeetCode.Easy.BinaryTree;

public class Search_In_A_Binary_Search_Tree {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode current = root;
        while(current != null){
            if(current.val == val){
                return current;
            } else if(current.val > val){
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }
}
