package LeetCodePremium.Amazon.LinkedList;

/*
Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

import java.util.List;

public class ReverseLinkedList {

    /// O(N) time | O(1) space.
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode first = null;
        ListNode second = head;
        ListNode third =null;

        if(head.next == null){ // edge case
            return head;
        } else {
            while (second != null) {
                third = second.next;
                second.next = first;
                first = second;
                second = third;
            }

            head = first;
            return head;
        }
    }

    /// O(N) time | O(N) space.
    public ListNode reverseListRecursive(ListNode head){
        if(head == null){
            return null ;
        }

        ListNode first = null;
        ListNode second = head;

        if(head.next == null){ // edge case.
            return head;
        } else{
            return helper(first, second);
        }
    }

    public ListNode helper(ListNode first, ListNode second){
        if(second == null){
            return first;
        }
        ListNode third = second.next;
        second.next = first;
        first = second;
        second = third;
        return  helper(first, second);
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
