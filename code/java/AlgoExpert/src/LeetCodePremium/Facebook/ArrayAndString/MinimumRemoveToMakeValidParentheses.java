package LeetCodePremium.Facebook.ArrayAndString;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*

 */
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
}
