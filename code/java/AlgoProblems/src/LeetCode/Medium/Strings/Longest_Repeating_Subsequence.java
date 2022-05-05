package LeetCode.Medium.Strings;

/*
Given a string, find the length of the longest repeating subsequence such
that the two subsequences don’t have same string character at the same position,
i.e., any i’th character in the two subsequences shouldn’t
have the same index in the original string.

Output: 0
There is no repeating subsequence

Input: str = "aab"
Output: 1
The two subsequence are 'a'(first) and 'a'(second).
Note that 'b' cannot be considered as part of subsequence
as it would be at same index in both.

Input: str = "aabb"
Output: 2

Input: str = "axxxy"
Output: 2

Idea:
This problem is just the modification of Longest Common Subsequence problem.
The idea is to find the LCS(str, str)where str is the input string with the
restriction that when both the characters are same,
they shouldn’t be on the same index in the two strings.

Below is the implementation of the idea.
 */

public class Longest_Repeating_Subsequence {
    public int findLongestRepeatingSubSeq(String str){
        return 0;
    }
}
