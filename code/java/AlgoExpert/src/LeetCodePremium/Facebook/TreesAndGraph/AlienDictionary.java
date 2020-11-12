package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {

        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();

        // count for in degrees.
        Map<Character, Integer> counts = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }

    class Node{
        int indegree ;
        List<Character> outnodes = new ArrayList<>();
    }

    public String alienOrder2(String[] words) {
        Map<Character, Node> graph = new HashMap<>();

        for(String word: words){
            for(Character c: word.toCharArray()){
                graph.computeIfAbsent(c, x-> new Node());
            }
        }

        // Graph Creation.
        for(int i=0; i< words.length-1; i++){
            String current = words[i];
            String next = words[i+1];

            if(current.length() > next.length() && current.startsWith(next)){
                return "";
            }

            int j=0;

            while(j < Math.min(current.length(), next.length())){
                if(current.charAt(j) != next.charAt(j)){ // first non-matching.
                    Node n = graph.computeIfAbsent(current.charAt(j), x-> new Node());
                    n.outnodes.add(next.charAt(j));
                    graph.computeIfAbsent(next.charAt(j), x->new Node()).indegree++;
                    break;
                }
                j++ ;
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(Character c: graph.keySet()){
            if(graph.get(c).indegree == 0){
                queue.add(c);
            }
        }

        int uniqueNodes = graph.size();
        String order = "";


        while(!queue.isEmpty()){
            Character current = queue.poll();
            order += current;
            for(Character neighbor: graph.get(current).outnodes){
                graph.get(neighbor).indegree--;
                if( graph.get(neighbor).indegree == 0){
                    queue.add(neighbor);
                }
            }
        }

        if(order.length() < uniqueNodes){
            return "";
        }

        return order;
    }
}
