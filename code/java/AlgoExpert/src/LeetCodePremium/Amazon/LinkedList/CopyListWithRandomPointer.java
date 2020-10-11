package LeetCodePremium.Amazon.LinkedList;

import java.util.HashMap;
import java.util.Map;

// Return a deep copy of the linked list with random pointers.
public class CopyListWithRandomPointer {

    Map<Node, Node> originalNewMap = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node node = new Node(head.val);
        originalNewMap.put(head, node);
        copyRandomList(head.next);
        node.next = originalNewMap.get(head.next);
        if(head.random != null) node.random = originalNewMap.get(head.random);
        return node;
    }

    // O(n) time | O(n) space.
    public Node copyRandomList2(Node head){
        if(head == null)
            return null;

        Node dummy = new Node(0), copy = dummy, temp = head;

        HashMap<Node, Node> map = new HashMap<>();

        while(temp != null) {
            copy.next = new Node(temp.val);
            map.put(temp, copy.next);
            temp = temp.next;
            copy = copy.next;
        }

        copy = dummy.next;
        temp = head;

        while(copy != null) {
            copy.random = temp.random == null ? null : map.get(temp.random); // The value of the hashmap is a fresh node and it has null next and random pointers.
            copy = copy.next;
            temp = temp.next;
        }

        // remove bindings ?
        return dummy.next;
    }

    // O(N) time | O(1) space
    public Node copyRandomList3 (Node head){
        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {
            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    // My O(1) space solution: This modifies the current linked list.
    public static Node copyRandomList4 (Node head){
        if(head == null ){
            return null;
        }

        if(head.next == null){ // Very Important Edge Case.
            Node temp = new Node(head.val);
            temp.random = head.random == null ? null : temp;
            return temp;
        }

        Node first = head;
        Node second = head.next ;

        // Create cloned nodes.
        while(second != null){
            Node middle = new Node(first.val);
            first.next = middle;
            middle.next = second;
            first = second ;
            second = second.next;
        }

        first.next = new Node(first.val);

        // Assign random pointers.
        first = head;
        second = head.next;
        while(second.next != null){
            second.random = first.random == null ? null : first.random.next; // first.random.next can never be null.
            first = second.next;
            second = first.next;
        }

        second.random = first.random == null ? null : first.random.next;

        // Separate the two lists.
        // Will need 2 pointers in the final third iteration. Can do with just one pointer in the above 2 iterations. 
        first = head;
        second = head.next ;
        Node third = second;

        while(second.next != null){
            first.next = second.next;
            second.next = first.next.next;
            first = first.next;
            second = second.next;
        }

        first.next = null;

        return third;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}