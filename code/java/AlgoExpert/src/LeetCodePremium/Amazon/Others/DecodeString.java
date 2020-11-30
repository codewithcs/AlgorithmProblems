package LeetCodePremium.Amazon.Others;

import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[abc]"));
    }

    public static String decodeString(String s) {
            Stack<Integer> countStack = new Stack<>();
            Stack<StringBuilder> stringStack = new Stack<>();

            StringBuilder currentString = new StringBuilder();

            int k = 0;

            for (char ch : s.toCharArray()) {
                if (Character.isDigit(ch)) {
                    k = k * 10 + ch - '0';
                } else if (ch == '[') {
                    // push the number k to countStack
                    countStack.push(k);
                    // push the currentString to stringStack
                    stringStack.push(currentString);
                    // reset currentString and k
                    currentString = new StringBuilder();
                    k = 0;
                } else if (ch == ']') {
                    StringBuilder decodedString = stringStack.pop();
                    // decode currentK[currentString] by appending currentString k times
                    for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                        decodedString.append(currentString);
                    }
                    currentString = decodedString;
                } else { // 'a' - 'z'
                    currentString.append(ch);
                }
            }

            return currentString.toString();
        }
}
