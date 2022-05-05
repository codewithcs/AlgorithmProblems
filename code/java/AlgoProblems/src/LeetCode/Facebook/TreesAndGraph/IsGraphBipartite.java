package LeetCode.Facebook.TreesAndGraph;

/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B,
such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
Each node is an integer between 0 and graph.length - 1.

There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Constraints:

1 <= graph.length <= 100
0 <= graphp[i].length < 100
0 <= graph[i][j] <= graph.length - 1
graph[i][j] != i
All the values of graph[i] are unique.
The graph is guaranteed to be undirected.
 */

import java.util.Arrays;
import java.util.Stack;

public class IsGraphBipartite {
    // // unique values of the vertices.
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // makes sure that we color every node and also takes into the account the disconnected components.
        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int neighbor: graph[node]) {
                        if (color[neighbor] == -1) {
                            stack.push(neighbor); // only push the neighbor in the stack when it has not been processed yet.
                            color[neighbor] = color[node] ^ 1;
                        } else if (color[neighbor] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
