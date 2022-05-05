package LeetCode.Google.ArrayAndString;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, return the minimum window in s which will
contain all the characters in t. If there is no such window in s that
covers all characters in t, return the empty string "".

Note that If there is such a window, it is guaranteed that there will always
be only one unique minimum window in s.

Constraints:
1 <= s.length, t.length <= 105
s and t consist of English letters.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ab", "b"));
    }

    public static String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        Map<Character, Integer> requiredCounts = new HashMap<>();

        for(int i=0; i<t.length(); i++){
            char current = t.charAt(i);
            requiredCounts.put(current, requiredCounts.getOrDefault(current, 0) + 1);
        }

        Map<Character, Integer> currentMap = new HashMap<>();

        int i=0; int j=0;
        int currentCount = 0;
        int uniqueKeys = requiredCounts.size();

        int start = 0;
        int end = Integer.MAX_VALUE;


        while(i<s.length() && j< s.length()){
            char currentChar = s.charAt(j);

            if(requiredCounts.containsKey(currentChar)){

                currentMap.put(currentChar, currentMap.getOrDefault(currentChar, 0) + 1);

                if(currentMap.get(currentChar) == requiredCounts.get(currentChar)){
                    currentCount++;
                }

                System.out.println("currentCount is : " + currentCount);
                System.out.println("uniqueKeys is : " +  uniqueKeys);
                if(currentCount == uniqueKeys){

                    if(j-i< end-start){
                        end = j;
                        start = i;
                    }

                    if(currentMap.containsKey(s.charAt(i))){
                        currentMap.put(s.charAt(i), currentMap.get(s.charAt(i)) -1 );
                        i++;
                        currentCount--;
                    }

                    while(i<s.length() && !requiredCounts.containsKey(s.charAt(i))){
                        i++;
                    }

                }

            }

            if(currentCount != uniqueKeys)
                j++;

        }


        if(end > s.length()-1){
            return "";
        }

        return s.substring(start, end+1);

    }
}
