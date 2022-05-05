package LeetCode.Facebook.Others;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators3_3 {

    // Using char[]
    public List<String> addOperators(String num, int target) {
        List<String> expressions = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return expressions;
        }
        char cache[] = new char[2 * num.length()];
        addOperators(num, target, 0, 0, 0, cache, 0, expressions);
        return expressions;
    }

    private void addOperators(String num, int target, int index, long currentValue, long lastValue, char[] expression, int expressionEndIndex, List<String> result) {

        if (index == num.length()) {
            if (currentValue == target) {
                //then this is our solution.
                result.add(new String(expression, 0, expressionEndIndex));
            }
            return;
        }

        int nextExpressionEndIndex = (index == 0) ? expressionEndIndex : expressionEndIndex + 1;
        long currentDigitsValue = 0;

        for (int i = index; i < num.length(); i++) {

            if (i != index && num.charAt(index) == '0') {
                break;
            }

            currentDigitsValue = currentDigitsValue * 10 + num.charAt(i) - '0';

            if (index == 0) {
                expression[nextExpressionEndIndex++] = num.charAt(i);
                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);
            } else {
                expression[nextExpressionEndIndex++] = num.charAt(i);

                expression[expressionEndIndex] = '+';
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);

                expression[expressionEndIndex] = '-';
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression, nextExpressionEndIndex, result);

                expression[expressionEndIndex] = '*';
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression, nextExpressionEndIndex, result);
            }
        }
    }
}
