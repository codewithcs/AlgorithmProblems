package LeetCode.Google.ArrayAndString;

/*
Given a string s , find the length of the longest substring t
that contains at most 2 distinct characters.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaccc"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;
        int start = 0;
        int end = 0;

        while (j < s.length()) {
            char current = s.charAt(j);

            while (!map.containsKey(current) && map.size() == 2 && i < s.length()) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
                i++;
            }

            current = s.charAt(j);
            map.put(current, map.getOrDefault(current, 0) + 1);
            if (map.size() <= 2) {
                if (j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
            j++;
        }

        return end - start + 1;
    }

    // Minor Optimization: Added an if on line 69 and removed check for i
    // as i<=j always. Check on j is sufficient.
    // We increment i only inside of while loop
    public static int lengthOfLongestSubstringTwoDistinct2(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;
        int start = 0;
        int end = 0;

        while (j < s.length()) {
            char current = s.charAt(j);

            if(!map.containsKey(current)) {
                while (map.size() == 2) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                    if (map.get(s.charAt(i)) == 0) {
                        map.remove(s.charAt(i));
                    }
                    i++;
                }
            }

            map.put(current, map.getOrDefault(current, 0) + 1);
            if (map.size() <= 2) {
                if (j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
            j++;
        }

        return end - start + 1;
    }
}























