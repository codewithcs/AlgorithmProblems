package LeetCode.Microsoft.ArraysAndStrings;

import java.util.ArrayDeque;
import java.util.Deque;

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
    // O(n) time and space.
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
        if(s.isEmpty()){
            return "";
        }

        StringBuilder current = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for(int i=s.length()-1 ; i>=0 ; i--){
            char currentChar = s.charAt(i);

            if(currentChar == ' '){
                if(current.length() == 0 ){
                    continue;
                }
                result.append(current.reverse() + " ");
                current = new StringBuilder();
            } else {
                current.append(currentChar);
            }

        }

        result.append(current.reverse());
        return result.toString().trim();
    }


    // Using a Dequeue.
    public String reverseWords4(String s) {
        int left = 0, right = s.length() - 1;

        // Remove leading spaces
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // Remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        // Push word by word in front of deque
        while (left <= right) {
            char c = s.charAt(left);

            if ((word.length() != 0) && (c == ' ')) {
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
}
