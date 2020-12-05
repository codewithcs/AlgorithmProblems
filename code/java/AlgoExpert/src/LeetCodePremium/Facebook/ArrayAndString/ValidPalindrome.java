package LeetCodePremium.Facebook.ArrayAndString;

/*
Given a string, determine if it is a palindrome,
considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty
string as valid palindrome.

Constraints:
s consists only of printable ASCII characters.
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        isPalindrome("Marge, let's \\\"[went].\\\" I await {news} telegram.");
    }

    public static boolean isPalindrome(String s) {
        String[] words = s.split("[^a-zA-Z0-9]+");
        StringBuilder word = new StringBuilder();
        for(String current: words){
            char[] charArray = current.toCharArray();
            for(int i=0; i<charArray.length; i++){
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
            word.append(String.valueOf(charArray));
        }
        System.out.println("word is : " + word.toString());

        int left=0; int right=word.length();

        return true;
    }

    private boolean isAlphanumeric(char c) {
        if((48 <= c && c <= 57) || (97 <= c && c <= 122) || (65 <= c && c <= 90)) { // 0-9, a-z, A-Z
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindrome2(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(!isAlphanumeric(s.charAt(left))) {
                left ++;
            } else if(!isAlphanumeric(s.charAt(right))) {
                right --;
            } else if(Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left ++;
                right --;
            } else {
                return false;
            }
        }
        return true;
    }
}
