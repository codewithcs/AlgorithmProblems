package LeetCodePremium.Amazon.TreesAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        List<Integer> subList = new ArrayList<>();
        int maxSize = queue.size();

        while(!queue.isEmpty()){
            subList = new ArrayList();

            for( int i=0; i < maxSize ; i++ ){ // Setting maxSize equal to queue size is the most important point.
                root = queue.poll();
                subList.add(root.val);

                if(root.left != null){
                    queue.add(root.left);
                }

                if(root.right != null){
                    queue.add(root.right);
                }
            }

            list.add(subList);
            maxSize = queue.size();
        }

        return list;
    }
/*
Initially I was thinking to take 1, 2, 4, 8 as maxSizes as I iterated on every level.
But null added complexity as on a level number of elements in a list can be < max size.
Setting max size equal to the queue size is an interesting observation.
 */

// Approach 2: Recursive
List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
    // Interesting approach: Doing a get and then adding the sublist.


    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
