package LeetCodePremium.Amazon.ArrayAndStrings;

import java.util.HashSet;
import java.util.Set;

public class LengthLongestWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println("length is : " + lengthOfLongestSubstring(""));
    }

    // O(n^2) time | O(n) space
    public static int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        Set<Character> set = new HashSet<>();

        int j = 0;
        int length = 0;
        int max = 0;

        int i = 0;

        while (i < charArray.length) {

            if (set.contains(charArray[i])) {
                set = new HashSet<>();
                j++;
                i = j;
                if (length > max) max = length;
                length = 0;
            } else {
                length++;
                set.add(charArray[i]);
                i++;
            }

        }

        if (length > max) max = length;

        return max;
    }
// O(n) time | O(min(m, n)) space for the set,  m is the size of the alphabet
    public static int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int right = 0;
        int max = 0;

        Set<Character> set = new HashSet<>();

        while( right < s.length()){

            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left));
                left++;
            }

        }

        return max;
    }
    // See the optimization for sliding window.
}