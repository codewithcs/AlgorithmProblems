package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.ArrayList;
import java.util.List;

/*
Constraints:

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */

public class CloneGraph {
    class Node{
        int val ;
        List<Node> neighbors;

        public Node(){
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        return null;
    }
}
