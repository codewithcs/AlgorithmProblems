package AlgoExpert_Medium;

import java.util.ArrayDeque;

public class invertBinaryTree {

	public static void main(String[] args) {
		
	}
	
	public static void swap(BinaryTree tree) {
		BinaryTree tmp = tree.left ; 
		tree.left = tree.right ; 
		tree.right = tmp;
}

	public static void invertBinaryTree(BinaryTree tree) { // Recursion: DFS 
	
		if ( tree.left == null && tree.right == null )
			return ; 
	
		swap(tree); 
		
		if (tree.left!=null)
			invertBinaryTree(tree.left) ;
		
		if(tree.right !=null)
			invertBinaryTree(tree.right) ; 
			
	}

	// O(n) time | O(n) space
	public static void invertBinaryTree2(BinaryTree tree) { // can use a queue. Doing Breadth First Search  
		
		ArrayDeque<BinaryTree> queue = new ArrayDeque<>();
		queue.add(tree); 
		
		while(!queue.isEmpty()) {
			BinaryTree current = queue.pollFirst() ; // retrieves and removes the first element. 
			if(current == null) {
				continue ; 
			}
			
			swapLeftAndRight(current) ; 
			
			if(current.left != null ) {
				queue.addLast(current.left);
			}
			
			if(current.right !=null) {
				queue.addLast(current.right);
			}
					
		}	
	}
	
		private static void swapLeftAndRight (BinaryTree tree) {
			BinaryTree left = tree.left ; 
			tree.left = tree.right ; 
			tree.right = left ; 
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
