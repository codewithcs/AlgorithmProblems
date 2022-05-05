package practice_Amazon;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together.
You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.


Clarifying questions to ask to remove vagueness:
All strings of same length ?
All lowercase characters? Any numbers or just english letters?
Min length of the strs array ?
What to return if length of strs[] is 0.

Thought Process:
Anagrams have the same set of characters.
2 anagrams will have the same set of counts for each character.
We Might need a hash table to store character counts with characters as the key and count as the value.
Create a common key for group of anagrams. sorted anagrams gives the same string, use that word as the key.


Another technique to generate a key:
Another technique could be to generate a same key for anagram groups by making use of their count.
A set of anagrams will be the value for this key in the hashmap.
 */

public class GroupAnagrams {

    // O(WNlogN) time and O(WN) space, w: number of words and n is the length of the longest word.
    public static List<List<String>> groupAnagramsMethod2(List<String> strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        for(String word: strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            if(anagrams.containsKey(sortedWord)) {
                anagrams.get(sortedWord).add(word) ;
            } else {
                anagrams.put(sortedWord, new ArrayList<String>(Arrays.asList(word)) );
            }
        }

        List<List<String>> output = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : anagrams.entrySet()) {
            output.add(entry.getValue()) ;
        }

        return output;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();

        if(strs.length == 0) return list;
        Map<String, List<String>> map = new HashMap<>();

        for(int i=0; i< strs.length; i++){
            String word = strs[i];
            int[] count = new int[26];

            for(char letter: word.toCharArray()){
                count[letter-'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int j=0; j< 26; j++){
                sb.append("#");
                sb.append(count[j]);
            }

            if(!map.containsKey(sb.toString())){
                map.put(sb.toString(), new ArrayList<>());
            }

            map.get(sb.toString()).add(word);
        }

        for(String s : map.keySet()){
            list.add(map.get(s));
        }

        return list;
    }


    // Brute Force
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> answer = new ArrayList<>();
        boolean[] isPresentInAGroup = new boolean[strs.length];


        for(int i=0; i< strs.length; i++){
            String word = strs[i];

            List<String> group = new ArrayList<>();

            if(isPresentInAGroup[i]){
                continue;
            }

            group.add(word);
            isPresentInAGroup[i] = true;

            Map<Character, Integer> map = new HashMap<>();

            for(char c: word.toCharArray()){
                if(map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }

            for(int j=i+1; j< strs.length ; j++){
                if(isPresentInAGroup[j]) continue;

                String newWord = strs[j];
                Map<Character, Integer> newWordMap = new HashMap<>();

                for(char c: newWord.toCharArray()){
                    if(newWordMap.containsKey(c)){
                        newWordMap.put(c, newWordMap.get(c) + 1);
                    } else {
                        newWordMap.put(c, 1);
                    }
                }

                if(newWordMap.equals(map)){
                    group.add(newWord);
                    isPresentInAGroup[j] = true;
                }

            }

            answer.add(group);
        }


        return answer;
    }
}
