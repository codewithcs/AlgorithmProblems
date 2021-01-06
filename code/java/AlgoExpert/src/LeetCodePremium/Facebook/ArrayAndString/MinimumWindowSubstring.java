package LeetCodePremium.Facebook.ArrayAndString;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, return the minimum window in s which will contain all the characters in t.
If there is no such window in s that covers all characters in t, return the empty string "".

Note that If there is such a window, it is guaranteed that there will always be
only one unique minimum window in s.
s and t can have repeating characters.
Constraints:

1 <= s.length, t.length <= 105
s and t consist of English letters.

Follow up: Could you find an algorithm that runs in O(n) time?

Cover similar questions.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) {
            return "";
        }

        int left = 0; int right = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        for(char current: t.toCharArray()){
            map1.put(current, map1.getOrDefault(current, 0) + 1);
        }

        int counts = map1.size();
        int currentCount = 0;
        Map<Character, Integer> map2 = new HashMap<>();

        int leftMin = 0; int rightMin = Integer.MAX_VALUE;
        boolean solutionExists = false;

        while(right < s.length()){
            char current = s.charAt(right);

            if(!map1.containsKey(current)){
                right++;
                continue;
            }

            map2.put(current, map2.getOrDefault(current, 0) + 1); // We only want to add keys in map2 which exist in map1.

            if(map2.get(current).equals(map1.get(current))){ // use .equals() here and not ==
                currentCount++;
            }

            while(currentCount == counts && left <= right ){
                if(rightMin-leftMin > right-left){
                    rightMin = right;
                    leftMin = left;
                    solutionExists = true;
                }
                if(map1.containsKey(s.charAt(left))){ // Try to decrease the sliding window.
                    if(map1.get(s.charAt(left)).equals(map2.get(s.charAt(left)))){ // Imp check.
                        currentCount--;
                    }
                    map2.put(s.charAt(left), map2.get(s.charAt(left))-1);
                }
                left++;
            }

            right++;
        }

        return solutionExists ? s.substring(leftMin, rightMin+1) : "";
    }
}
