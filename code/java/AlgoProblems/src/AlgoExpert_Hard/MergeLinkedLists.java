package AlgoExpert_Hard;

public class MergeLinkedLists {

	public static void main(String[] args) {
		
	}
	
	public static class LinkedList {
	    int value;
	    LinkedList next;
	
	    LinkedList(int value) {
	      this.value = value;
	      this.next = null;
	    }
    }

	public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {

		LinkedList currentOne = headOne.next; 
		LinkedList previousOne = headOne; 
		
		LinkedList currentTwo = headTwo.next; 
		LinkedList previousTwo = headTwo;
			
		while(currentOne != null || currentTwo != null) {
			
			if(previousOne.value > previousTwo.value && currentTwo != null) {
				
				while(previousOne.value > currentTwo.value) { // incrementing on the list which has a smaller value. 
					previousTwo = currentTwo ; 
					currentTwo = currentTwo.next ; 
				} 
				// joining the pointers
				previousTwo.next = previousOne ; 
								
				previousOne = currentOne; 
				
				if(currentOne != null)
					currentOne = currentOne.next ; // Save previousOne first. 
				
				previousTwo.next.next = currentTwo; 
				// 
				previousTwo = previousTwo.next; 
			}
			
			else {
				
				while(previousOne.value < currentTwo.value && currentOne != null ) {
					previousOne = currentOne ; 
					currentOne = currentOne.next ; 
				} 
				// joining the pointers
				previousOne.next = previousTwo ; 
				previousTwo = currentTwo; 
				
				if(currentTwo != null)
					currentTwo = currentTwo.next ; 
				
				previousOne.next.next = currentOne; 
				
				// 
				previousOne = previousOne.next; 
// Do not change current				
			}			
		}
		return null ;
	}
	
	// Assuming each of the linked lists have atleast one element. 
	public static LinkedList mergeLinkedLists2(LinkedList headOne, LinkedList headTwo) {
		LinkedList prev = null ; 
		LinkedList p1 = headOne; 
		LinkedList p2 = headTwo ; 
		 
		
		while(p1 != null && p2 != null ) {
			if(p1.value > p2.value) {
				if(prev!=null)
					prev.next = p2 ;
				
				prev = p2 ;  // Store p2 first before changing it. 
				p2 = p2.next ; 
				prev.next = p1; 
			} else {
				prev = p1 ; 
				p1 = p1.next ; 
			}
		}
		
		if(p1 == null ) { // Edge case 2
			prev.next = p2; 
		}
		// Edge case 3 is when p2 is null and we return head. 
		return headOne.value< headTwo.value ? headOne : headTwo; 
	}

	public static LinkedList mergeLinkedListsRecursive(LinkedList headOne, LinkedList headTwo) {
		
		LinkedList prev = null ; 
		LinkedList p1 = headOne; 
		LinkedList p2 = headTwo ; 
		
		mergeLinkedListsRecursiveHelper(prev, p1, p2); 
		return headOne.value< headTwo.value ? headOne : headTwo; 
	}

	
	public static void mergeLinkedListsRecursiveHelper(LinkedList prev, LinkedList p1, LinkedList p2) {
		
		if(p1 == null) {
			prev.next = p2 ; return ; 
		} 
		
		if(p2 == null) {
			return ; 
		}
		
		if(p1.value>= p2.value) {
			if(prev!=null)
				prev.next = p2 ; 
			prev = p2 ; // Store the p2 first. 
			p2 = p2.next ; 
			prev.next = p1 ; 
		} else {
			prev = p1; 
			p1 = p1.next ;
		}
		
		mergeLinkedListsRecursiveHelper(prev, p1, p2) ; 
	}
}















