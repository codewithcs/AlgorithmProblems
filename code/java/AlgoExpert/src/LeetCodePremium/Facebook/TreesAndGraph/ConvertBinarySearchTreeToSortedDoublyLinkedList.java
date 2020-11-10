package LeetCodePremium.Facebook.TreesAndGraph;

/*
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
You can think of the left and right pointers as synonymous to the predecessor and successor pointers
in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element
is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree
node should point to its predecessor, and the right pointer should point to its successor.
You should return the pointer to the smallest element of the linked list.
 */
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

    public Node treeToDoublyList(Node root) {
        return null;
    }
}
