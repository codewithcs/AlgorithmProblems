package AlgoExpert_VeryHard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllKindsOfNodeDepths {
    public static int allKindsOfNodeDepths(BinaryTree root){
        return -1 ;
    }

    // AlgoExpert solution 1:
    // Average Case: When the tree is balanced
    // O(nlogn) time | O(h) space, n: number of nodes in the binary tree and h: height of the binary tree
    // Worst Case: O(n^2) time
    public static int allKindsOfNodeDepths1(BinaryTree root){
        int sumOfAllDepths = 0;

        List<BinaryTree> stack = new ArrayList<>();
        stack.add(root);

        while(stack.size() > 0){
            BinaryTree node = stack.remove(stack.size()-1);
            if(node == null){
                continue;
            }
            sumOfAllDepths += nodeDepths(node, 0);
            stack.add(node.left);
            stack.add(node.right);
        }
        return sumOfAllDepths ;
    }

    public static int nodeDepths(BinaryTree node, int depth){
        if(node == null){
            return 0;
        }
        return depth + nodeDepths(node.left, depth +1 ) +
                nodeDepths(node.right,depth+1);
    }

    // AlgoExpert solution 2: complexity same as above
    // Doing recursive traversal rather than iterative dfs.
    public static int allKindsOfNodeDepths2(BinaryTree root){
        if(root == null){
            return 0;
        }
        return allKindsOfNodeDepths2(root.left) + allKindsOfNodeDepths2(root.right) +
                nodeDepths(root, 0);
    }

    // AlgoExpert solution 3: O(n) time | O(n) space - n: number of nodes in the binary tree
    public static int allKindsOfNodeDepths3(BinaryTree root){
        Map<BinaryTree, Integer> nodeCounts = new HashMap<>();
        Map<BinaryTree, Integer> nodeDepths = new HashMap<>();

        // Pre-computing, pre-compute node counts before calling recursion on node depths.
        addNodeCounts(root, nodeCounts);
        addNodeDepths(root, nodeDepths, nodeCounts);

        return sumAllNodeDepths(root, nodeDepths); // final iteration through the input binary tree
    }

    public static int sumAllNodeDepths(BinaryTree node, Map<BinaryTree, Integer> nodeDepths){
        if(node == null){
            return 0;
        }
        return sumAllNodeDepths(node.left, nodeDepths)
                + sumAllNodeDepths(node.right, nodeDepths)
                + nodeDepths.get(node);
    }

    public static void addNodeDepths(BinaryTree node, Map<BinaryTree, Integer> nodeDepths, Map<BinaryTree, Integer> nodeCounts){
        nodeDepths.put(node, 0);

        if(node.left != null){
            addNodeDepths(node.left, nodeDepths, nodeCounts);
            nodeDepths.put(node, nodeDepths.get(node) + nodeDepths.get(node.left) + nodeCounts.get(node.left));
        }
        if(node.right != null){
            addNodeDepths(node.right, nodeDepths, nodeCounts);
            nodeDepths.put(node, nodeDepths.get(node) + nodeDepths.get(node.right) + nodeCounts.get(node.right));
        }
    }

    public static void addNodeCounts(BinaryTree node, Map<BinaryTree, Integer> nodeCounts){
        nodeCounts.put(node, 1);

        if(node.left != null){
            addNodeCounts(node.left, nodeCounts);
            nodeCounts.put(node, nodeCounts.get(node) +  nodeCounts.get(node.left));
        }
        if(node.right != null){
            addNodeCounts(node.right, nodeCounts);
            nodeCounts.put(node, nodeCounts.get(node) + nodeCounts.get(node.right));
        }
    }

    // AlgoExpert solution 4: O(n) time | O(h) space where n is the number of nodes in the Binary Tree and
    // h is the height of the Binary Tree
    // Average Case: When the tree is balanced.
    public static int allKindsOfNodeDepths4(BinaryTree root){
        return getTreeInfo(root).sumOfAllDepths;
    }

    // What does sumOfDepths mean ?
    public static TreeInfo getTreeInfo(BinaryTree tree){
        if(tree == null){
            return new TreeInfo(0, 0, 0);
        }
        TreeInfo leftTreeInfo = getTreeInfo(tree.left);
        TreeInfo rightTreeInfo = getTreeInfo(tree.right);

        int sumOfLeftDepths = leftTreeInfo.sumOfDepths + leftTreeInfo.numNodesInTree;
        int sumOfRightDepths = rightTreeInfo.sumOfDepths + rightTreeInfo.numNodesInTree;

        int numNodesInTree =  1 + leftTreeInfo.numNodesInTree + rightTreeInfo.numNodesInTree;
        int sumOfDepths = sumOfLeftDepths + sumOfRightDepths;
        int sumOfAllDepths = sumOfDepths + leftTreeInfo.sumOfAllDepths + rightTreeInfo.sumOfAllDepths;

        return new TreeInfo(numNodesInTree, sumOfDepths, sumOfAllDepths);
    }

        static class TreeInfo{
        public int numNodesInTree;
        public int sumOfDepths;
        public int sumOfAllDepths;
        public TreeInfo(int numNodesInTree, int sumOfDepths, int sumOfAllDepths){
            this.numNodesInTree = numNodesInTree;
            this.sumOfDepths = sumOfDepths;
            this.sumOfAllDepths = sumOfAllDepths;
        }
    }

    // AlgoExpert solution 5: complexity same as 4
    public static int allKindsOfNodeDepths5(BinaryTree root){
        return allKindsOfNodeDepthsHelper(root, 0, 0);
    }

    public static int allKindsOfNodeDepthsHelper(BinaryTree node, int depthSum, int depth){
        if(node == null){
            return 0;
        }

        depthSum += depth;
        return depthSum
               + allKindsOfNodeDepthsHelper(node.left, depthSum, depth+ 1)
                + allKindsOfNodeDepthsHelper(node.right, depthSum, depth+1);
    }

    // AlgoExpert solution 6: complexity same as 5
    public static int allKindsOfNodeDepths6(BinaryTree root){
        return allKindsOfNodeDepthsHelper(root, 0);
    }

    public static int allKindsOfNodeDepthsHelper(BinaryTree node, int depth){
        if(node == null){
            return 0;
        }
        // 1 + 2 + 3 + ... + depth-1 + depth
        int depthSum = (depth * (depth+1)) / 2;
        return depthSum
                + allKindsOfNodeDepthsHelper(node.left, depth+1)
                + allKindsOfNodeDepthsHelper(node.right, depth+1);
    }


        static class BinaryTree{
        int value;
        BinaryTree left;
        BinaryTree right;
         public BinaryTree(int value){
             this.value = value;
             left = null;
             right = null;
         }
    }
}
