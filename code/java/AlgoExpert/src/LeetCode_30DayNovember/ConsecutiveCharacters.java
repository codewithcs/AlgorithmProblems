package LeetCode_30DayNovember;

/*
Naive approach: O(n^2)

Given a string s, the power of the string is the maximum length of a non-empty substring
that contains only one unique character.

Return the power of the string.
 */
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        int left = 0;
        int right = 1; int max =1;

        int current = 1;

        while( right< s.length()){ // O(n)
            if(s.charAt(left) == s.charAt(right)){
                current++;
                max = Math.max(current, max);
                right++;
            } else{
                current = 1;
                left++; right = left+1;
            }
        }

        return max;
    }
}
