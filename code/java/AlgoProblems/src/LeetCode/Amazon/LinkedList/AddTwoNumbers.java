package LeetCode.Amazon.LinkedList;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        ListNode P1 = l1;
        ListNode P2 = l2;
        int sum = 0; // important initialization.


        // Here sum and dummy are both getting changed. So it is difficult to refactor into a function.
        while (P1 != null && P2 != null) {
            sum += P1.val + P2.val;

            if (sum <= 9) {
                dummy.next = new ListNode(sum);
                sum = 0;
            } else {
                dummy.next = new ListNode(sum % 10);
                sum = 1; /// sum over.
            }

            dummy = dummy.next;
            P1 = P1.next;
            P2 = P2.next;
        }

        // When P1 is larger.
        while (P1 != null) {
            sum = sum + P1.val;
            if (sum <= 9) {
                dummy.next = new ListNode(sum);
                sum = 0;
            } else {
                dummy.next = new ListNode(sum % 10);
                sum = 1;
            }
            dummy = dummy.next;
            P1 = P1.next;
        }


        // When P2 is larger.
        while (P2 != null) {
            sum = sum + P2.val;
            if (sum <= 9) {
                dummy.next = new ListNode(sum);
                sum = 0;
            } else {
                dummy.next = new ListNode(sum % 10);
                sum = 1;
            }
            dummy = dummy.next;
            P2 = P2.next;
        }

        if (sum == 1) {
            dummy.next = new ListNode(1);
        }

        return head.next;
    }

     public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
