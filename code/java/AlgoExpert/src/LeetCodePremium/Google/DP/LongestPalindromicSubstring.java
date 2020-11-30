package LeetCodePremium.Google.DP;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s.length() == 1) return s;

        int maxEvenPalindrome = 0;
        int maxOddPalindrome = 1;

        int leftOddIndex = 0; int rightOddIndex= 0;

        int leftEvenIndex =0; int rightEvenIndex = 0;

        for(int i=0 ; i<s.length() ; i++){
            int currentLength = generateOddPalindrome(i, s);
            if(maxOddPalindrome < currentLength){
                leftOddIndex = i - currentLength/2;
                rightOddIndex = i + currentLength/2;
                maxOddPalindrome = currentLength;
            }
        }

        for(int i=0; i<s.length()-1; i++){
            int currentLength = generateEvenPalindrome(i, i+1, s);
            if(maxEvenPalindrome < currentLength){
                rightEvenIndex = (i+1) + (currentLength/2-1);
                rightOddIndex = i - (currentLength/2-1);
                maxEvenPalindrome = currentLength;
            }
        }

        return maxEvenPalindrome > maxOddPalindrome ? s.substring(leftEvenIndex, rightEvenIndex+1) : s.substring(leftOddIndex, rightOddIndex+1) ;
    }

    public int generateOddPalindrome(int middle, String s){
        int length = 1;
        int left = middle-1; int right = middle +1;

        while(left >= 0 && right <= s.length()-1){
            if(s.charAt(left) == s.charAt(right)){
                length += 2;
                left--; right++;
            } else {
                break;
            }
        }
        return length;
    }

    public int generateEvenPalindrome(int left, int right, String s){
        int length = 0;

        while(left >= 0 && right <= s.length()-1){
            if(s.charAt(left) == s.charAt(right)){
                length += 2;
                left--; right++;
            } else {
                break;
            }
        }
        return length;
    }

}
