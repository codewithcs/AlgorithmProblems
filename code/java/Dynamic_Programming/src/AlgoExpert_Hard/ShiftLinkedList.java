package AlgoExpert_Hard;

public class ShiftLinkedList {

	public static LinkedList shiftLinkedList(LinkedList head, int k) {

		LinkedList p = head ; 
		int size = 1; 
		while(p.next != null ) {
			p = p.next ; size++ ; 
		}
		LinkedList tail = p; 
		
		int i =0 ; 
		if(k%size==0) {
			return head;
		}
		else if(k>0) {
			i= size - k%size ; 
		} else {
			i = (-k)%size; 
		}
				
		p = head ;
		
		while(i>1) {
			p = p.next; 
			i-- ; 
		}
		LinkedList newTail = p ; 
		LinkedList newHead = p.next ; 

		newTail.next = null ;
		tail.next = head; 
		
		return newHead;
	  }

	public static LinkedList shiftLinkedList2(LinkedList head, int k) {
		
		return null ;
	}

	
	  static class LinkedList {
	    public int value;
	    public LinkedList next;

	    public LinkedList(int value) {
	      this.value = value;
	      next = null;
	    }
	  }
	
}
