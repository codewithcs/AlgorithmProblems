package LeetCode.Facebook.SimilarQuestions;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces.

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class BasicCalculator {
    public static int evaluate(int num2, int num1, char ch) {
        if( ch == '+') {
            return num1+num2;
        }
        return num1-num2;
    }

    public static int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque(); // Can simply use a Stack.
        Deque<Character> operator = new ArrayDeque();

        for (int i = 0, n=s.length() ; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == ' ')  {
                continue;
            }

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = 10*num + (s.charAt(i)-'0') ;
                    i++;
                }
                nums.push(num);
                i--; // as we are incrementing i in the for loop.
            }
            else if (ch== '(') {
                operator.push(ch);
            }
            else if (ch == ')') {
                //Then consider the whole expression between (...) as single entity
                //Postfix order is stored in stack, evaluate till find first '('
                while (operator.peek() != '(') {
                    nums.push(evaluate(nums.pop(),nums.pop(),operator.pop()));
                }
                operator.pop();
            }
            // Current token is an operator.
            else {
                // Evaluate previous operands and add in nums, till you don't reach '('
                while (!operator.isEmpty() &&  operator.peek() != '(') {
                    nums.push(evaluate(nums.pop(), nums.pop(), operator.pop()));
                }
                // Push current token to operator.
                operator.push(ch);
            }
        }

        while (!operator.isEmpty()) { // Don't need to worry about '(' since we will only be left with numbers and +, -
            nums.push(evaluate(nums.pop(),nums.pop(),operator.pop()));
        }

        return nums.peek();
    }
}