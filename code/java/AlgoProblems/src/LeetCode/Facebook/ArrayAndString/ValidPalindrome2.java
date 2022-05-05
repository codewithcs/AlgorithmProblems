package LeetCode.Facebook.ArrayAndString;

/*
Given a non-empty string s, you may delete at most one character.
Judge whether you can make it a palindrome.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome2 {

    // Brute Force: For each index i in the given string, let's remove that character
    // then check if the resulting string is a palindrome.
    // O(n^2) time and O(n) space.
    public boolean isPalindrome(CharSequence s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            if (isPalindrome(sb)) return true;
            sb.insert(i, c);
        }
        return isPalindrome(s);
    }

    // O(N) time. Each of two checks of whether some substring is a palindrome is O(N).
    // O(1) space.
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) {
                return false;
            }
        }
        return true;
    }
    // If the given string is a palindrome, then there won't be a mismatch.
    public boolean validPalindrome2(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
}
