package LeetCodePremium.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Given a string s, remove duplicate letters so that every
letter appears once and only once.
You must make sure your
result is the smallest in lexicographical
order among all possible results.

Constraints:
1 <= s.length <= 10^4
s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> count = new HashMap<>();
        StringBuilder sb = new StringBuilder(s);

        for(char c: s.toCharArray()){
            count.put(c, count.getOrDefault(c, 0) + 1);
        }


        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()){
            if(count.get(c) == 1 ){

            } else {

            }
        }


        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }

        return result.toString();
    }
}
