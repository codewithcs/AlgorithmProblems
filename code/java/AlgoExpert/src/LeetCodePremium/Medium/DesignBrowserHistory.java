package LeetCodePremium.Medium;

import java.util.ArrayList;
import java.util.List;

/*
You have a browser of one tab where you start on the "homepage" and you can visit another url,
get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

1. BrowserHistory(string homepage)
Initializes the object with the homepage of the browser.

2. void visit(string url)
Visits url from the current page. It clears up all the forward history.

3. string back(int steps)
Move steps back in history.
If you can only return x steps in the history and steps > x, you will return only x steps.
Return the current url after moving back in history at most steps.

4. string forward(int steps)
Move steps forward in history.
If you can only forward x steps in the history and steps > x, you will forward only x steps.
Return the current url after forwarding in history at most steps.

Constraints:
1 <= homepage.length <= 20
1 <= url.length <= 20
1 <= steps <= 100
homepage and url consist of  '.' or lower case English letters.
At most 5000 calls will be made to visit, back, and forward.
 */
public class DesignBrowserHistory {
}

class BrowserHistory {

    class Node{
        String name;
        Node next;
        Node prev;

        public Node(String name){
            this.name = name; next = null ; prev = null;
        }
    }

    Node head ; Node current = null; Node tail;
    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        current = head;
        tail = head;
    }

    // Add new node in doubly linked list.
    public void visit(String url) {
        Node newNode = new Node(url);
        if(current.next != null){ // Remove Bindings. No need to do this as we won't have the reference for current.next and it will be garbage collected.
            current.next.prev = null;
        }
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
        tail = current;
    }

    // Iterate backwards on linked list. O(min(steps, length))
    public String back(int steps) {
        Node n = current;

        while(steps > 0 && n != null){
            n = n.prev;
            steps--;
        }

        current = n;

        if(n == null){
            current = head;
        }

        return current.name;
    }

    // Iterate forward on linked list. O(min(steps, length))
    public String forward(int steps) {
        Node n = current;
        while(steps > 0 && n != null){
            n = n.next;
            steps--;
        }

        current = n;

        if(n == null){
            current = tail;
        }

        return current.name;
    }
}
