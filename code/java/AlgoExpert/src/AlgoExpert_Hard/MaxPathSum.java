package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPathSum {
	public static void main(String[] args) {
		
	}
	
	public static int maxPathSum(BinaryTree tree) {
		List<Integer> maxSumArray = findMaxSum(tree) ; 
		
		return maxSumArray.get(1);
	  }

	public static List<Integer> findMaxSum(BinaryTree tree) {
		
		if(tree == null) {
			return new ArrayList<>(Arrays.asList(0, 0)) ; 
		}
		
		List<Integer> leftMaxSumArray = findMaxSum(tree.left) ;
		Integer leftMaxSumAsBranch = leftMaxSumArray.get(0); 
		Integer leftMaxPathSum = leftMaxSumArray.get(1); 
		
		List<Integer> rightMaxSumArray = findMaxSum(tree.right); 
		Integer rightMaxSumAsBranch = rightMaxSumArray.get(0); 
		Integer rightMaxPathSum = rightMaxSumArray.get(1); 
		
		Integer maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch); 
		Integer maxSumAsBranch = Math.max(maxChildSumAsBranch + tree.value, tree.value) ; 
		
		Integer maxSumAsRootNode = 
				Math.max(leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch, maxSumAsBranch) ; 
		
		int maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode)) ; 
		
		return new ArrayList<>(Arrays.asList(maxSumAsBranch, maxPathSum)) ; 
	}
	
	
	static class BinaryTree {
	    public int value;
	    public BinaryTree left;
	    public BinaryTree right;
	
	    public BinaryTree(int value) {
	      this.value = value;
	    }
    }
	
}
