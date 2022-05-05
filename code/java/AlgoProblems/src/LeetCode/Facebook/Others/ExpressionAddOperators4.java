package LeetCode.Facebook.Others;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ExpressionAddOperators4 {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.length() == 0) {
            return result;
        }
        String current = "";
        String[] operators = { "", "+", "-", "*" };
        generateString(0, num, current, operators, result, target);
        return result;
    }

    public void generateString(int index, String num, String current, String[] operators, List<String> result, int target){
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

    // Output Limit Exceeded.
    public boolean checkExpression(String s, int target) {
        if( s== null || s.isEmpty()) return false;
        Deque<Integer> numbers = new ArrayDeque<>();
        int result = 0;
        int len = s.length();
        char previousOperator = '+';
        int value = 0; int lastNumber = 0; boolean firstTime = true; int numDigits = 0;
        boolean startsWithZero = false;

        for(int i=0; i< s.length() ; i++){
            char current = s.charAt(i);

            if(Character.isDigit(current)){
                value = value*10 + (current - '0'); numDigits++;

                if(firstTime && value == 0){
                    startsWithZero = true;
                }
                firstTime = false;
            }

            if(startsWithZero && numDigits > 1) return false;

            if(!Character.isDigit(current) && !Character.isWhitespace(current) || i == len - 1){
                if(previousOperator == '+' || previousOperator == '-'){
                    result = result + lastNumber;
                    lastNumber = previousOperator == '+' ? value : -value;
                } else if(previousOperator == '*'){
                    lastNumber = lastNumber * value ;
                } else{
                    lastNumber = lastNumber / value;
                }

                value = 0;
                previousOperator = current;

                firstTime = true;
                numDigits = 0;
            }

        }

        result += lastNumber;

        return result == target;
    }
}
