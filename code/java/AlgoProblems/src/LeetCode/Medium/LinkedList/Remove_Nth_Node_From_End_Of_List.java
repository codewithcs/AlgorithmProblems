package LeetCode.Medium.LinkedList;

/*
iven the head of a linked list,
remove the nth node from the end of the list and return its head.

Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */
public class Remove_Nth_Node_From_End_Of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy; ListNode fast = dummy;
        while(n > 0){
            fast = fast.next;
            n--;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        // The node to be deleted will be slow.next.
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Better Solution.
    // Merge the 2 while loops in a single loop.
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode slow = newHead;
        ListNode fast = newHead;

        for(int i=1; fast.next != null ; i++){
            if(i<= n){
                fast = fast.next;
                // If n is too large, then i < n. We can make i global and then check if i < n.
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }

        slow.next = slow.next.next;
        return newHead.next;
    }

}
