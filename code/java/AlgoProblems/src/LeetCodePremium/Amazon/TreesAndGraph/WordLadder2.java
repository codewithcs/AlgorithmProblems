package LeetCodePremium.Amazon.TreesAndGraph;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
 */

import java.util.*;

public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) {
            return res;
        }
        Map<String, List<String>> map = getChildren(beginWord, endWord, dict);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        findLadders(beginWord, endWord, map, res, path);
        return res;

    }

    public void findLadders(String beginWord, String endWord, Map<String, List<String>> map, List<List<String>> res, List<String> path) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String next : map.get(beginWord)) {
            path.add(next);
            findLadders(next, endWord, map, res, path);
            path.remove(path.size() - 1);
        }
    }

    public Map<String, List<String>> getChildren(String beginWord, String endWord, Set<String> dict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> start = new HashSet<>();
        start.add(beginWord);

        Set<String> end = new HashSet<>();
        Set<String> visited = new HashSet<>();
        end.add(endWord);
        boolean found = false;
        boolean isBackward = false;

        while (!start.isEmpty() && !found) {

            if (start.size() > end.size()) {
                Set<String> tem = start;
                start = end;
                end = tem;
                isBackward = !isBackward;
            }

            Set<String> set = new HashSet<>();
            for (String cur : start) {
                visited.add(cur);
                for (String next : getNext(cur, dict)) {
                    if (visited.contains(next) || start.contains(next)) {
                        continue;
                    }
                    if (end.contains(next)) {
                        found = true;
                    }
                    set.add(next);
                    String parent = isBackward ? next : cur;
                    String child = isBackward ? cur : next;
                    if (!map.containsKey(parent)) {
                        map.put(parent, new ArrayList<>());
                    }
                    map.get(parent).add(child);

                }
            }

            start = set;
        }
        return map;
    }

    private List<String> getNext(String cur, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) {
                    continue;
                }
                chars[i] = c;
                String next = new String(chars);
                if (dict.contains(next)) {
                    res.add(next);
                }
            }
            chars[i] = old;
        }
        return res;
    }



    // Solution 2: Using BFS and DFS.
    public List<List<String>> findLadders2(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();

        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>(); // Neighbors for every node
        HashMap<String, Integer> distance = new HashMap<String, Integer>(); // Distance of every node from the start node
        ArrayList<String> solution = new ArrayList<String>();

        dict.add(start); // Important Step.
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict) {
            nodeNeighbors.put(str, new ArrayList<String>());
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;

            for (int i = 0; i < count; i++) {  // Iterate over the current level: The elements present in the queue before an iteration are the elements of the current level.
                String cur = queue.poll();
                int curDistance = distance.get(cur);

                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);

                    if (!distance.containsKey(neighbor)) { // Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor)) { // Found the shortest path
                            foundEnd = true;
                        }
                        else {
                            queue.offer(neighbor);
                        }
                    }
                }

            }

            if (foundEnd) {
                break;
            }
        }
    }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) { // nodeNeighbors list stores all possible neighbors of the current node that are present in the "dict" set.
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }

        solution.remove(solution.size() - 1); // Backtracking Step.
    }

}
