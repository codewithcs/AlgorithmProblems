package LeetCode.Google.ArrayAndString;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtmostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() == 0 || k ==0 ) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int i = 0; int j = 0; int start = 0; int end = 0;

        while (j < s.length()) {
            char current = s.charAt(j);

            while (!map.containsKey(current) && map.size() == k && i < s.length()) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
                i++;
            }

            current = s.charAt(j);
            map.put(current, map.getOrDefault(current, 0) + 1);
            if (map.size() <= k) {
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
