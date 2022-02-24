package AlgoExpert_Medium;

public class BSTTraversal {

	public static void main(String[] args) {
		BST root = new BST(10) ;
		root.insert(12) ; 
		
	}
	
	static class BST {
	    public int value;
	    public BST left;
	    public BST right;

	    public BST(int value) {
	      this.value = value;
	    }

	    public BST insert(int value) {
				
			if ( this == null ){
				return null ; 
			}
				else {
					
					if( this.value <= value ) {
						
						if (this.right == null ) {
							this.right = new BST(value) ;
						} else {
							return this.right.insert(value);
						}
						
					} else {
						
						if ( this.left == null ){
							this.left = new BST(value) ; 
						} else {
							return this.left.insert(value) ; 
						}
						
					}
					
					
				}
				
	      return this;
	    }

	    public boolean contains(int value) {
	      
			if ( this == null ) return false; 
			
				if ( this.value < value ){
					if (right ==null) return false; 
					return this.right.contains(value);
				} else if ( this.value == value ) {
					return true ; 
				} else {
					if(left==null) return false; 
					return this.left.contains(value) ;
				}
				
		
	    }

	    public BST remove(int value) {
	    		remove(value, null); // we pass null because we can also pass root whose parent would be null. 
				return this ;  		 // If value to be deleted is not found, then do nothing.
	    }
			
			public void remove(int value, BST parent) { // We are calling it parent because this node has left and right pointers which are its children. 
		
				if (value < this.value ) {
					if ( left != null ) {
						left.remove(value, this) ;
					}
				} else if ( value > this.value ) {
					if ( right != null) {
						right.remove(value, this);
					}
				} else { // Found the value. 
					
					if(left!=null && right!= null) { // 2 children node. 
						this.value = right.getMinValue(); 
						right.remove(this.value, this); 
					} 
					
					else if (parent == null ) {
						
						if(left!=null) {
							this.value = left.value ;
							right = left.right;
							left = left.left; 
						} else if(right!=null) {
							this.value = right.value ;
							left = right.left ;
							right = right.right ; 
						} else {
							// This is a single node tree ; do nothing
						}
						
					} 
					
					else if ( parent.left == this ) {
						parent.left = left != null ? left : right ; 
					} 
					
					else if (parent.right == this) {
						parent.right = left != null ? left : right ; 
					}				
				}				
			}
	  			
			public int getMinValue () {
				if(left ==null ) {
					return this.value ; 
				} else {
					return left.getMinValue() ; 
				}
			}
		
		}	
	
}
 