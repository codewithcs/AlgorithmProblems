package LeetCode.Facebook.SimilarQuestions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

Constraints:
1 <= s.length <= 3 * 10^5
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculator2 {
    public static int getPriority(char c){
        if(c == '+' || c == '-') return 1;
        else return 2;
    }

    public static int evaluate(int first, int second, char operator){
        if(operator == '+'){
            return second+first;
        } else if(operator == '-'){
            return second-first;
        } else if(operator == '*'){
            return second*first;
        } else {
            return second/first;
        }
    }

    // Works correctly now.
    public static int calculate(String s) {
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for(int i=0; i< s.length() ; i++){
            char current = s.charAt(i);

            if(current == ' ') continue;

            else if(Character.isDigit(current)){
                int value = 0;

                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    value = value*10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(value);

            } else { // operator
                while(!operators.isEmpty() && getPriority(operators.peek()) >= getPriority(current)){
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.push(current);
            }

            System.out.println("Stack is : " + Arrays.asList(numbers.toArray()));
        }

        while(!operators.isEmpty()){
            System.out.println("Stack is : " + Arrays.asList(numbers.toArray()));
            numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
        }

        return numbers.peek();
    }

    public static void main(String[] args) {
        System.out.println(calculate2("2+3*5"));
    }

    // Without Stack: O(n) time and O(1) space.
    public static int calculate2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+'; // previous operation

        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
            System.out.println("Result is : " + result);
        }
        result += lastNumber;
        return result;
    }

    // Using a single Stack for numbers: First evaluate higher precedence operators * and / and then reduce everything to additions.
    public int calculate3(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();

        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
