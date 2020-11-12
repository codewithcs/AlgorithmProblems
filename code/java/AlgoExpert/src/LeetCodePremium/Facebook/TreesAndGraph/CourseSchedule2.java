package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.*;

/*
Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */

public class CourseSchedule2 {
    class Node{
        int indegree;
        List<Integer> outnodes = new ArrayList<>();
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, Node> graph = new HashMap<>();

        for(int i=0 ; i<numCourses; i++){ // Important Step
            graph.put(i, new Node());
        }

        // Keep an eye on constraints.
        for(int[] element: prerequisites){
            int current = element[1];
            int next = element[0];
            graph.computeIfAbsent(current, x->new Node()).outnodes.add(next);
            graph.computeIfAbsent(next, x->new Node()).indegree++;
            // Don't really need to worry about if the key is present in the map or not.
        }

        Queue<Integer> queue = new LinkedList<>();
        for(Integer key: graph.keySet()){
            if(graph.get(key).indegree==0){
                queue.add(key);
            }
        }

        List<Integer> order =  new ArrayList<>();

        while(!queue.isEmpty()){
            Integer current = queue.poll();
            order.add(current);

            for(Integer neighbor: graph.get(current).outnodes){
                graph.get(neighbor).indegree--;
                if(graph.get(neighbor).indegree == 0){
                    queue.add(neighbor);
                }
            }
        }

        if(order.size() < graph.size()){
            return new int[]{};
        }

        int[] output = new int[order.size()];
        for(int i=0 ; i<output.length; i++){
            output[i] = order.get(i);
        }

        return output;
    }
}
