package LeetCodePremium.Facebook.SimilarQuestions;

import java.util.Arrays;
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
    public static void main(String[] args) {
        System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
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

    public static int calculate(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

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
                if(current == '+' || current == '-'){
                    if(!operators.isEmpty()){
                        numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                    }
                    operators.push(current);
                } else {
                    if(!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')){
                        numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                    }
                    operators.push(current);
                }
            }

            System.out.println("Stack is : " + Arrays.asList(numbers.toArray()));
        }

        while(!operators.isEmpty()){
            System.out.println("Stack is : " + Arrays.asList(numbers.toArray()));
            numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
        }

        return numbers.peek();
    }
}
