package LeetCodePremium.Amazon.ArrayAndStrings;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together.
You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.
 */

public class GroupAnagrams {
    // Important technique
    // O(NW) time and O(NW) space, W is the length of strs, N is the length of longest string.
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();

        Map<String, List> ans = new HashMap<String, List>();

        int[] count = new int[26];

        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++; // imp technique.

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }

        return new ArrayList(ans.values()); // .values() returns Collection for values of the hashmap, can also use the entry set as well.
    }
}
