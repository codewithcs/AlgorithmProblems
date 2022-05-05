package LeetCode.Facebook.ArrayAndString;

/*
Given two strings s and t, return true if
they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart
from a string t if you can:
Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a
different character to get t.

Constraints:
0 <= s.length <= 104
0 <= t.length <= 104
s and t consist of lower-case letters, upper-case letters and/or digits.
 */
public class OneEditDistance {

    // Best Case: O(1) time, Worst Case: O(n) time and O(n) space because Strings are immutable in Java.
    public boolean isOneEditDistance(String s, String t) {
        int l1 = s.length();
        int l2 = t.length();

        if (Math.abs(l1 - l2) > 1) {
            // more than 1 edit distance apart
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < l1 && j < l2 && s.charAt(i) == t.charAt(j)) {
            i++;
            j++;
        }

        if (i == l1 && j == l2) {
            return false;
        }

        if (l1 > l2) {
            // deletion is only possible
            return s.substring(i + 1).equals(t.substring(j));
        } else if (l1 < l2) {
            // insertion is only possible
            return s.substring(i).equals(t.substring(j + 1));
        } else {
            // replacing is only possible
            return s.substring(i + 1).equals(t.substring(j + 1));
        }
    }
}