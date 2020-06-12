package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MPS2 {

	public static void main(String[] args) {
		
	}
	
	public static int maxPathSum(BinaryTree tree) {
		List<Integer> maxSumArray = findMaxSum(tree) ; 
		return maxSumArray.get(1) ; 
	  }

	public static List<Integer> findMaxSum(BinaryTree tree) {
		
		List<Integer> list = new ArrayList<>(Arrays.asList(0, 0)) ; 
		
		if(tree == null ) { // Base Case 
			return list ; 
		}
						
		// Include this value. 
		List<Integer> leftSubTree = findMaxSum(tree.left) ; // Only 2 recursive calls required. Head Recursion
		List<Integer> rightSubTree = findMaxSum(tree.right) ; 
		
		int leftSumAsBranch = leftSubTree.get(0) ; 
		int rightSumAsBranch = rightSubTree.get(0) ; 
		
		int maxChildSumAsBranch = Math.max(leftSumAsBranch, rightSumAsBranch); 
		
		int maxSumAsBranch = Math.max(maxChildSumAsBranch + tree.value, tree.value) ; 
		list.set(0, maxSumAsBranch );  // set msb for current node. 
		
		int mst = Math.max(maxSumAsBranch, leftSumAsBranch + rightSumAsBranch + tree.value) ; 

		int leftSum = leftSubTree.get(1) ; 
		int rightSum = rightSubTree.get(1) ;
		
		int runningMaxPathSum = Math.max(mst, Math.max(leftSum, rightSum) ); 
		list.set(1, runningMaxPathSum) ;
		
		// Setting the running max path sum. 
		if(list.get(0) > list.get(1)) {
			list.set(1,  list.get(0)) ; 
		}
		
		return list ; 
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
