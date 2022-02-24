package Blackrock_coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class q3 {
     static class Main {
        /**
         * Iterate through each line of input.
         */
        public static void main(String[] args) throws IOException {
            InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(reader);
            String line;
            while ((line = in.readLine()) != null) {
                determineExchangeRate(line);
            }
        }
        private static void determineExchangeRate(String line) {
            // Access your code here. Feel free to create other classes as required
            int inputLength = line.length();
            String start = line.substring(inputLength-6, inputLength - 3);
            String end = line.substring(inputLength-3);

            List<Node> data = new ArrayList<>();
            double answer = determineExchangeRate(line, data);
            if(answer != -1){
                System.out.format(start + end + ":%.2f", answer);
            } else {
                System.out.println("Unable to determine rate for " + start + end);
            }
        }

        private static double determineExchangeRate(String input, List<Node> data){
             int inputLength = input.length();
             String start = input.substring(inputLength-6, inputLength - 3);
             String end = input.substring(inputLength-3);

             if(start.equals(end)){
                 return 1.00;
             }

             String temp = input.split("\\|")[0];
             String[] inputs = temp.split(";");

             for(int i=0; i < inputs.length ; i++){
                 String first = inputs[i].substring(0, 3);
                 String second = inputs[i].substring(3, 6);
                 double rate = Double.parseDouble(inputs[i].substring(7));
                 data.add(new Node(first, second, rate));
             }

             return getRatio(start, end, data);
         }

         private static double getRatio(String start, String end, List<Node> data) {
             Map<String, Map<String, Double>> map = new HashMap<>();
             for (Node node: data) {
                 if (!map.containsKey(node.start)) map.put(node.start, new HashMap<>());
                 map.get(node.start).put(node.end, node.ratio);
                 if (!map.containsKey(node.end)) map.put(node.end, new HashMap<>());
                 map.get(node.end).put(node.start, 1.00/node.ratio);
             }
             Queue<String> q = new LinkedList<>();
             Queue<Double> val = new LinkedList<>();
             q.offer(start);
             val.offer(1.00);
             Set<String> visited = new HashSet<>();
             while(!q.isEmpty()) {
                 String cur = q.poll();
                 double num = val.poll();
                 if (visited.contains(cur)) continue;
                 visited.add(cur);
                 if (map.containsKey(cur)) {
                     Map<String, Double> next = map.get(cur);
                     for (String key: next.keySet()) {
                         if (!visited.contains(key)) {
                             q.offer(key);
                             if (key.equals(end)) return num*next.get(key);
                             val.offer(num*next.get(key));
                         }
                     }
                 }
             }
             return -1;
         }

         static class Node {
             String start;
             String end;
             double ratio;
             public Node(String s, String e, double r) {
                 this.start = s;
                 this.end = e;
                 this.ratio = r;
             }
         }

    }
}
/*
    public static double determineExchangeRate(String input, List<Node> data){
        int inputLength = input.length();
        String start = input.substring(inputLength-6, inputLength - 3);
        String end = input.substring(inputLength-3);

        if(start.equals(end)){
            return 1.00;
        }

        String temp = input.split("\\|")[0];
        System.out.println("temp is : " + temp);
        String[] inputs = temp.split(";");

        for(int i=0; i < inputs.length ; i++){
            String first = inputs[i].substring(0, 3);
            String second = inputs[i].substring(3, 6);
            double rate = Double.parseDouble(inputs[i].substring(7));
            data.add(new Node(first, second, rate));
        }

        return getRatio(start, end, data);
    }

    public static double getRatio(String start, String end, List<Node> data) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (Node node: data) {
            if (!map.containsKey(node.start)) map.put(node.start, new HashMap<>());
            map.get(node.start).put(node.end, node.ratio);
            if (!map.containsKey(node.end)) map.put(node.end, new HashMap<>());
            map.get(node.end).put(node.start, 1.0/node.ratio);
        }
        Queue<String> q = new LinkedList<>();
        Queue<Double> val = new LinkedList<>();
        q.offer(start);
        val.offer(1.0);
        Set<String> visited = new HashSet<>();
        while(!q.isEmpty()) {
            String cur = q.poll();
            double num = val.poll();
            if (visited.contains(cur)) continue;
            visited.add(cur);
            if (map.containsKey(cur)) {
                Map<String, Double> next = map.get(cur);
                for (String key: next.keySet()) {
                    if (!visited.contains(key)) {
                        q.offer(key);
                        if (key.equals(end)) return num*next.get(key);
                        val.offer(num*next.get(key));
                    }
                }
            }
        }
        return -1;
    }


    static class Node {
        String start;
        String end;
        double ratio;
        public Node(String s, String e, double r) {
            this.start = s;
            this.end = e;
            this.ratio = r;
        }
    }
*/
