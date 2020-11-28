package LeetCodePremium.Facebook.ArrayAndString;

import java.util.Stack;

public class MinimumAddToMakeParenthesesValid {

    // O(n) time and O(n) space.
    public int minAddToMakeValid(String S) {
        if(S.length() == 0) return 0;

        int count = 0;
        Stack<Character> stack = new Stack<>();

        for(char current: S.toCharArray()){
            if(current == '('){
                stack.push(current);
            } else if(current == ')'){
                if(stack.isEmpty()){
                    count++;
                } else {
                    stack.pop();
                }
            }
        }

        count += stack.size();
        return count;
    }

    // Smarter Way without stack. O(n) time and O(1) space.
    public int minAddToMakeValid2(String s){
        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {
            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;
                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        return left + right;
    }
}
