package AlgoExpert_Hard;

public class FindLoop {

	public static void main(String[] args) {
		
	}
	
	// Assuming there is always a cycle. If there is not a cycle then we have to check for nulls.
	// Question to ask the interviewer: Can I assume that the cycle is always there ? 
	public static LinkedList findLoop(LinkedList head) {

		LinkedList first = head.next ; 
		LinkedList second = head.next.next ; 
		
		while(first != second ) { // Compare the full node as value and next both must be same. 
			first = first.next ;
			second = second.next.next ; 
		}
		
		first = head; 
		
		while(first != second) {
			first = first.next ; 
			second = second.next;
		}
		
		return first;
	}

    static class LinkedList {
	    int value;
	    LinkedList next = null;
	
	    public LinkedList(int value) {
	      this.value = value;
	    }
    }
    
    
    public static LinkedList findLoop3(LinkedList head) {
        LinkedList slow = head;
      	LinkedList fast = head;

          while(fast != null && fast.next != null) { // check for null on the fast pointer
              slow = slow.next;          // 1 hop
              fast = fast.next.next;     // 2 hops 
              if(slow == fast) { /// cannot have the opposite check in the while itself. 
            	  break;
              }
          }
  		
  		 slow = head; 
  		
  		 while(fast != null && fast.next != null && slow != fast) { 
              slow = slow.next;         
              fast = fast.next;     
          }
  	
          return slow;  
    }
     
    // Floyd's tortoise and hare algorithm.
    boolean hasLoop(LinkedList first) {
    	LinkedList slow = first;
    	LinkedList fast = first;

        while(fast != null && fast.next != null) { // check for null on the fast pointer
            slow = slow.next;          // 1 hop
            fast = fast.next.next;     // 2 hops 

            if(slow == fast)  // fast caught up to slow, so there is a loop
                return true;
        }
        return false;  // fast reached null, so the list terminates
    }

/*
This is similar to the hare and the tortoise [Floyd's cycle] except that, the slow node here doesn't move, 
but is later "teleported" to the position of the fast node at fixed intervals.
Brent's Cycle Detection Algorithm, The Teleporting Turtle
*/
    
    public static boolean hasLoop2(LinkedList root) { 
        if(root == null) return false;

        LinkedList slow = root, fast = root;
        int taken = 0, limit = 2;

        while (fast.next != null) {
            fast = fast.next;
            taken++;
            if(slow == fast) return true;

            if(taken == limit){
                taken = 0;
                limit <<= 1;    // equivalent to limit *= 2;
                slow = fast;    // teleporting the turtle (to the hare's position) 
            }
        }
        return false;
    }
    
    
}
