package LeetCode.Google.TreesAndGraphs;

/*
You are given an array of variable pairs equations and an array of real numbers values,
where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you
must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result
in division by zero and that there is no contradiction.

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */

import java.util.*;

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

        // Step 1). build the graph from the equations
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<String, Double>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<String, Double>());
            }

            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }

        // Step 2). Evaluate each query via bactracking (DFS)
        // by verifying if there exists a path from dividend to divisor
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) { // graph does not have source or destination node.
                results[i] = -1.0;
            } else if (dividend == divisor) {
                results[i] = 1.0;
            } else {
                HashSet<String> visited = new HashSet<>();
                results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
            }
        }

        return results;
    }

    private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph, String currNode, String targetNode, double accProduct, Set<String> visited) {
        visited.add(currNode);
        double ret = -1.0;

        Map<String, Double> neighbors = graph.get(currNode);
        if (neighbors.containsKey(targetNode)) {
            ret = accProduct * neighbors.get(targetNode);
        } else {
            for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
                String nextNode = pair.getKey();
                if (visited.contains(nextNode)) {
                    continue;
                }
                ret = backtrackEvaluate(graph, nextNode, targetNode, accProduct * pair.getValue(), visited);
                if (ret != -1.0) {
                    break;
                }
            }
        }

        return ret; // Important Note: Don't need to unmark the node.
    }

    // Backtracking using a Stack.
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

        for(int i=0; i < equations.size() ; i++){
            String first = equations.get(i).get(0);
            String second = equations.get(i).get(1);

            if(!graph.containsKey(first)){
                graph.put(first, new HashMap<String, Double>());
            }
            if(!graph.containsKey(second)){
                graph.put(second, new HashMap<String, Double>());
            }

            graph.get(first).put(second, values[i]);
            graph.get(second).put(first, 1/values[i]);
        }

        double[] answer = new double[queries.size()];

        for(int i=0; i<queries.size(); i++){
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            if(!graph.containsKey(start) || !graph.containsKey(end)){
                answer[i] = -1.0;
            }
            else if(start.equals(end)){
                answer[i] = 1.0;
            }
            else {
                answer[i] = dfs(start, end, graph);
            }
        }

        return answer;
    }

    class Node{
        String name;
        Double cost;
        public Node(String name, Double cost){
            this.name = name;
            this.cost = cost;
        }
    }

    public double dfs(String start, String end, HashMap<String, HashMap<String, Double>> graph){
        double answer = 1.0;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(start, 1.0));
        Set<String> visited = new HashSet<>();

        while(!stack.isEmpty()){
            Node current = stack.pop();
            String currentName = current.name;
            Double currentCost = current.cost;
            visited.add(currentName);

            if(graph.get(currentName).containsKey(end)){
                return currentCost * graph.get(currentName).get(end);
            } else {
                for(String neighbor : graph.get(currentName).keySet()){
                    if(!visited.contains(neighbor)){
                        double cost = currentCost * graph.get(currentName).get(neighbor);
                        stack.push(new Node(neighbor, cost));
                    }
                }
            }
        }

        return -1.0;
    }

}
