package LeetCode.Medium;

import java.util.*;

/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar,
then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar
is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences
words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words.
So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20]
 */

// Similarity relation being transitive and symmetric hints that we can use an undirected graph.
public class SentenceSimilarity2 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length){
            return false;
        }

        Map<String, Set<String>> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for(List<String> current: pairs){
            String first = current.get(0);
            String second = current.get(1);

            if(!map.containsKey(first)) {
                map.put(first, new HashSet<>());
            }

            map.get(first).add(second);

            if(!map.containsKey(second)) {
                map.put(second, new HashSet<>());
            }

            map.get(second).add(first);
        }

        for(int i=0; i< words1.length ; i++){
            String start = words1[i];
            String end = words2[i];

            if(start.equals(end)){ // Need to check this here as well as it can happen that these words are not in the hashmap which would lead to return result result of false.
                continue;
            }

            if(!helper(start, end, map, visited)){
                return false;
            }

            visited = new HashSet<>();
        }

        return true;
    }

    public boolean helper(String start, String end, Map<String, Set<String>> map, Set<String> visited){
        if(visited.contains(start) || !map.containsKey(start)){ // Cycle or "start" is not present in the hashmap.
            return false;
        }

        if(start.equals(end)) { // This will also form the base case of recursion when there is a path from start to end.
            return true;
        }

        visited.add(start);

        boolean found = false;
        for(String neighbor: map.get(start)){
            if(!visited.contains(neighbor)){
                found = found || helper(neighbor, end, map, visited);
            }
        }

        return found;
    }
}
