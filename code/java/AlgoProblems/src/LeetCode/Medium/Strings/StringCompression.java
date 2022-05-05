package LeetCode.Medium.Strings;

/*
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

Follow up:
Could you solve it using only O(1) extra space ?

Constraints:
1 <= chars.length <= 2000
chars[i] is a lower-case English letter, upper-case English letter, digit, or symbol.
 */
public class StringCompression {
    public static void main(String[] args) {
        char[] chars = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
        System.out.println(compress(chars));
    }

    // O(n) time and O(1) space ?
    public static int compress(char[] chars) {
        int previousIndex = 0 ;
        int indexToWrite = 0;
        int count = 1;
        char charToWrite = chars[0];

        for(int i=1; i< chars.length; i++){
            char current = chars[i];
            if(current == chars[previousIndex]){
                count++;
                previousIndex = i;
            } else {
                chars[indexToWrite++] = charToWrite;
                if(count > 1){
                    String charCount = String.valueOf(count); // or can create a char[] using ("" + count).toCharArray()
                    for(int j=0; j< charCount.length(); j++){
                        chars[indexToWrite++] = charCount.charAt(j);
                    }
                }
                count = 1;
                previousIndex = i;
                charToWrite = current;
            }
        }

        chars[indexToWrite++] = charToWrite;
        if(count > 1){
            String charCount = String.valueOf(count);
            for(int j=0; j< charCount.length(); j++){
                chars[indexToWrite++] = charCount.charAt(j);
            }
        }

        return indexToWrite;
    }
}
