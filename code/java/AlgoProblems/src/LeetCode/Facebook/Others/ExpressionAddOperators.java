package LeetCode.Facebook.Others;

import java.util.*;

/*
Given a string that contains only digits 0-9 and a target value,
return all possibilities to add binary operators (not unary) +, -, or * between
the digits so they evaluate to the target value.

Constraints:
0 <= num.length <= 10
num only contain digits.

After a bit thinking, this looked like a DP problem to me.
Looks like backtracking as well. Can we optimize backtracking using DP ?

// Generate the expression and check whether it evaluates to the target.
// Can we do some kind of pruning ?
 */
public class ExpressionAddOperators{
    public static void main(String[] args) {
        System.out.println(calculate("1*2*3*4*5", 120));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.length() == 0) {
            return result;
        }
        String current = "";
        String[] operators = { "", "+", "-", "*" };
        generateString(0, num, current, operators, result, target);
        return result;
    }

    /// Output Limit Exceeded
    public static void generateString(int index, String num, String current, String[] operators, List<String> result, int target){
        if(index == num.length() -1){
            current += (num.charAt(num.length()-1));
            System.out.println("current is : " + current);
            if(checkExpression(current, target) ){
                result.add(current);
            }
            return;
        }

        String original = current;
        for(String operator: operators){
            current += (num.charAt(index) + operator);
            generateString(index+1, num, current, operators, result, target);
            current = original;
        }
    }

    public static List<String> addOperators2(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.length() == 0) {
            return result;
        }
        StringBuilder current = new StringBuilder();
        String[] operators = { "", "+", "-", "*" };
        generateString2(0, num, current, operators, result, target);
        return result;
    }

    // Output Limit Exceeded.
    public static void generateString2(int index, String num, StringBuilder current, String[] operators, List<String> result, int target){
        if(index == num.length() -1){
            current.append(num.charAt(num.length()-1));
            System.out.println("current is : " + current);
            if(checkExpression(current.toString(), target) ){
                result.add(current.toString());
            }
            return;
        }

        for(String operator: operators){
            current.append( (num.charAt(index) + operator));
            generateString2(index+1, num, new StringBuilder(current), operators, result, target);
            if(operator.equals("")){
                current.deleteCharAt(current.length()-1);
            } else{
                current.deleteCharAt(current.length()-1);
                current.deleteCharAt(current.length()-1);
            }
        }
    }

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

    public static boolean calculate(String s, int target) {
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for(int i=0; i< s.length() ; i++){
            char current = s.charAt(i);

            if(current == ' ') continue;

            else if(Character.isDigit(current)){
                int value = 0; int index = i; boolean startsWithAZero = false; int numDigits = 0;

                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    value = value*10 + (s.charAt(i) - '0');
                    numDigits++;
                    if(value == 0 && i == index){
                        startsWithAZero = true;
                    }
                    i++;
                }
                if(numDigits > 1 && startsWithAZero) return false; // Handle the case "00", "05"

                i--;
                numbers.push(value);

            } else { // operator
                while(!operators.isEmpty() && getPriority(operators.peek()) >= getPriority(current)){
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.push(current);
            }

        }

        while(!operators.isEmpty()){
            numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
        }

        return numbers.peek() == target;
    }

    // not completely correct.
    public static boolean checkExpression(String expression, int target){
        int currentNum = 0; int previous = 0; int result = 0;
        char previousOperator = '+';

        for(int i=0; i< expression.length(); i++){
            char current = expression.charAt(i);

            if(Character.isDigit(current)){
                int value = 0; int index = i; boolean startsWithAZero = false; int numDigits = 0;
                while(i < expression.length() && Character.isDigit(expression.charAt(i))){
                    value = value*10 + (expression.charAt(i) - '0');
                    numDigits++;
                    if(value == 0 && i == index){
                        startsWithAZero = true;
                    }
                    i++;
                }
                if(numDigits > 1 && startsWithAZero) return false; // Handle the case "00", "05"

                currentNum = value;
                i--;

                if(previousOperator == '+' || previousOperator == '-'){
                    result = result + currentNum;
                    previous = (previousOperator == '+') ? currentNum : -currentNum;
                } else {
                    previous = currentNum*previous;
                }

            } else { // operator
                    previousOperator = current;
                }
        }
        result += previous;

        System.out.println("result is : " + result);
        return result == target;
    }
}
