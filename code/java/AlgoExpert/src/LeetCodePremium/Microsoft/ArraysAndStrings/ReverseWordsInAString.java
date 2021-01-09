package LeetCodePremium.Microsoft.ArraysAndStrings;

/*
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters.
The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words.
The returned string should only have a single space separating the words.
Do not include any extra spaces.

Constraints:
1 <= s.length <= 10^4
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow up:
Could you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        String result = "";
        for(int i=words.length-1; i>=0 ; i--){
            result += words[i] + " ";
        }
        return result.trim();
    }

    public String reverseWords2(String s) {
        String[] words = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        for(int i=words.length-1; i>=0 ; i--){
            result.append(words[i] + " ");
        }
        return result.toString().trim();
    }

    // Using O(1) space. 
    public String reverseWords3(String s) {
        return "";
    }
}
