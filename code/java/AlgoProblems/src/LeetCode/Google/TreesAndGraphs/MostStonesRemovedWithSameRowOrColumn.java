package LeetCode.Google.TreesAndGraphs;
/*
On a 2D plane, we place stones at some integer coordinate points.
Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column
or row with another stone on the grid.

What is the largest possible number of moves we can make?

Note:
1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
 */

import java.util.*;
public class MostStonesRemovedWithSameRowOrColumn {
    static class Node{
        String name;
        List<Node> neighbors = new ArrayList<>();
        int size;
        public Node(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        int[][] stones ={{0, 0}, {0, 1} , {1, 0} , {1, 1}, {2, 1}, {2, 2}, {3, 2}, {3, 3}, {3, 4}, {4, 3}, {4, 4}}; // 11
        System.out.println(removeStones(stones));
    }

    // Failed Strategy: Create examples.
    public static int removeStones(int[][] stones) {
        Map<String, Node> map = new HashMap<>();
        for(int[] stone: stones){
            String key = "" + stone[0] + "" + stone[1];
            if(!map.containsKey(key)){
                Node node = new Node(key);
                map.put(key, node);
            }
        }

        for(int i=0 ; i<stones.length ; i++){
            for(int j=i+1; j<stones.length;j++) {
                String firstKey = stones[i][0] + "" + stones[i][1];
                String secondKey = stones[j][0] + "" + stones[j][1];
                if (!firstKey.equals(secondKey) && (stones[i][0] == stones[j][0]  || stones[i][1] == stones[j][1])) {
                    map.get(firstKey).neighbors.add(map.get(secondKey));
                    map.get(firstKey).size++;
                    map.get(secondKey).neighbors.add(map.get(firstKey));
                    map.get(secondKey).size++;
                }
            }
        }

        List<Node> list = new ArrayList<>();
        for(Map.Entry<String, Node> entry: map.entrySet()){
            list.add(entry.getValue());
        }
        Collections.sort(list, (a, b) -> a.neighbors.size() - b.neighbors.size());

        int length = 0;

        // The graph can become disconnected in the middle of the algorithm and we could be left with more vertices than the optimal number.
        while( list.size() > 1 ){
            Node current = list.get(0);
            if(current.size > 0){
                //int neighborSize = current.neighbors.size();
                while( current.neighbors.size() > 0) {
                    Node neighbor = current.neighbors.get(0);
                    neighbor.neighbors.remove(current);
                    current.size--;
                    current.neighbors.remove(neighbor);
                    neighbor.size--;
                }

                length++;
                list.remove(current);
                Collections.sort(list, (a, b) -> a.neighbors.size() - b.neighbors.size());
            } else {
                list.remove(current);
            }
        }

        return length;
    }

    // O(n^2) space and time.
    public int removeStones2(int[][] stones) {
        if(stones.length == 0) return 0;
        if(stones.length == 1) return 0;

        int N = stones.length;
        int[][] graph = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    graph[i][++graph[i][0]]  = j;
                    graph[j][++graph[j][0]]  = i;
                }
            }
        }

        int numberOfIslands = 0;

        boolean[] visited = new boolean[N];
        // Iterate over the graph.
        for(int i=0 ; i<N; i++){
            if(!visited[i]){
                numberOfIslands += countIslands(graph, i, visited);
            }
        }

        return N - numberOfIslands;
    }

    public int countIslands(int[][] graph, int vertex, boolean[] visited){
        Stack<Integer> stack = new Stack<>();
        stack.add(vertex);

        while(!stack.isEmpty()){
            int current = stack.pop();
            visited[current] = true; // should mark here and not inside the if. because if this vertex has no neighbors then it will be left.
            // other logic: once a vertex comes out of a stack, mark it as visited.
            for(int k=1; k<=graph[current][0] ; k++){
                if(!visited[graph[current][k]]){
                    stack.add(graph[current][k]);
                }
            }
        }

        return 1;
    }
}
