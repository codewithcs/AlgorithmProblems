package LeetCode.Amazon.Others;

import java.util.Arrays;

public class DecodeWays {
    public static void main(String[] args) {
        System.out.println("abc".substring(3, 3));
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return helper(s, 0, dp);
    }

    public int helper(String s, int decodingPointer, int[] dp){
        if(decodingPointer >= s.length()){
            return 1;
        }

        if(dp[decodingPointer] > -1){
            return dp[decodingPointer];
        }

        int decodings = 0;

        for(int i=1; i<=2 ; i++){
            if(decodingPointer+i <= s.length()){
                String sub = s.substring(decodingPointer, decodingPointer+i);
                if(isValidString(sub)) {
                    decodings += helper(s, decodingPointer+i,  dp);
                }
            }
        }

        dp[decodingPointer] = decodings;
        return dp[decodingPointer];
    }

    boolean isValidString(String s){
        if(s.length()==0 || s.charAt(0) == '0'){
            return false;
        }
        int value = Integer.parseInt(s);

        return value >=1 && value <= 26;
    }

    // Bottom Up Approach.
    public int numDecodings2(String s) {

        if(s == null || s.length() == 0) {
            return 0;
        }

        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i += 1) {

            // Check if successful single digit decode is possible.
            if(s.charAt(i-1) != '0') {
                dp[i] += dp[i-1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i-2, i));
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[s.length()];
    }
}

