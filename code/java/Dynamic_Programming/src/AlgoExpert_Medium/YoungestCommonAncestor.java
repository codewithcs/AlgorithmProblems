package AlgoExpert_Medium;

import java.util.ArrayList;
import java.util.List;

public class YoungestCommonAncestor {

	 public static AncestralTree getYoungestCommonAncestor(
			 AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
			 
		        AncestralTree one = descendantOne; 
			    AncestralTree two = descendantTwo;
			  
			    List<AncestralTree> list1 = new ArrayList<>(); 
			
				while(one.ancestor != null ){
					if(one.ancestor.name == two.name) {
						return two ; 
					}
					list1.add(one.ancestor) ; 
					one = one.ancestor;
			    }
			
			    one = descendantOne;
				
			    List<AncestralTree> list2 = new ArrayList<>(); 
	
				while(two.ancestor != null ){
					if(two.ancestor.name == one.name) {
						return one ; 
					}
					list2.add(two.ancestor) ; 
					two = two.ancestor;
				}
			
			// Find the first common element in list 1 and list2. 
			
			    for(int i=0 ; i<list1.size(); i++) {
				
					for(int j=0; j<list2.size() ; j++) {
						if( list1.get(i) == list2.get(j) ) {
							return list1.get(i) ;
						}
					}
				
			    }
			
			
				return null;
	}
	 
	 
	 public static AncestralTree getYoungestCommonAncestorO1Space(
			 AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
		 
		 int depthOne = getDescendantDepth(descendantOne, topAncestor); 
		 int depthTwo = getDescendantDepth(descendantTwo, topAncestor) ; 
		 
		 if(depthOne > depthTwo ) {
			 return backtrackAncestralTree(descendantOne, descendantTwo, depthOne - depthTwo) ;  
		 } else {
			 return backtrackAncestralTree(descendantTwo, descendantOne, depthTwo - depthOne ) ;
		 }
		 
	 }
	 
	 public static int getDescendantDepth(AncestralTree descendant, AncestralTree topAncestor) {
		 
		 int depth = 0; 
		 
		 while( descendant != topAncestor ) {
			 depth++;
			 descendant = descendant.ancestor; 
		 }
		 
		 return depth ;
		 
	 }
	 
	 public static AncestralTree backtrackAncestralTree(AncestralTree lowerDescendant, AncestralTree higherDescendant, int diff) {
		 
		 while( diff>0 ) {
			 lowerDescendant = lowerDescendant.ancestor ; 
			 diff-- ; 		 
		 }
		 
		 while(lowerDescendant!=higherDescendant) {
			 lowerDescendant = lowerDescendant.ancestor; 
			 higherDescendant = higherDescendant.ancestor ; 
		 }
		 
		 return lowerDescendant; 
		 
	 }
	 

		  static class AncestralTree {
		    public char name;
		    public AncestralTree ancestor;

		    AncestralTree(char name) {
		      this.name = name;
		      this.ancestor = null;
		    }

		    // This method is for testing only.
		    void addAsAncestor(AncestralTree[] descendants) {
		      for (AncestralTree descendant : descendants) {
		        descendant.ancestor = this;
		      }
		    }
		    
		  }
}
