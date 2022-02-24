package LeetCodePremium.Google.ArrayAndString;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    // Naive approach: Generate all substrings.

    public int lengthOfLongestSubstring(String s) {
        if(s.length()== 0){
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int i=0; int j=0; int start = 0; int end =0;

        while(j < s.length()){
            char current = s.charAt(j);

            if(!map.containsKey(current)){
                map.put(current, 1);

                if(j-i > end-start){
                    end = j;
                    start = i;
                }

            } else {
                while(map.containsKey(current)){
                    map.remove(s.charAt(i));
                    i++;
                }
                map.put(current, 1);
            }

            j++;
        }

        return end-start+1;
    }

    public int lengthOfLongestSubstring2(String s) {
        if(s.length()== 0){
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int i=0; int j=0; int start = 0; int end =0;

        while(j < s.length()){
            char current = s.charAt(j);

            if(!map.containsKey(current)){
                map.put(current, 1);

                if(j-i > end-start){
                    end = j;
                    start = i;
                }
                 j++;
            } else {
                while(map.containsKey(current)){
                    map.remove(s.charAt(i));
                    i++;
                }
            }

        }

        return end-start+1;
    }

    // Optimization to the while loop on Line 64.
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
