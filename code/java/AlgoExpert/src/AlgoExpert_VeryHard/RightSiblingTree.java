package AlgoExpert_VeryHard;

public class RightSiblingTree {
    public static BinaryTree rightSiblingTree(BinaryTree root){
        mutate(root, null, false);
        return root;
    }

    // traverse(root, null, true); return root;
    // this works, O(n) time and O(h) space, n: number of nodes in the BinaryTree and
    // d is the depth (height) of the Binary Tree.
    public static BinaryTree traverse(BinaryTree node, BinaryTree parent, boolean isLeftChild){
        BinaryTree left = node.left;
        BinaryTree right = node.right;

        if(left !=  null ){
            traverse(left, node, true) ;
        }

        if(isLeftChild){
            node.right = parent!=null ? parent.right : null;
        } else {
            node.right = parent.right!=null ? parent.right.left :null;
        }

        if(right != null ){
            traverse(right, node, false);
        }

        return node;
    }


    // AlgoExpert solution
    public static void mutate(BinaryTree node, BinaryTree parent, boolean isLeftChild){
        if(node == null ){
            return;
        }
        BinaryTree left = node.left;
        BinaryTree right = node.right;

        mutate(left, node, true);

        if(parent == null ){
            node.right = null;
        } else if(isLeftChild){
            node.right = parent.right;
        } else {
            node.right = parent.right!=null ? parent.right.left : null;
        }
        mutate(right, node, false);
    }

    static class BinaryTree{
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value){
            this.value = value;
        }
    }
}
