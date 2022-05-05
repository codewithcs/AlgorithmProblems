package LeetCode.Amazon.ArrayAndStrings;
/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

s consists of parentheses only '()[]{}'.
 */

import java.util.Stack;

public class ValidParentheses {

    // O(n) time, O(n) space worst case when all brackets are opening types.
    // push and pop operations in a stack take O(1) time.
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i< s.length(); i++){
            char current = s.charAt(i);

            if(current == ')' && !stack.isEmpty() && stack.peek() == '(') { // important to check for non-empty stack.
                stack.pop();
            } else if ( current == '}' && !stack.isEmpty() && stack.peek() == '{'){
                stack.pop();
            } else if(current == ']' && !stack.isEmpty() && stack.peek() == '['){
                stack.pop();
            } else if(current == '{' || current == '(' || current == '[' ){
                stack.push(current);
            } else {
                return false;
            }
        }

        return stack.isEmpty(); // For the case (){}[
    }

    // Interesting Solution:
    public boolean isValid2(String s) {
        Stack<Integer> p = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            int q = "(){}[]".indexOf(s.substring(i, i + 1));
            if(q % 2 == 1) {
                if(p.isEmpty() || p.pop() != q - 1) return false;
            } else p.push(q);
        }
        return p.isEmpty();
    }
}
