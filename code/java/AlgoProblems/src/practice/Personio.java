package practice;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'validateCompanyStrcuture' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY reportingLines as parameter.
     */

    static class Node{
        int outDegrees = 0;
        int inDegrees = 0;
        List<String> neighbors = new ArrayList<>();
    }

    public static String validateCompanyStrcuture(List<String> reportingLines) {
        Map<String, Node> map = new HashMap<>();
        int numberOfEdges = 0;

        for(String current: reportingLines){
            String[] lines = current.split(" ");
            Node previous = getCreateNode(map, lines[0]);
            Node next = getCreateNode(map, lines[1]);

            previous.neighbors.add(lines[1]);
            next.inDegrees++;
            previous.outDegrees++;
            numberOfEdges++;
        }

        System.out.println("Number of Edges is : " + numberOfEdges);
        LinkedList<String> list = new LinkedList<>();

        // Count number of nodes which have outdegree of 0. these will be the CEOs.
        List<String> ceo = new ArrayList<>();
        int outDegreeZeroCount = 0;
        for(Map.Entry<String, Node> entry: map.entrySet()){
            if(entry.getValue().outDegrees == 0){
                outDegreeZeroCount++;
                ceo.add(entry.getKey());
            }
        }


        for(Map.Entry<String, Node> entry: map.entrySet()){
            Node n = entry.getValue();
            if(n.inDegrees == 0){
                list.add(entry.getKey());
            }
        }

        int removedEdges = 0;

        while(list.size() > 0){
            String current = list.pop();

            for(String next : map.get(current).neighbors){
                Node n = map.get(next);
                n.inDegrees--;
                removedEdges++;

                if(n.inDegrees == 0){
                    list.add(next);
                }
            }
        }

        List<List<String>> cycles = new ArrayList<>();
        int currentPointer = 0;
        int loops = 0;

        // If this if is true then there are cycles.
        if(removedEdges != numberOfEdges){
            // int numberOfRemainingEdges = numberOfEdges - removedEdges;
            Set<String> visited = new HashSet<>();

            for(Map.Entry<String, Node> entry: map.entrySet()){
                String name = entry.getKey();

                // Generate the cycle list.
                if(!visited.contains(name) && map.get(name).inDegrees > 0){
                    List<String> neighbors = entry.getValue().neighbors;

                    Stack<String> stack = new Stack<>();
                    stack.add(name);

                    cycles.add(currentPointer, new ArrayList<String>());
                    while(!stack.isEmpty()){
                        String current = stack.pop();
                        cycles.get(currentPointer).add(current);
                        visited.add(current);

                        for(String n : neighbors){
                            if(!visited.contains(n)){
                                stack.add(n);
                            }
                        }
                    }

                    currentPointer++;
                }
            }

            loops = cycles.size();
            System.out.println("Number of loops is : " + loops) ;

            if(loops > 1){
                return "Multiple loops were detected";
            }

            // If we reach here, Only 1 cycle will be there.
            List<String> answer = cycles.get(0);
            Set<String> answerSet = new HashSet<>();
            for(String a : answer) answerSet.add(a);
            answer = new ArrayList<>();
            for(String a : answerSet) answer.add(a);

            Collections.sort(answer);
            return "A loop was detected involving: " + String.join(", ", answer);
        }

        // If we reach here, then there are no cycles.

        if(outDegreeZeroCount > 1){
            Collections.sort(ceo);
            return "Multiple CEOs found: " + String.join(", ", ceo);
        }

        return "The company structure is valid";

    }

    public static Node getCreateNode(Map<String, Node> graph, String name){
        Node n = null;
        if(graph.containsKey(name)){
            n = graph.get(name);
        } else {
            n = new Node();
            graph.put(name, n);
        }
        return n;
    }

}

