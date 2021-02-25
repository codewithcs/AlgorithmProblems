package AlgoExpert_Medium;

import java.util.*;

/*
Given a string S, check if the letters can be rearranged so that two
characters that are adjacent to each other are not the same.

If possible, output any possible result.
If not possible, return the empty string.

Note:
S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {

    // O(n) time and O(1) space since there are only 26 characters.
    // not consider the size of output char[] in the space.
    public String reorganizeString(String S) {
        int[] count = new int[26];
        for(char c: S.toCharArray()){
            count[c-'a']++;
        }

        int max = 0; char maxCharacter = '0';

        for(int i=0; i< count.length; i++){
            if(count[i] > max){
                max = count[i];
                maxCharacter = (char)(i + 'a');
            }
        }

        if(max > (S.length() + 1)/2 ) return "";

        int index = 0;
        char[] result = new char[S.length()];

        while(count[maxCharacter - 'a'] > 0){
            result[index] = maxCharacter;
            index += 2;
            count[maxCharacter-'a']--;
        }

        for(int i=0; i< count.length; i++){ // iterate through all remaining characters.
            while(count[i] > 0){

                if(index >= result.length){
                    index = 1;
                }

                result[index] = (char)(i + 'a');
                count[i]--;
                index += 2;
            }
        }

        return String.valueOf(result);
    }
}
