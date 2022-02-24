package AlgoExpert_Medium;

public class ValidateBST {

	public static void main(String[] args) {
		
	}

	// Better approach is to pass in null and null for the root and have Integer in place of int.
	public static boolean validateBST(BST tree, int minValue, int maxValue) {
		
		if(tree.value < minValue || tree.value >= maxValue) {
			return false; 
		}
		
		if(tree.left != null && !validateBST(tree.left, minValue, tree.value) ) {
			return false; 
		}
		
		if(tree.right != null && !validateBST(tree.right, tree.value, maxValue) ) {
			return false; 
		}
		
		return true ; 
	}
	
	
	static class BST {
	    public int value;
	    public BST left;
	    public BST right;

	    public BST(int value) {
	      this.value = value;
	    }
	}
	
}
