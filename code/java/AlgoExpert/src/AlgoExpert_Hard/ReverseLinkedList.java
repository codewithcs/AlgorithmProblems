package AlgoExpert_Hard;

public class ReverseLinkedList {

	public static void main(String[] args) {
		
	}
	
	public static LinkedList reverseLinkedList(LinkedList head) {
		LinkedList p1 = null; 
		LinkedList p2 = head; 
		
		while(p2 != null) {
			LinkedList p3 = p2.next ;
			p2.next = p1;
			p1 = p2 ;
			p2 = p3 ;
		}
		return p1 ; 
	}
	
	public static LinkedList reverseLinkedList2(LinkedList head) {
		LinkedList p1 = head; 
		LinkedList p2 = head.next; p1.next = null;
		
		while(p2 != null){
			LinkedList p3 = p2.next; // We need to store p2.next first because if we change it, we lose further references to the linked list.
			p2.next = p1 ;
			p1 = p2; 
			p2 = p3; 
			
		}
		return p1 ;
  }

	  static class LinkedList {
	    int value;
	    LinkedList next = null;

	    public LinkedList(int value) {
	      this.value = value;
	    }
	  }
}
