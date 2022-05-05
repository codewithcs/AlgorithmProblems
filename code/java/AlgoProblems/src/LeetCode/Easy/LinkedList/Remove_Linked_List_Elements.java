package LeetCode.Easy.LinkedList;


/*
Given the head of a linked list and an integer val,
remove all the nodes of the linked list that
has Node.val == val, and return the new head.

Constraints:
The number of nodes in the list is in the range [0, 10^4].
1 <= Node.val <= 50
0 <= val <= 50
 */
public class Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;

        ListNode current = head;
        while(current.next != null){
            if(current.next.val == val){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        if(head.val == val){
            head = head.next;
        }

        return head;
    }
}
