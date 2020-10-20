package LeetCodePremium.Amazon.DynamicProgramming;

/*
Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromicSubstring {
    public static String longestPalindromicSubstring(String str) {
        int maxLength = 1;
        int mid = -1; // optimal mid.

        for (int i = 0; i < str.length() - 1; i++) {
            int oddLength = 0;
            if (i > 0) {
                oddLength = findPalindromeLength(str, i, i - 1, i + 1) + 1;
            }
            int evenLength = findPalindromeLength(str, i, i, i + 1);
            int currentLength = Math.max(oddLength, evenLength);
            if (currentLength > maxLength) {
                mid = i;
                maxLength = currentLength;
            }
        }

        boolean isOdd = (maxLength % 2) == 1; // mid is always >= maxLength/ 2
        int maxLeft = isOdd ? mid - maxLength / 2 : mid - maxLength / 2 + 1;
        int maxRight = mid + maxLength / 2;

        if (maxLength == 1) {
            return str.charAt(0) + "";
        }

        return str.substring(maxLeft, maxRight + 1);
    }

    public static int findPalindromeLength(String str, int middle, int left, int right) {
        int length = 0;
        while (left >= 0 && right <= str.length() - 1) {
            if (str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
                length = length + 2;
            } else { // We are going outwards, so if it is a mismatch then we return the current length.
                break;
            }
        }
        return length;
    }

    // Expand along the center.
    public String longestPalindrome(String s) {
      if (s == null || s.length() < 1) return "";

         int start = 0, end = 0;

         for (int i = 0; i < s.length(); i++) {
             int len1 = expandAroundCenter(s, i, i);
             int len2 = expandAroundCenter(s, i, i + 1);

             int len = Math.max(len1, len2);
             if (len > end - start) {
                 start = i - (len - 1) / 2;
                 end = i + len / 2;
             }
         }

         return s.substring(start, end + 1);
 }

 private int expandAroundCenter(String s, int left, int right) {
         int L = left, R = right;
         while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
             L--;
             R++;
         }
         return R - L - 1; //  returning the length of the palindrome.
    }
}
