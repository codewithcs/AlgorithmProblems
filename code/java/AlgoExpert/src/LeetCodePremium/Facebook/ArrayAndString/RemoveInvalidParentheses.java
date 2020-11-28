package LeetCodePremium.Facebook.ArrayAndString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid.
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).
 */
public class RemoveInvalidParentheses {
    private  List<String> res;
    public List<String> removeInvalidParentheses(String s) {
        res=new ArrayList();
        recur(s,0,0,new char[]{'(',')'});
        return res;
    }
    private void recur(String s,int left,int right,char[] paranthesis){
        int len=s.length();int val=0;

        for(;right<len;right++){
            char ch=s.charAt(right);
            if(ch==paranthesis[0]) {
                val++;
            } else if(ch==paranthesis[1]) {
                val--;
            }
            if(val<0) {
                break;
            }
        }

        if(val<0){
            for(;left<=right;left++){
                char ch=s.charAt(left);
                if(ch!=paranthesis[1]) {
                    continue;
                }
                if(left>1 && s.charAt(left)==s.charAt(left-1)) {
                    continue;
                }
                String temp=s.substring(0,left)+s.substring(left+1);
                recur(temp,left,right,paranthesis);
            }
        }
        else if(val>0){
            recur(new StringBuilder(s).reverse().toString(),0,0,new char[]{')','('});
        }
        else {
            res.add(paranthesis[0]=='('?s:new StringBuilder(s).reverse().toString());
        }
    }

    // Approach 2: Backtracking
    //
    private Set<String> validExpressions = new HashSet<String>();
    private int minimumRemoved;

    private void reset() {
        this.validExpressions.clear();
        this.minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
        // If we have reached the end of string.
        if (index == s.length()) {

            // If the current expression is valid.
            if (leftCount == rightCount) {

                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= this.minimumRemoved) {
                    // Convert StringBuilder to a String. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        } else {

            char currentCharacter = s.charAt(index);
            int length = expression.length();
            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {
                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);

                // If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }
                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length);
            }
        }
    }

    public List<String> removeInvalidParentheses2(String s) {
        this.reset();
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList(this.validExpressions);
    }

}
