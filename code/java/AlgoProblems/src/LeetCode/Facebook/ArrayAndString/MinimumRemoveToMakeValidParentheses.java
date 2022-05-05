package LeetCode.Facebook.ArrayAndString;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*

 */
// O(n) time and space.
public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalidIndices = new HashSet<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    invalidIndices.add(i);
                } else{
                    stack.pop();
                }
            }
        }

        while(!stack.isEmpty()){
            invalidIndices.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(!invalidIndices.contains(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    // Two Parse String Builder.
    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }
        return sb;
    }

    public String minRemoveToMakeValid2(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }

    // Shortened 2 Parse String Builder. Nice Solution: Remove invalid ")" and "(" separately.
    public String minRemoveToMakeValid3(String s) {
        // Parse 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            }
            if (c == ')') {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }

        // Parse 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) {
                    continue;
                }
            }
            result.append(c);
        }

        return result.toString();
    }
}
