package LeetCode.Google.Recursion;

import java.util.HashMap;
import java.util.Map;

/*
A strobogrammatic number is a number that looks the same when
rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic.
The number is represented as a string.
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {

        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        StringBuilder sbReversed = new StringBuilder(num);
        sbReversed.reverse();

        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);

            if (!map.containsKey(c)){
                return false;
            }

            if (map.get(c) != sbReversed.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public boolean isStrobogrammatic2(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');

        int i = 0;
        int j = num.length() - 1;
        while(i <= j) {
            char c1 = num.charAt(i);
            char c2 = num.charAt(j);
            if(!map.containsKey(c1) || !map.containsKey(c2)) {
                return false;
            }
            if(c1 != map.get(c2)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
