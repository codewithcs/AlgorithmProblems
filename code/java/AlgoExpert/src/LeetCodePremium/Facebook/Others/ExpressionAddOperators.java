package LeetCodePremium.Facebook.Others;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(checkExpression("1*2*3*4*5", 12));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        String current = "";
        String[] operators = { "", "+", "-", "*" };
        generateString(0, num, current, operators, result, target);
        return result;
    }

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
