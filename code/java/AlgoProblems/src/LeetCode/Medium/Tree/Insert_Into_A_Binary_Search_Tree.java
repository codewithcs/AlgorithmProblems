package LeetCode.Medium.Tree;

/*
You are given the root node of a binary search tree (BST) and a value to insert into the tree.
Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Notice that there may exist multiple valid ways for the insertion,
as long as the tree remains a BST after insertion. You can return any of them.

Constraints:
The number of nodes in the tree will be in the range [0, 10^4].
-10^8 <= Node.val <= 10^8
All the values Node.val are unique.
-10^8 <= val <= 10^8
It's guaranteed that val does not exist in the original BST.
 */

class TreeNode{
    int val;
    TreeNode left; TreeNode right;
    TreeNode(){}
    public TreeNode(int val){ this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val; this.left = left; this.right = right;
    }
}
public class Insert_Into_A_Binary_Search_Tree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return recurse(root, val);
    }

    public TreeNode recurse(TreeNode root, int val){
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }

        if(root.val > val){
            root.left = recurse(root.left, val);
        } else if(root.val < val){
            root.right = recurse(root.right, val);
        }

        return root;
    }

    // Does not work when root is null.
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        recurse2(root, val);
        return root;
    }

    public void recurse2(TreeNode root, int val){
        if(root == null) {
            root =  new TreeNode(val);
            System.out.println("Here");
            return;
        }

        if(root.val > val){
            if(root.left == null){
                root.left = new TreeNode(val);
            } else {
                recurse(root.left, val);
            }

        } else if(root.val < val){
            if(root.right == null){
                root.right = new TreeNode(val);
            } else {
                recurse(root.right, val);
            }
        }

    }

    // This works.
    public TreeNode insertIntoBST3(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        recurse3(root, val);
        return root;
    }

    public void recurse3(TreeNode root, int val){
        if(root == null) {
            return;
        }

        if(root.val > val){
            if(root.left == null){
                root.left = new TreeNode(val);
            } else {
                recurse(root.left, val);
            }
        } else if(root.val < val){
            if(root.right == null){
                root.right = new TreeNode(val);
            } else {
                recurse(root.right, val);
            }
        }

    }

    // Iterative Solution:
    public TreeNode insertIntoBST_Iterative(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        TreeNode current = root;

        while(true){
            if(current.val < val) {
                if(current.right == null){
                    current.right = new TreeNode(val);
                    return root;
                } else {
                    current = current.right;
                }
            }
            else if(current.val > val){
                if(current.left == null){
                    current.left = new TreeNode(val);
                    return root;
                } else {
                    current = current.left;
                }
            }
        }

    }

}
