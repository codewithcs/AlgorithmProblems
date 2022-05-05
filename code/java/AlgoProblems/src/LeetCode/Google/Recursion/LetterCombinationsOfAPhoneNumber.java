package LeetCode.Google.Recursion;

import java.util.*;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations
that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return new ArrayList<String>();
        }

        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));

        List<String> answer = new ArrayList<>();
        traverse(answer, map, "", digits);
        return answer;
    }

    public void traverse(List<String> answer, Map<Integer, List<Character>> map, String current, String digits){
        if(digits.length() == 0){
            answer.add(current);
            return;
        }

        int firstDigit = digits.charAt(0) - '0';
        for(char currentCharacter: map.get(firstDigit)){
            traverse(answer, map, current + currentCharacter, digits.substring(1));
        }
    }
}
