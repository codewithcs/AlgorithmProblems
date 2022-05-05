package LeetCode.Facebook.Others;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators3 {
    public static void main(String[] args) {
        System.out.println(addOperators("224", 16));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> expressions = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return expressions;
        }
        addOperators(num, target, 0, 0, 0, "", expressions);
        return expressions;
    }

    // Generating Substrings.
    // Calculating the current value on the fly, that is in the recursion itself.
    private static void addOperators(String num, int target, int index, long currentValue, long lastValue, String expression, List<String> result) {
        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */
        if (index == num.length()) {
            /**
             * Our Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */
            if (currentValue == target) {
                //then this is our solution.
                result.add(expression);
            } //2. if not, we discard
            return;
        }

        /**
         * Our choices:
         * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
         */
        for (int i = index; i < num.length(); i++) {
            /**
             * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
             *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
             */
            if (i != index && num.charAt(index) == '0') { // Substrings that can start with a 0. "0" is valid but "0xyz" is not.
                break;
            }

            /// Ask the interviewer about whether the number can overflow.
            long currentDigitsValue = Long.parseLong(num.substring(index, i + 1));

            /**
             * Our Constraints:
             * 4. We need two operands for a operator and operator can't be apply on single operand
             */

            if (index == 0) {
                // as this is the first digit only, then don't apply any operator
                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression + currentDigitsValue, result);
            } else {
                //We have two operands, last and current

                /**
                 * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitsValue = 5 then expression is 10 + 2 + 5 = 17
                 * So last value would be 5
                 */
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression + "+" + currentDigitsValue, result);

                /**
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitsValue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression + "-" + currentDigitsValue, result);


                /**
                 * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
                 * Current value become = currentValue - lastValue + last*currentDigitsValue;
                 * For example
                 * current value = 12 ,
                 * last value = 2 ( let say we applied + as 10 + 2 )
                 * currendDigitValue = 4
                 * so expression become : 10 + 2 * 4
                 * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
                 *
                 * New value = 10 + 2 * 4 = 18
                 * last value = 2*4 = 8
                 *
                 *
                 */
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression + "*" + currentDigitsValue, result);
            }
        }
    }
}