package LeetCode.Medium.Strings;

/*
Given two strings text1 and text2, return the length of their
longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original
string with some characters (can be none) deleted without changing the
relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence
that is common to both strings.

Constraints:
1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class longest_common_subsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        return recurse(text1, text2, 0);
    }

    // Brute Force Approach.
    public int recurse(String text1, String text2, int maxLength) {
        int max = 0;

        if(text1.length() == 0 || text2.length() == 0){
            return maxLength;
        }

        if(text1.charAt(0) == text2.charAt(0)){
            max = recurse(text1.substring(1), text2.substring(1), maxLength+1);
        } else {
            int length1 = recurse(text1.substring(1), text2, maxLength);
            int length2 = recurse(text1, text2.substring(1), maxLength);

            max = Math.max(maxLength, Math.max(length1, length2));
        }

        return max;
    }

    public int recurse2(String text1, String text2, int maxLength) {
        if(text1.length() == 0 || text2.length() == 0){
            return maxLength;
        }

        if(text1.charAt(0) == text2.charAt(0)){
            maxLength = recurse(text1.substring(1), text2.substring(1), maxLength+1);
        } else {
            int length1 = recurse(text1.substring(1), text2, maxLength);
            int length2 = recurse(text1, text2.substring(1), maxLength);

            maxLength = Math.max(maxLength, Math.max(length1, length2));
        }

        return maxLength;
    }



}
