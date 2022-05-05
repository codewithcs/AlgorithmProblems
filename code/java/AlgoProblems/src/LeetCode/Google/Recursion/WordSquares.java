package LeetCode.Google.Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read
the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square
because each word reads the same both horizontally and vertically.

Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
 */

// IMP Note: Using ArrayList instead of LinkedList leads to Ouput Limit Exceeded even though adding and removing will be O(1) in both.
// Why ? Investigate this.
// See the time complexity again.
public class WordSquares {
    int N = 0;
    String[] words = null;
    HashMap<String, List<String>> prefixHashTable = null;

    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        this.N = words[0].length();

        List<List<String>> results = new ArrayList<List<String>>();
        this.buildPrefixHashTable(words);

        for (String word : words) {
            LinkedList<String> wordSquares = new LinkedList<String>();
            wordSquares.addLast(word);
            this.backtracking(1, wordSquares, results);
        }
        return results;
    }

    // We go into a "step" only when it is possible.
    protected void backtracking(int step, LinkedList<String> wordSquares,
                                List<List<String>> results) {
        if (step == N) {
            results.add((List<String>) wordSquares.clone());
            return;
        }

        StringBuilder prefix = new StringBuilder();
        for (String word : wordSquares) {
            prefix.append(word.charAt(step));
        }

        for (String candidate : this.getWordsWithPrefix(prefix.toString())) {
            wordSquares.addLast(candidate);
            this.backtracking(step + 1, wordSquares, results);
            wordSquares.removeLast();
        }
    }

    protected void buildPrefixHashTable(String[] words) {
        this.prefixHashTable = new HashMap<String, List<String>>();

        for (String word : words) {
            for (int i = 1; i < this.N; ++i) {
                String prefix = word.substring(0, i);
                List<String> wordList = this.prefixHashTable.get(prefix);
                if (wordList == null) {
                    wordList = new ArrayList<String>();
                    wordList.add(word);
                    this.prefixHashTable.put(prefix, wordList);
                } else {
                    wordList.add(word);
                }
            }
        }
    }

    protected List<String> getWordsWithPrefix(String prefix) {
        List<String> wordList = this.prefixHashTable.get(prefix);
        return (wordList != null ? wordList : new ArrayList<String>());
        /// if the prefix is not contained as a key, we should either call containsKey() method or
        // return a new ArrayList to avoid null in the for each loop above.
    }

    // Approach 2: Backtracking Using Trie
}
