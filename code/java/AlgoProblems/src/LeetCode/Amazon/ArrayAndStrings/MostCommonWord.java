package LeetCode.Amazon.ArrayAndStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Easy 
public class MostCommonWord {
/*
Punctuation symbols: !?',;.
paragraph is not case sensitive and the answer is in lowercase.
 */

        public String mostCommonWord(String paragraph, String[] banned) {
            Set<String> banned_words = new HashSet<>() ;
            Map<String, Integer> valid_word_counts = new HashMap<>();

            for(String word: banned){
                banned_words.add(word);
            }

            String[] words = paragraph.toLowerCase().split("\\W+");

            for(String word: words){
                if(!banned_words.contains(word)){
                    valid_word_counts.put(word, valid_word_counts.getOrDefault(word, 0) + 1);
                }
            }

            int max = 0;
            String mostCommonWord = "";

            for(String key: valid_word_counts.keySet()){
                if( valid_word_counts.get(key) > max ){
                    max = valid_word_counts.get(key);
                    mostCommonWord = key;
                }
            }

            return mostCommonWord;
    }
}
