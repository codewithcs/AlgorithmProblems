package LeetCodePremium.Facebook.ArrayAndString;

import java.util.Stack;

public class MinimumAddToMakeParenthesesValid {
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
}
