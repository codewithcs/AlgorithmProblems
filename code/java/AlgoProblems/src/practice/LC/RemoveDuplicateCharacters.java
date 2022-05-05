package practice.LC;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateCharacters {

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for(int i=0; i< s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        Set<Character> visited = new HashSet<>();

        for(int i=0; i< s.length() ; i++){

            // Prevent Duplicate characters.
            if(visited.contains(s.charAt(i))){
                count[s.charAt(i)-'a']--;
                continue;
            }

            // Deletion Logic.
            int j = result.length()-1;
            if(result.length() != 0 && s.charAt(i) <= result.charAt(j)){
                while(j>=0 && result.charAt(j) > s.charAt(i) && count[result.charAt(j)-'a'] >= 1){
                    char c = result.charAt(j);
                    result.deleteCharAt(j);
                    visited.remove(c);
                    j--;
                }
            }

            result.append(s.charAt(i));
            visited.add(s.charAt(i));

            // s.charAt(i) characters remaining in the string s.
            count[s.charAt(i) - 'a']--;
        }


        return result.toString();
    }
}
