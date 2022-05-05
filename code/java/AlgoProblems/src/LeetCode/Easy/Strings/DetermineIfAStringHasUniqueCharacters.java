package LeetCode.Easy.Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class DetermineIfAStringHasUniqueCharacters {
    int MAX_CHAR = 256;

    // Use of Extra Data Structure. O(n) time complexity.
    /*
    This approach assumes ASCII char set(8 bits).
    The idea is to maintain a boolean array for the characters.

    The 256 indices represent 256 characters.
    All the array elements are initially set to false.

    As we iterate over the string, set true at the
    index equal to the int value of the character.

    If at any time, we encounter that the array value is already true,
    it means the character with that int value is repeated.
     */
    boolean uniqueCharacters(String str) {
        // If length is greater than 256,
        // some characters must have been repeated
        if (str.length() > MAX_CHAR) {
            return false;
        }

        boolean[] chars = new boolean[MAX_CHAR]; // constant space.
        Arrays.fill(chars, false);

        for (int i = 0; i < str.length(); i++) {
            int index = (int)str.charAt(i);

            /* If the value is already true, string
               has duplicate characters, return false */
            if (chars[index]) {
                return false;
            }

            chars[index] = true;
        }

        /* No duplicates encountered, return true */
        return true;
    }


/*
Without Extra Data Structure.
The approach is valid for strings having alphabet as a-z. This approach is a little tricky.
Instead of maintaining a boolean array, we maintain an integer value called checker(32 bits).

As we iterate over the string, we find the int value of the character with respect to ‘a’
with the statement int bitAtIndex = str.charAt(i)-‘a’;

Then the bit at that int value is set to 1 with the statement 1 << bitAtIndex .
Now, if this bit is already set in the checker, the bit AND operation would make the checker > 0.

Return false in this case.
Else Update checker to make the bit 1 at that index with the statement checker = checker | (1 <<bitAtIndex);
 */
    // Debug this.
    boolean uniqueCharacters2(String str) {
        // Assuming string can have characters a-z
        // this has 32 bits set to 0
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int bitAtIndex = str.charAt(i) - 'a';

            // if that bit is already set in checker,
            // return false
            if ((checker & (1 << bitAtIndex)) > 0) {
                return false;
            }

            // otherwise update and continue by
            // setting that bit in the checker
            checker = checker | (1 << bitAtIndex);
        }

        // no duplicates encountered, return true
        return true;
    }

    // Using Java Streams
    boolean uniqueCharacters3(String s) {
        // If at any character more than once create another stream
        // stream count more than 0, return false
       // return s.chars().filter(e-> Collections.frequency(s.chars().boxed().collect(Collectors.toList()), e) > 1).count() > 1 ? false: true;
        return s.chars().filter(e -> Collections.frequency(s.chars().boxed().collect(Collectors.toList()), e) > 1).count() <= 1;
    }
}
