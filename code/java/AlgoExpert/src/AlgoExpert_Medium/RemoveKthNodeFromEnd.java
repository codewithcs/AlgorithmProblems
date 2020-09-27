package AlgoExpert_Medium;

public class RemoveKthNodeFromEnd {
	
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList(0); 
		list.next = new LinkedList(1); 
		list.next.next = new LinkedList(2);
		list.next.next.next = new LinkedList(3);
		list.next.next.next.next = new LinkedList(4);
		list.next.next.next.next.next = new LinkedList(5);
		list.next.next.next.next.next.next = new LinkedList(6);
		list.next.next.next.next.next.next.next = new LinkedList(7);
		list.next.next.next.next.next.next.next.next = new LinkedList(8);
		list.next.next.next.next.next.next.next.next.next = new LinkedList(9);
		
		removeKthNodeFromEnd(list, 10) ;
		
		LinkedList current = list;  
		
		while(current!=null) {
			System.out.println(current.value);
			current = current.next; 
		}
		
	}
	
	public static void removeKthNodeFromEnd(LinkedList head, int k) {

	// find the length of the linked list. 
		
		int length = 0 ; 
		LinkedList current = head ;
		while(current != null ) {
			length ++ ; 
			current = current.next ;
		}
		
		int positionFromStart = length - k + 1; 
		
		current = head ;
		int i= 1 ; 
		
		while(i < positionFromStart-1) {
			current= current.next ; 	
			i++;
		}
		
		System.out.println("positionFromStart is : " + positionFromStart); // strange behaviour when positionFromStart is 1. 
		
		if(positionFromStart != 1)
			current.next = current.next.next ; 
		else {
			current = current.next; 
			head = current;
		}
		
        current = head;  
		
		while(current!=null) {
			System.out.println(current.value);
			current = current.next; 
		}
		System.out.println();		
	}

	
	
	  static class LinkedList {
	    int value;
	    LinkedList next = null;

	    public LinkedList(int value) {
	      this.value = value;
	    }
	  }
	  	  
}