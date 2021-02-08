package LeetCodePremium.Medium;

import javafx.util.Pair;

/*
Given a string s, a k duplicate removal consists of choosing k adjacent and
equal letters from s and removing them causing the left and the right side
of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on
s until we no longer can.

Return the final string after all such
duplicate removals have been made.

It is guaranteed that the answer is unique.

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/
public class RemoveAllAdjacentDuplicatesInString2 {

    // Check All substrings of length k ?
    public String removeDuplicates(String s, int k) {
        if(k > s.length()){
            return "";
        }

        StringBuilder sb = new StringBuilder(s);
        boolean foundAtLeastOne = false;

        do{
            foundAtLeastOne = false;

            for(int i=0; i< s.length()-k+1; i++) {
                boolean found = true;
                char current = s.charAt(i);
                for(int j = i+1; j<=i+k-1 ; j++){
                    if(current != sb.charAt(j)){
                        found = false;
                        break;
                    }
                }

                if(found){
                    sb = new StringBuilder(sb.substring(0, i) + sb.substring(i+k));
                    foundAtLeastOne = true;
                }
            }
        } while(foundAtLeastOne);

        return sb.toString();
    }
}
