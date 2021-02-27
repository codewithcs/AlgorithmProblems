package LeetCodePremium.Medium;

/*
Given the head of a singly linked list and two integers left and right
where left <= right, reverse the nodes
of the list from position left to position right,
and return the reversed list.

Constraints:
The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n
 */
public class ReverseLinkedList2 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val; this.next = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
    }
}
