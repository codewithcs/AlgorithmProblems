package LeetCodePremium.Google.Recursion;

import java.util.HashMap;
import java.util.Map;

/*
A strobogrammatic number is a number that looks the same when
rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers
that exist in the range of low <= num <= high.

Note:
Because the range might be a large number, the low and high
numbers are represented as string.
 */
public class StrobogrammaticNumber3 {
    public int strobogrammaticInRange(String low, String high) {
        Map<Character, Character> map = new HashMap<>();
        map.put('1','1');
        map.put('8','8');
        map.put('6','9');
        map.put('9','6');
        map.put('0','0');



        return 0;
    }
}
