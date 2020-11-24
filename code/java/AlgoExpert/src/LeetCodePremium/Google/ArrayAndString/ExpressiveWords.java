package LeetCodePremium.Google.ArrayAndString;

/*
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by
any number of applications of the following extension operation: choose a group consisting
of characters c, and add some number of characters c to the group so
that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get
"hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.
Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".
If S = "helllllooo", then the query word "hello" would be stretchy because of these two
extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy.


Constraints:
0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
 */

import java.util.ArrayList;
import java.util.List;

public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        int count = 0;
        List<Integer> groupStartIndices = new ArrayList<>();

        groupStartIndices.add(0);
        for(int i=1; i < S.length(); i++){
            if(S.charAt(i) != S.charAt(i-1)){
                groupStartIndices.add(i);
            }
        }

        int numberOfGroups = groupStartIndices.size();
        int[] lengthOfGroups = new int[numberOfGroups];
        for(int i=1; i< lengthOfGroups.length ; i++){
            lengthOfGroups[i-1] = groupStartIndices.get(i) - groupStartIndices.get(i-1);
        }

        lengthOfGroups[lengthOfGroups.length-1] = S.length() - groupStartIndices.get(lengthOfGroups.length-1);

        for(int i=0; i<words.length ; i++){
            List<Integer> startIndices = new ArrayList<>();
            String current = words[i];

            startIndices.add(0);
            boolean found = false;

            for(int j=1; j<current.length(); j++){
                if(current.charAt(j) != current.charAt(j-1)){
                    startIndices.add(j);
                }
            }

            if(numberOfGroups != startIndices.size()) continue;

            for(int j=0; j< groupStartIndices.size(); j++){
                if(S.charAt(groupStartIndices.get(j)) != current.charAt(startIndices.get(j)) ) {
                    found = true; break;
                }
            }

            if(found) continue;

            int[] groupLengths = new int[startIndices.size()];

            for(int j=1; j< groupLengths.length ; j++){
                groupLengths[j-1] = startIndices.get(j) - startIndices.get(j-1);
            }

            groupLengths[groupLengths.length-1] = current.length() - startIndices.get(groupLengths.length-1);

            for(int j=0 ; j<lengthOfGroups.length; j++){
                if(lengthOfGroups[j] < groupLengths[j] || (lengthOfGroups[j] > groupLengths[j] && lengthOfGroups[j] < 3) )  {
                    found = true; break;
                }
            }

            if(found) continue;

            count++;
        }

        return count;
    }
}
