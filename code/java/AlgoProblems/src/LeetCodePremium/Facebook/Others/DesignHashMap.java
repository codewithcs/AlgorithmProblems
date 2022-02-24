package LeetCodePremium.Facebook.Others;

public class DesignHashMap {
    Node[] nodes;
    int capacity = 2096;

    public DesignHashMap() {
        nodes = new Node[capacity];
    }

    public void put(int key, int value) {
        Node prev = find(key);
        if (prev.next == null) {
            prev.next = new Node(key, value);
        }
        else {
            prev.next.val = value;
        }
    }

    public int get(int key) {
        Node cur = find(key).next;
        return cur == null ? -1 : cur.val;
    }

    public void remove(int key) {
        Node prev = find(key);
        if (prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    public Node find(int key) {
        int idx = key % capacity;
        if (nodes[idx] == null) {
            nodes[idx] = new Node(-1, -1);
        }
        Node prev = nodes[idx];
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    class Node {
        int key;
        int val;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
