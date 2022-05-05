package LeetCode.Amazon.ArrayAndStrings;

/*
Given a string S and a string T, find the minimum window in S
which will contain all the characters in T in complexity O(n).

If there is no such window in S that covers all
characters in T, return the empty string "".

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

If there is such window, you are guaranteed that there will always be only
one unique minimum window in S.

 */

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println("Minimum window string is : " + minWindow("ADOBECODEBANC","ABC"));
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> smaller = new HashMap<>();
        Map<Character, Integer> larger = new HashMap<>();

        for(char c: t.toCharArray()){
            if(smaller.containsKey(c)){
                smaller.put(c, smaller.get(c) + 1);
            } else {
                smaller.put(c, 1);
            }
        }

        int count = smaller.keySet().size();
        System.out.println("count is : " + count);

        int L = 0; int R =0;
        int numUniqueCharsDone  = 0;
        String finalString = ""; Integer minDiff = Integer.MAX_VALUE;

        int leftMin = -1; int rightMin =-1;

        while(  R< s.length() ){
            char c = s.charAt(R);
            if(smaller.containsKey(c)){
                if(larger.containsKey(c)){
                    larger.put(c, larger.get(c) + 1);
                } else {
                    larger.put(c, 1);
                }

                if(larger.get(c) == smaller.get(c)){
                    numUniqueCharsDone++;
                }

                while( numUniqueCharsDone == count && L<= R){ // Now increment L.
                    if(R-L< minDiff){
                        leftMin = L; rightMin = R;
                        minDiff = R-L;
                    }

                    if(smaller.containsKey(s.charAt(L))){
                        if(smaller.get(s.charAt(L)) == larger.get(s.charAt(L))) { // larger can have a greater count than smaller, then we don't decrement.
                            numUniqueCharsDone--;
                        }
                        larger.put(s.charAt(L), larger.get(s.charAt(L)) - 1 ); // decrement it after.
                    }

                    L++;
                }

            }

            R++;
        }

        if(minDiff.equals(Integer.MAX_VALUE)){
            return "";
        }

        return finalString = s.substring(leftMin, rightMin+1);
    }
}
