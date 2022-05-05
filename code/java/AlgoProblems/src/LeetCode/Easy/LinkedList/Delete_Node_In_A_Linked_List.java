package LeetCode.Easy.LinkedList;

/*
Write a function to delete a node in a singly-linked list.
You will not be given access to the head of the list,
instead you will be given access to the node to be deleted directly.

It is guaranteed that the node to be deleted
is not a tail node in the list.

The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node
 */

 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class Delete_Node_In_A_Linked_List {
    public void deleteNode(ListNode node) {
        if(node == null) return;

        ListNode current = node;
        ListNode secondLastNode = null ;
        while(current.next != null){
            current.val = current.next.val;
            if(current.next.next == null) secondLastNode = current;
            current = current.next;
        }

        secondLastNode.next = null;
    }

    public void deleteNode2(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
