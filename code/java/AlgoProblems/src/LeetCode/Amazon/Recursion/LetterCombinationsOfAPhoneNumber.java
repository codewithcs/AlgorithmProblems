package LeetCode.Amazon.Recursion;
/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations
that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        if (digits.isEmpty()) {
            return list;
        }

        Map<Integer, Character[]> map = new HashMap<>();

        map.put(2, new Character[]{'a', 'b', 'c'});
        map.put(3, new Character[]{'d', 'e', 'f'});
        map.put(4, new Character[]{'g', 'h', 'i'});
        map.put(5, new Character[]{'j', 'k', 'l'});
        map.put(6, new Character[]{'m', 'n', 'o'});
        map.put(7, new Character[]{'p', 'q', 'r', 's'});
        map.put(8, new Character[]{'t', 'u', 'v'});
        map.put(9, new Character[]{'w', 'x', 'y', 'z'});

        helper(map, Integer.parseInt(digits), list, "");

        return list;
    }

    public void helper(Map<Integer, Character[]> map, int num, List<String> list, String s){
        int first = num / 10 ;
        int second = num % 10 ;

        if(second == 0){
            list.add(s);
            return ;
        }

        Character[] charArray = map.get(second);
        for(int i=0 ; i < charArray.length ; i++){
            helper(map, first , list, charArray[i] + s);
        }

    }
}