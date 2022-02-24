package LeetCodePremium.Google.SortingAndSearching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    // Approach 1:
    public boolean isAnagram(String s, String t) {

        char c1[] = s.toCharArray();
        Arrays.sort(c1);

        char c2[] = t.toCharArray();
        Arrays.sort(c2);

        return String.valueOf(c1).equals(String.valueOf(c2));
    }

    public boolean isAnagram2(String s, String t){
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 =  new HashMap<>();

        for(char c: s.toCharArray()){
            if(!map1.containsKey(c)){
                map1.put(c, 1);
            } else {
                map1.put(c, map1.get(c) +1 );
            }
        }

        for(char c: t.toCharArray()){
            if(!map2.containsKey(c)){
                map2.put(c, 1);
            } else {
                map2.put(c, map2.get(c) +1 );
            }
        }

        return map1.equals(map2);
    }

    // Approach 3:
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    // Approach 4:
    public boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
