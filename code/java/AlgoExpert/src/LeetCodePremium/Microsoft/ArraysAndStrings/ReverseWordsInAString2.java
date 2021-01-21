package LeetCodePremium.Microsoft.ArraysAndStrings;

/*
Given an input string , reverse the string word by word.

Note:
A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.

Follow up: Could you do it in-place without allocating extra space?
 */

// O(n) time and O(1) space.
public class ReverseWordsInAString2 {
    public void reverseWords(char[] s) {
        int i = 0;
        int j = s.length-1;

        // Reverse the whole string.
        while(i< j){
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }

        // Reverse each word.
        i=0 ; j=0 ;
        while(i < s.length){
            while(j < s.length && s[j] != ' '){
                j++;
            }
            int endPosition = j-1;
            while(i< endPosition){
                char temp = s[i];
                s[i++] = s[endPosition];
                s[endPosition--] = temp;
            }

            i = j+1;
            j= i;
        }
    }
}
