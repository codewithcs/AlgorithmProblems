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
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
    
    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26]; // since the string contains lowercase characters.
        StringBuilder sb = new StringBuilder(s);

        for(int i=0; i< s.length(); i++){
            count[s.charAt(i)- 'a']++;
        }

        StringBuilder result = new StringBuilder();
        Set<Character> visited = new HashSet<>();

        for(int i=0; i< sb.length(); i++){
            if(result.length() == 0 || sb.charAt(i) > result.charAt(result.length()-1) && !visited.contains(sb.charAt(i))){
                result.append(sb.charAt(i));
                visited.add(sb.charAt(i));
            } else {
                int j = result.length()-1;

                while(j>=0 && count[sb.charAt(j) - 'a'] > 1){
                    char c = sb.charAt(j);
                    result.deleteCharAt(j);
                    visited.remove(c); /// return a boolean
                    count[c-'a']--;
                    j--;
                }

                if(!visited.contains(sb.charAt(i))){
                    result.append(sb.charAt(i));
                    visited.add(sb.charAt(i));
                }
            }
        }

        return result.toString();
    }

    // Output Limit Exceeded for "thesqtitxyetpxloeevdeqifkz"
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

        for(int i=0; i< sb.length(); i++){
            if(count.get(sb.charAt(i)) > 1){
                char charToDelete = sb.charAt(i);
                found = true;

                StringBuilder previous = new StringBuilder(sb); // Important to use the new operator.
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
