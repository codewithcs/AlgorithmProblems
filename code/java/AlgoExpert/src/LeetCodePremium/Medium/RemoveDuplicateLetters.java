package LeetCodePremium.Medium;

import java.util.*;

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

    public String removeDuplicateLetters2(String s) {
        Map<Character, Integer> count = new HashMap<>();
        StringBuilder sb = new StringBuilder(s);

        for(char c: s.toCharArray()){
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        List<String> list = new ArrayList<>();
        recurse(sb, list, count);
        System.out.println("list is : " + list);
        Collections.sort(list);
        return list.get(0);
    }

    public void recurse(StringBuilder sb, List<String> list, Map<Character, Integer> count){
        boolean found = false;
        System.out.println("sb  is : " + sb);

        for(int i=0; i< sb.length(); i++){
            if(count.get(sb.charAt(i)) > 1){
                char charToDelete = sb.charAt(i);
                found = true;

                StringBuilder previous = new StringBuilder(sb); // Important to use the new operator.gi
                sb.deleteCharAt(i);
                count.put(charToDelete, count.get(charToDelete)-1);
                recurse(sb, list, count);
                sb = new StringBuilder(previous);
                count.put(charToDelete, count.get(charToDelete)+1);
            }
        }

        if(!found){
            list.add(sb.toString());
        }

    }
}
