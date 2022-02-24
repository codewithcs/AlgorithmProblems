package LeetCodePremium.Amazon.ArrayAndStrings;

/*
Given a string, find the first non-repeating character in it and return its index.
If it doesn't exist, return -1.

You may assume the string contains only lowercase English letters.
 */

import java.util.*;

public class FirstUniqueCharacterInAString {

    // O(n) time and O(n) space. Can be O(1) space if we don't create character array.
    public int firstUniqChar(String s) {
        Map<Character, List<Integer>> map = new LinkedHashMap<>();

        char[] charArray = s.toCharArray(); // no need to do this.

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (map.containsKey(c)) {
                map.get(c).set(1, map.get(c).get(1) + 1); // ++ wont work here.
            } else {
                map.put(c, new ArrayList<Integer>(Arrays.asList(i, 1))); // use Integer instead of int.
            }
        }

        int index = -1;
        for (List<Integer> list : map.values()) {
            if (list.get(1) == 1) {
                index = list.get(0);
                break;
            }
        }

        return index;
    }


    // Approach 2; O(n) time and O(1) space.
    public int firstUniqChar2(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();

        // build hash map : character and how often it appears
        // No need to convert to a character array.
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) { // ensure ordering using this. We don't iterate over HashMap as the order in a hash map is not maintained.
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}