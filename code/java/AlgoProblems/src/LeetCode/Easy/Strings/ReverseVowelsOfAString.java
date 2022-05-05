package LeetCode.Easy.Strings;

/*
Given a string s, reverse only all
the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u',
and they can appear in both cases.

Constraints:
1 <= s.length <= 3 * 10^5
s consist of printable ASCII characters.
 */

import java.util.ArrayList;
import java.util.List;

// Constraints are important here.
public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        char[] charArray = s.toCharArray();

        for(int i=0; i< charArray.length; i++){
            if(checkVowel(charArray[i])){
                vowels.add(charArray[i]);
            }
        }

        if(vowels.size() == 0){
            return s;
        }

        int currentIndex = vowels.size()-1;

        for(int i=0; i< charArray.length; i++){
            if(checkVowel(charArray[i])){
                charArray[i] = vowels.get(currentIndex);
                currentIndex--;
            }
        }

        return new String(charArray);
    }

    public boolean checkVowel(char current){
        return current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u' || current == 'A' || current == 'E' || current == 'I' || current == 'O' || current == 'U' ;
    }

    // Constant Space.
    public String reverseVowels2(String s) {
        char[] charArray = s.toCharArray();

        int i=0;
        int j=charArray.length-1;

        while(i< j){
            while(i < charArray.length && !checkVowel(charArray[i]) ){
                i++;
            }

            while(j >=0 && !checkVowel(charArray[j]) ){
                j--;
            }

            // Important Condition.
            if(i> j) break;

            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++; j--;
        }

        return new String(charArray);
    }

    // Slightly Cleaner solution.
    public String reverseVowels3(String s) {
        char[] charArray = s.toCharArray();

        int i=0;
        int j=charArray.length-1;

        while(i< j){
            while(i< j && !checkVowel(charArray[i]) ){
                i++;
            }

            while(i< j && !checkVowel(charArray[j]) ){
                j--;
            }

            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++; j--;
        }

        return new String(charArray);
    }
}
