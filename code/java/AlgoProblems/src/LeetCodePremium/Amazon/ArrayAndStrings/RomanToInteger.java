package LeetCodePremium.Amazon.ArrayAndStrings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public int romanToInt(String s) {
        // O(1) time and space
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1); map.put('V', 5);
        map.put('X', 10); map.put('L', 50);
        map.put('C', 100); map.put('D', 500); map.put('M', 1000);

        int answer = 0; answer += map.get(s.charAt(s.length()-1));

        for(int i=s.length()-2; i>=0 ; i--){
            char currentCharacter = s.charAt(i);
            char nextCharacter = s.charAt(i+1);

            if(map.get(currentCharacter) >= map.get(nextCharacter)){
                answer += map.get(currentCharacter);
            } else {
                answer -= map.get(currentCharacter);
            }
        }

        return answer;
    }
}
