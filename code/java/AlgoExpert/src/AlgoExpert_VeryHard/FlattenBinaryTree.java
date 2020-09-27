package AlgoExpert_VeryHard;

import java.util.ArrayList;
import java.util.List;

public class FlattenBinaryTree {
    public static void main(String[] args) {

    }

    // Solution 1
    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        BinaryTree flattenedTree = new BinaryTree(root.value);
        List<BinaryTree> inOrderNodes = new ArrayList<>();

        inOrderNodes = getNodesInOrder(root, inOrderNodes);

        for(int i=0 ; i < inOrderNodes.size()-1 ; i++ ){
            BinaryTree leftNode = inOrderNodes.get(i);
            BinaryTree rightNode = inOrderNodes.get(i+1);
            leftNode.right = rightNode;
            rightNode.left = leftNode;
        }

        return inOrderNodes.get(0);
    }

    public static List<BinaryTree> getNodesInOrder(BinaryTree root, List<BinaryTree> inOrderNodes){
        if(root != null ){
            getNodesInOrder(root.left, inOrderNodes);
            inOrderNodes.add(root);
            getNodesInOrder(root.right, inOrderNodes);
        }
        return inOrderNodes;
    }

    // Solution 2
    public static BinaryTree flattenBinaryTree2(BinaryTree root) {
        if(root!= null ){

            if(root.left != null ){
                BinaryTree leftNode ;
                BinaryTree rightNode ;

                leftNode = getRightmostNodeInLeftSubtree(root.left.left);
                rightNode = getLeftmostNodeInRightSubtree(root.left.right);

                root.left = leftNode ;
                root.right = rightNode;

                flattenBinaryTree2(root.left.left);
            }



            if(root.right != null ) {
                BinaryTree leftNode ;
                BinaryTree rightNode ;

                leftNode = getRightmostNodeInLeftSubtree(root.right.left);
                rightNode = getLeftmostNodeInRightSubtree(root.right.right);

                root.left = leftNode ;
                root.right = rightNode;

                flattenBinaryTree2(root.right.left);
            }

        }

        return root ;
    }

    public static BinaryTree getRightmostNodeInLeftSubtree(BinaryTree node){
        if(node != null ){
           if(node.right != null ){

           }
        }
        return node;
    }

    public static BinaryTree getLeftmostNodeInRightSubtree(BinaryTree node){
        return null;
    }

    // AlgoExpert Solution2
    public static BinaryTree flattenBinaryTree3(BinaryTree root) {
        BinaryTree leftMost = flattenTree(root)[0];
        return leftMost;
    }

    public static BinaryTree[] flattenTree(BinaryTree node){
        BinaryTree leftMost;
        BinaryTree rightMost;

        if(node.left == null) { // Base Case
            leftMost = node;
        } else {
            BinaryTree[] leftAndRightMostNodesOfLeft = flattenTree(node.left);
            connectNodes(leftAndRightMostNodesOfLeft[1], node);
            leftMost = leftAndRightMostNodesOfLeft[0];
        }

        if(node.right == null ){ // Base Case
            rightMost = node;
        } else {
            BinaryTree[] leftAndRightMostNodesOfRight = flattenTree(node.right);
            connectNodes(node, leftAndRightMostNodesOfRight[0]);
            rightMost = leftAndRightMostNodesOfRight[1];
        }

        return new BinaryTree[] { leftMost, rightMost };
    }

    public static void connectNodes(BinaryTree left, BinaryTree right){
        left.right = right;
        right.left = left;
    }

    static class BinaryTree {
        int value;
        BinaryTree left = null ;
        BinaryTree right = null ;

        public BinaryTree(int value){
            this.value = value;
        }
    }
}
