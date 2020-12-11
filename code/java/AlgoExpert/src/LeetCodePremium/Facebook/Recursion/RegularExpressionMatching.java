package LeetCodePremium.Facebook.Recursion;

/*
Given an input string (s) and a pattern (p), implement regular expression matching with
support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Constraints: Constraints generally come to be known after asking clarifying questions.
0 <= s.length <= 20
0 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */

// We have to implement functionality of matches() function of String class.
// Similar to Pattern Matcher on AlgoExpert.
public class RegularExpressionMatching {

    // Hitting the base case on the right side.
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()){ // Base Case
            return text.isEmpty();
        }

        boolean first_match =
                (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
        } else { // length of pattern is < 2 or second character is not '*' but can be present later.
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    // DP solution for 3rd solution.
    public boolean isMatch2(String text, String pattern){
        char[] textArray = text.toCharArray(); char[] patternArray = pattern.toCharArray();
        boolean[][] result = new boolean[textArray.length+1][patternArray.length+1];
        result[0][0] = true;
        for(int i=1; i<result[0].length; i++){
            if(patternArray[i-1] == '*'){
                result[0][i] = result[0][i-2];
            }
        }

        for(int i=1; i<result.length; i++){
            for(int j=1; j< result[0].length; j++){
                if(patternArray[j-1] == '.' || patternArray[j-1] == textArray[i-1]){
                    result[i][j] = result[i-1][j-1];
                } else if (patternArray[j - 1] == '*') {
                    result[i][j] = result[i][j-2];
                    if(patternArray[j-2] == '.' || patternArray[j-2] == textArray[i-1]){
                        result[i][j] = result[i][j] || result[i-1][j];
                    }
                } else {
                    result[i][j] = false;
                }
            }
        }

        return result[textArray.length][patternArray.length];
    }

    // Recursive solution to the DP solution.
    public static boolean isMatch3(String text, String pattern) {
        return traverse(text, pattern, text.length(), pattern.length());
    }

    public static boolean traverse(String text, String pattern, int i, int j){
        if(pattern.substring(0, j).isEmpty()){
            return text.substring(0, i).isEmpty();
        }

        if( !text.substring(0, i).isEmpty() && (pattern.charAt(j-1) == '.' || pattern.charAt(j-1) == text.charAt(i-1) ) ){
            return traverse(text, pattern, i-1, j-1);
        } else if (pattern.charAt(j-1) == '*'){
            boolean b = traverse(text, pattern, i, j-2);
            if(!text.substring(0, i).isEmpty() && (pattern.charAt(j-2) == '.' || pattern.charAt(j-2) == text.charAt(i-1) )){
                b = b || traverse(text, pattern, i-1, j);
            }
            return b;
        } else {
            return false;
        }
    }
}
