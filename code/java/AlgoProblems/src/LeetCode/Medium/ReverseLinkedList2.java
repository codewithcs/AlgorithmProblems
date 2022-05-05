package LeetCode.Medium;

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
        if(left >= right || head == null){
            return head;
        }

        ListNode head1Previous = head;
        ListNode head1 = null;

        int left1 = left-2;

        if(left- 2 > 0){
            while(left1 > 0 && head1Previous != null){
                head1Previous = head1Previous.next; left1--;
            }

            head1 = head1Previous.next;
        } else {
            head1 = left == 2 ? head.next : head;
        }


        int numOfIterations = right-left;

        ListNode current = head1.next;

        while(current != null && numOfIterations > 0){
            ListNode third = current.next;
            current.next = head1;
            head1 = current;
            current = third;
            numOfIterations--;
        }

        ListNode second = left>=2 ? head1Previous.next : head1Previous;

        if(left>=2){
            head1Previous.next = head1;
        }

        second.next = current;

        return left>=2 ? head : head1;
    }
}
