package LeetCode.Medium.Strings;

/*
Given a string s, return the longest
palindromic substring in s.

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
 */

// O(n^4) time complexity. 2 loops, 1 check and substring method is O(n).
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String longestPalindrome = "" + s.charAt(0);
        int maxLength =1;

        int n = s.length();
        for(int i=0; i< n-1 ; i++){
            for(int j=i+1; j<n; j++ ){
                int length = j-i+1;
                String current = s.substring(i, j+1);

                if(checkPalindrome(current)){
                    if(current.length() > maxLength){
                        longestPalindrome = current;
                        maxLength = length;
                    }
                }
            }
        }

        return longestPalindrome;
    }

    // O(n)
    public boolean checkPalindrome(String s){
        int start = 0;
        int end = s.length()-1;

        while(start < end){
            if(s.charAt(start) != s.charAt(end)) return false ;
            start++; end--;
        }

        return true;
    }

    public String longestPalindrome2(String s) {
        int length = s.length();
        String longestPalindrome = "" + s.charAt(0);
        int maxLength = 1;

        boolean[] cache = new boolean[length+1];
        cache[1] = true;
        int n = s.length();
        int currentLength = 2;

        // Trying some optimization.
        for(int i=0; i< n-1 ; i++){
            for(int j=currentLength; j<=n; j++ ){

                System.out.println("j is : " + j);
                if(cache[j]) continue;

                if(i+j > n) break;

                String current = s.substring(i, i+j);
                System.out.println("current is : " + current);

                if(checkPalindrome(current)){
                    if(j > maxLength){
                        longestPalindrome = current;
                        maxLength = j;
                        currentLength = maxLength;
                        cache[j] = true;
                    }
                }

            }
        }

        return longestPalindrome;
    }

    // O(n^2) time and O(n) space complexity.
    public String longestPalindrome3(String s) {
        String longestPalindrome = "";
        int n = s.length();
        int maxLength = 0;

        for(int i=0; i<= n-1 ; i++){
            String oddPalindrome = getPalindrome(s, i, i);
            String evenPalindrome = getPalindrome(s, i, i+1);

            int oddLength = oddPalindrome.length();
            int evenLength = evenPalindrome.length();

            if(oddLength > maxLength || evenLength > maxLength){
                if(oddLength > evenLength){
                    longestPalindrome = oddPalindrome;
                    maxLength = oddLength;
                } else {
                    longestPalindrome = evenPalindrome;
                    maxLength = evenLength;
                }
            }
        }

        return longestPalindrome;
    }

    /// Better with StringBuilder, better run time and less space.
    public String getPalindrome(String s, int left, int right){
        StringBuilder palindrome = new StringBuilder();

        if( left>=0 && right<=s.length()-1 && s.charAt(left) == s.charAt(right)){
            if(left != right){
                palindrome.append(s.charAt(left)).append(s.charAt(right));
            } else {
                palindrome.append(s.charAt(left));
            }
        } else {
            return "";
        }

        left--; right++;

        while(left>=0 && right <= s.length()- 1){
            if(s.charAt(left) == s.charAt(right)){
                palindrome.append(s.charAt(right)).insert(0, s.charAt(left));
                left--; right++;
            } else {
                break;
            }
        }

        return palindrome.toString();
    }


    // Bottom Up Approach.
    // To get an answer for dp[i][j], we should know the answer to dp[i+1][j-1]
    // That is why we start the index i from the end.
    public String longestPalindrome4(String s) {
        int n = s.length();
        String res = "";

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && ( j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

    // Better DP.
    public String longestPalindrome5(String s) {
        String ans = "";
        if(s.length() == 0) {
            return ans;
        }

        int n = s.length(), start = 0, end = 0;

        boolean[][] DP = new boolean[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {

                // for length 1, 2, 3, if characters at i and j are equal, we can set dp[i][j] to true.
                // can also put j-i< 2, j-i< 3 is minor optimization.
                DP[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || DP[i+1][j-1]);

                if(DP[i][j] && (j - i > end - start)) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end+1);
    }

}
