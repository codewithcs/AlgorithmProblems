package LeetCode.Facebook.TreesAndGraph;

/*
Convert a Binary Search Tree to a sorted "Circular" Doubly-Linked List in place.
You can think of the left and right pointers as synonymous to the predecessor and successor pointers
in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element
is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree
node should point to its predecessor, and the right pointer should point to its successor.
You should return the pointer to the smallest element of the linked list.

Constraints:

-1000 <= Node.val <= 1000
Node.left.val < Node.val < Node.right.val
All values of Node.val are unique.
0 <= Number of Nodes <= 2000
 */

// Looks similar to Morris algorithm.
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    class Node{
        int val;
        Node left;
        Node right;
        Node(){}
        Node(int val){
            this.val = val;
        }
        Node(int val, Node left, Node right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            }
            else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

    // Approach 2: Morris Traversal, We do not need to restore tree and we invalidate left pointer to keep track of processed nodes.
    Node treeToDoublyList2(Node root) {
        if (root != null) {
            Node head = new Node(); //fake node for a start
            Node prev = head;       // remember previous node
            Node curr = root;       // start from the root
            while (curr != null) {
                // this is the most left unprocessed node
                if (curr.left == null){ // || curr.left.right == curr - add this condition if temp.left = rightmost below
                    curr.left = prev;
                    prev.right = curr;
                    prev = curr;
                    curr = curr.right;
                }
                else{
                    Node rightmost = curr.left; // find the rightmost child
                    while (rightmost.right != null) {
                        rightmost = rightmost.right;
                    }
                    if (rightmost.right == null){ // found the rightmost
                        rightmost.right = curr;
                        Node temp = curr;  // remember curr to invalidate it's left pointer
                        curr = curr.left;
                        temp.left = null; // we can also set it right away temp.left = rightmost but then we need to check for it
                    }
                }
            }
            prev.right = head.right; // at the end prev point to the last node
            head.right.left = prev;  // replace fake node with the last node in the tree
            head = head.right;
            return head;
        }
        return null;
    }
}
