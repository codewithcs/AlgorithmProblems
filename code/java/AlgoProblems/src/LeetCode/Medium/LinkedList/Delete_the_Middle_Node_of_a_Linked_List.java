package LeetCode.Medium.LinkedList;

/*
You are given the head of a linked list.
Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node
from the start using 0-based indexing, where ⌊x⌋ denotes the
largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes
are 0, 1, 1, 2, and 2, respectively.

Constraints:
The number of nodes in the list is in the range [1, 10^5].
1 <= Node.val <= 10^5
 */
 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class Delete_the_Middle_Node_of_a_Linked_List {
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return null;

        int length = 0;
        ListNode current = head;

        while(current != null){
            length++;
            current = current.next;
        }

        int n = length/2+1;
        current = head;
        while(n > 2){
            current = current.next;
            n--;
        }

        current.next = current.next.next;
        return head;
    }

    // Slow Fast Pointers.
    public ListNode deleteMiddle2(ListNode head) {
        // Base Condition
        if(head == null || head.next == null) return null;

        // Pointers Created
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;

        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Node to be deleted will be the slow node.
        prev.next = slow.next;

        return head;
    }

}
