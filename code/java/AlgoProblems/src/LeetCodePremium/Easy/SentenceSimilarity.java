package LeetCodePremium.Easy;

import java.util.*;

/*
We can represent a sentence as an array of words, for example, the sentence
"I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].

Given two sentences sentence1 and sentence2 each represented as a string array and given an array of
string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

Return true if sentence1 and sentence2 are similar, or false if they are not similar.

Two sentences are similar if:
They have the same length (i.e. the same number of words)
sentence1[i] and sentence2[i] are similar.

Notice that a word is always similar to itself, also notice that the similarity relation is not transitive.
For example, if the words a and b are similar and the words b and c are similar, a and c are not necessarily similar.
 */
public class SentenceSimilarity {

    // O(np) time and O(1) space.
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length) {
            return false;
        }

        for(int i=0; i< sentence1.length; i++){
            String first = sentence1[i];
            String second = sentence2[i];

            boolean found = false;

            if(!first.equals(second)){
                for(List<String> current: similarPairs){
                    String firstString = current.get(0);
                    String secondString = current.get(1);

                    if(firstString.equals(first) && secondString.equals(second) || firstString.equals(second) && secondString.equals(first)){
                        found = true;
                        break;
                    }
                }
            } else {
                found = true;
            }

            if(!found) {
                return false;
            }
        }

        return true;
    }

    // O(n+p), n is the length of sentence1 and sentence2 and p is the length of similarPairs.
    // O(p) space.
    // For a word, .equals() takes O(w)
    // Here the length of all words are within 20, so it is O(1).
    public boolean areSentencesSimilar2(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length ){
            return false;
        }

        Set<String> set = new HashSet<>();
        for(List<String> current: similarPairs){
            set.add(current.get(0) + "#" + current.get(1));
        }

        for(int i=0; i< sentence1.length; i++){
            if(!sentence1[i].equals(sentence2[i]) || !set.contains(sentence1[i] + "#" + sentence2[i]) ||
            !set.contains(sentence2[i] + "#" + sentence1[i])){
                return false;
            }
        }

        return true;
    }

    // Using HashMap, O(n+p), O(p) space.
    public boolean areSentencesSimilar3(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (List<String> pair: similarPairs) {
            String key = pair.get(0);
            String value = pair.get(1);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(value);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];

            if (w1.equals(w2)) {
                continue;
            }
            boolean b1 = map.containsKey(w1) && map.get(w1).contains(w2);
            if (b1) {
                continue;
            }
            boolean b2 = map.containsKey(w2) && map.get(w2).contains(w1);
            if (b2) {
                continue;
            }

            return false;
        }
        return true;
    }
}