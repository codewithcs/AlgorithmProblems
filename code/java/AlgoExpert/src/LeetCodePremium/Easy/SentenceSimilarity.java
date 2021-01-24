package LeetCodePremium.Easy;

import java.util.List;

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
}