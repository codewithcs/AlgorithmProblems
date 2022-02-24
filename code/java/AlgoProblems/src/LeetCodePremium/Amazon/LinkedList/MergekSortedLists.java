package LeetCodePremium.Amazon.LinkedList;

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Constraints:
    k == lists.length
    0 <= k <= 10^4
    0 <= lists[i].length <= 500
    -10^4 <= lists[i][j] <= 10^4
    lists[i] is sorted in ascending order.
    The sum of lists[i].length won't exceed 10^4.
 */

import java.util.PriorityQueue;

public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(ListNode list: lists){
            while(list != null){
                minHeap.add(list.val);
                list = list.next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while(!minHeap.isEmpty()){
            current.next = new ListNode(minHeap.remove());
            current = current.next;
        }
        return dummy.next;
    }

    // Merge Lists one by one.
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans=h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            h.next=l2;
        }
        if(l2==null){
            h.next=l1;
        }
        return ans.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length==1){
            return lists[0];
        }
        if(lists.length==0){
            return null;
        }
        ListNode head = mergeTwoLists(lists[0],lists[1]);
        for (int i = 2; i < lists.length; i++) {
            head = mergeTwoLists(head,lists[i]);
        }
        return head;
    }

    // Divide and Conquer:
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans=h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            h.next=l2;
        }
        if(l2==null){
            h.next=l1;
        }
        return ans.next;
    }
    public ListNode mergeKLists3(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        int interval = 1;

        while(interval<lists.length){
            System.out.println(lists.length);
            for (int i = 0; i + interval< lists.length; i=i+interval*2) {
                lists[i] = mergeTwoLists2(lists[i],lists[i+interval]);
            }

            interval*=2;
        }

        return lists[0];
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}