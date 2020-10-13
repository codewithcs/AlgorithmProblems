package LeetCodePremium.Amazon.LinkedList;

/*
Merge two sorted linked lists and return it as a new sorted list.
The new list should be made by splicing together the nodes of the first two lists.
Both l1 and l2 are sorted in non-decreasing order.
 */

public class Merge2SortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null) return null ;
        if(l1 == null) return l2 ;
        if(l2 == null) return l1;

        ListNode headOne = l1;
        ListNode headTwo = l2;

        ListNode P1 = l1;
        ListNode P2 = l2;
        ListNode previous = null ;

        // In the end, I have do the converge in one list. I am converging into List P2.

        // Edge Case: When previous is null.
        if(P1.val <= P2.val){
            previous = P1;
            P1 = P1.next;
            previous.next = P2;
        } else {
            previous = P2;
            P2 = P2.next;
        }

        while(P1 != null && P2 != null ){
            if(P1.val <= P2.val){
                previous.next = P1;
                previous = P1;
                P1 = P1.next;
                previous.next = P2 ;
            } else{
                P2 = P2.next;
                previous = previous.next;
            }
        }

        if(P1 != null){
            previous.next = P1;
        }

        if(headOne.val <= headTwo.val){
            return headOne;
        } else{
            return headTwo;
        }
    }


    class ListNode{
        int val;
        ListNode next;
        ListNode(){
        }
        ListNode(int val){this.val = val ;}
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
}
