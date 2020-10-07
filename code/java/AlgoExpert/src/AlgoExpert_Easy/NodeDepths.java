package AlgoExpert_Easy;

import java.util.ArrayList;
import java.util.List;

public class NodeDepths {

    public static int nodeDepths(BinaryTree root){
        List<Integer> sum = new ArrayList<>();
        sum.add(0);
        calculateSum(root, 0, sum);
        return sum.get(0);
    }

    public static void calculateSum(BinaryTree root, int currentDepth, List<Integer> currentSum ){

        if(root == null ) return;

        currentSum.set(0, currentSum.get(0) + currentDepth );

        calculateSum(root.left, currentDepth + 1, currentSum);
        calculateSum(root.right, currentDepth + 1, currentSum);
    }

    public static int calculateSum2(BinaryTree root, int currentDepth ){

        if(root == null ) return 0;

        return currentDepth + calculateSum2(root.left, currentDepth + 1) +
                calculateSum2(root.right, currentDepth + 1);
    }


    // Using DFS traversal, O(n) time | O(h) space where n is number of nodes in the Binary Tree and
    // h is the height of the Binary Tree.

    public static int nodeDepths2(BinaryTree root){
        int sumOfDepths = 0;
        List<Level> stack = new ArrayList<>(); // only h elements at max in this stack.
        stack.add(new Level(root, 0));

        while(stack.size() > 0){
            Level top = stack.remove(stack.size() -1);
            BinaryTree node = top.root;
            int depth = top.depth;

            if(node == null) continue;

            sumOfDepths += depth; // only add the depth of the current node.
            stack.add(new Level(node.left, depth+1));
            stack.add(new Level(node.right, depth+1));
        }
        return sumOfDepths;
    }

    static class Level {
        public BinaryTree root;
        int depth;

        public Level(BinaryTree root, int depth){
            this.root = root;
            this.depth = depth;
        }
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

}
