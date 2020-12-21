package LeetCodePremium.Facebook.Others;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

Constraints:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

Same as: Find All Anagrams in a String problem.
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s1.length()){
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for(char c: s1.toCharArray()){
            if(map1.containsKey(c)){
                map1.put(c, map1.get(c) + 1);
            } else {
                map1.put(c, 1);
            }
        }

        for(int right = 0; right < s2.length(); right++){
            char current = s2.charAt(right);
            if(map2.containsKey(current)){
                map2.put(current, map2.get(current) + 1);
            } else {
                map2.put(current, 1);
            }

            if(right >= s1.length()){
                char c = s2.charAt(right-s1.length());
                if(map2.containsKey(c)){
                    if(map2.get(c) == 1){
                        map2.remove(c); // this will ensure that the map2 keys can be same as keys in map1.
                    } else{
                        map2.put(c, map2.get(c)-1 );
                    }
                }
            }

            if(map1.equals(map2)) return true;
        }

        return false;
    }
}
