package LeetCode.Facebook.SimilarQuestions;

import java.util.Stack;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:
Division between two integers should truncate toward zero.
The given RPN expression is always valid.
That means the expression would always evaluate to a result and
there won't be any divide by zero operation.
 */
public class EvaluateReversePolishNotation {
    // Use .equals() method for String comparisons. 
    public int evaluate(int first, int second, String s){
        if(s.equals("+")){
            return second+first;
        } else if(s.equals("-")){
            return second-first;
        } else if( s.equals("*")){
            return second*first;
        } else {
            return second/first;
        }
    }

    // O(n) time and space.
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i< tokens.length; i++){
            String current = tokens[i];
            if(!"+-*/".contains(current)){ // this improved the complexity.
                stack.push(Integer.parseInt(current));
            } else{ // operator
                stack.push(evaluate(stack.pop(), stack.pop(), current));
            }
        }
        return stack.peek();
    }
}
