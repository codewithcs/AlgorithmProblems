package AlgoExpert_Medium;

public class ValidateBST {

	public static void main(String[] args) {
		
	}
	
	public static boolean validateBST(BST tree, int minValue, int maxValue) { // From the root, pass in MIN_VALUE and MAX_VALUE 
		
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
