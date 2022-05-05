package LeetCode.Microsoft.ArraysAndStrings;

/*
A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string.
For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 */

import java.util.HashSet;
import java.util.Set;

// Special Case: Multiple characters which have frequency as 1.
public class MinDeletionsToMakeCharacterFrequencyUnique {
    public int minDeletions(String s) {
        int[] count = new int[26];
        for(int i=0; i< s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }

        Set<Integer> counts = new HashSet<>();
        // Counts have to be > 0 in the HashSet.

        int numDeletions = 0;

        for(int i=0 ; i < 26 ; i++){
            if(count[i] == 0){
                continue;
            }

            if(!counts.contains(count[i])){
                counts.add(count[i]);
            } else {
                int newValue = count[i];

                while(counts.contains(newValue)){
                    newValue--;
                    numDeletions++;
                    if(newValue == 0) { // If newValue becomes 0, then it means that this character is permanently deleted.
                        break;
                    }
                }

                if(newValue > 0 ){
                    counts.add(newValue);
                }
            }

        }

        return numDeletions;
    }
}
