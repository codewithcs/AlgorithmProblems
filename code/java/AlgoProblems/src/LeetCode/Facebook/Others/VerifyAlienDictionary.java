package LeetCode.Facebook.Others;

import java.util.HashMap;
import java.util.Map;

public class VerifyAlienDictionary {

    // Clarifying questions: Duplicates in words: Yes,
    // duplicates in order not possible.
    // Lexicographically basically means sorted alphabetically like a dictionary.

    // Time: O(C), C: Length of words, Can do in O(1) space.
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1) return true;
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0 ; i<order.length(); i++){
            if(!map.containsKey(order.charAt(i))){
                map.put(order.charAt(i), i);
            }
        }

        // Ask the interviewer about whether there can be duplicates in words[].
        for(int i=0 ; i<words.length-1 ; i++){
            String first = words[i];
            String second = words[i+1];
            int j=0;

            if(!first.equals(second) && first.startsWith(second)) return false;

            while(j< Math.min(first.length(), second.length())){
                if(first.charAt(j) != second.charAt(j)){
                    if( map.get(first.charAt(j)) > map.get(second.charAt(j)) ){
                        return false;
                    } else {
                        break;
                    }
                }
                j++ ;
            }
        }

        return true;
    }
}
