package LeetCode.Medium.Strings;

/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words.
The returned string should only have a single space separating the words. Do not include any extra spaces.

Constraints:
1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow-up: If the string data type is mutable in your language,
can you solve it in-place with O(1) extra space?
 */

import java.util.ArrayList;
import java.util.List;

public class ReverseWordsInAString {

    // Solution 1.
    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        char[] charArray = s.toCharArray();

        StringBuilder word = new StringBuilder();

        for(int i=0; i< charArray.length ; i++){
            if(charArray[i] != ' '){
                while(i< charArray.length && charArray[i] != ' '){
                    word.append(charArray[i]);
                    i++;
                }
                list.add(word.toString());
                word = new StringBuilder();
            }
        }

        StringBuilder result = new StringBuilder();
        for(int i= list.size()-1; i>=1 ; i--){
            result.append(list.get(i)).append(" ");
        }

        result.append(list.get(0));

        System.out.println(list);

        return result.toString();
    }

    public char[] reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
        return arr;
    }


    // Solution 2. Iterate from back, no need to use List<String>
    public String reverseWords3(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
            } else {
                int j = i;
                while (i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                if (sb.length() > 0) { // if previous words already exist then append ' '
                    sb.append(' ');
                }

                for (int k = i + 1; k <= j; k++) {
                    sb.append(s.charAt(k));
                }
            }
        }

        return sb.toString();
    }

    // Solution 3, // Constant Space Solution. Also see this.
    public String reverseWords4(String s) {
        if (s.length() < 1) {
            return s; // empty string
        }

        int startIdx = 0; // To set the final positions.

        char[] str = s.toCharArray();

        // reverse whole string
        reverse3(str, 0, str.length - 1);

        // i points to the start of the word.
        // j points to the next index of the word.

        // reverse word one by one
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ') {

                if (startIdx != 0) { // If this is not 0, then it means that we have formed words before.
                    str[startIdx++] = ' ';
                }

                int j = i;
                while (j < str.length && str[j] != ' ') {
                    str[startIdx++] = str[j++];
                }

                reverse3(str, startIdx - (j - i), startIdx - 1);

                i = j;
            }
        }

        return new String(str, 0, startIdx); // number of characters in the final string will be equal to startIdx.
    }

    private void reverse3(char[] str, int begin, int end) {
        for (; begin < end; begin++, end--) {
            char tmp = str[begin];
            str[begin] = str[end];
            str[end] = tmp;
        }
    }



    // Solution 5, Another Clean Java solution
    public String reverseWords5(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse2(a, 0, n - 1);

        // step 2. reverse each word
        reverseWords(a, n);

        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i); // i is the number of characters we need.
    }

    // reverse a[] from a[i] to a[j]
    private void reverse2(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }


    // Same idea as above.
    public String reverseWords6(String s) {
        if (s.length() < 1) {
            return s; // empty string
        }

        char[] str = s.toCharArray();

        // reverse whole string
        reverse4(str, 0, str.length - 1);

        System.out.println("Str is : " + new String(str));

        // reverse word one by one
        for (int i = 0; i < str.length; ) {
            if(str[i] != ' '){
                int startIdx = i;
                int endIdx = i+1;

                while(endIdx < str.length && str[endIdx] != ' '){
                    endIdx++;
                }

                if(endIdx <= str.length){
                    reverse4(str, startIdx, endIdx-1);
                    System.out.println("Str is : " + new String(str));
                }

                i = endIdx;
            } else {
                i++;
            }
        }

        // Have to clean the spaces now
        return cleanSpaces(str, str.length);
    }

    private void reverse4(char[] str, int begin, int end) {
        for (; begin < end; begin++, end--) {
            char tmp = str[begin];
            str[begin] = str[end];
            str[end] = tmp;
        }
    }


}
