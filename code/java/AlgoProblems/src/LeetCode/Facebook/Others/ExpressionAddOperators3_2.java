package LeetCode.Facebook.Others;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators3_2 {

    // Time Complexity ?
    public List<String> addOperators(String num, int target) {
        List<String> expressions = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return expressions;
        }
        addOperators(num, target, 0, 0, 0, new StringBuilder(), expressions);
        return expressions;
    }

    private void addOperators(String num, int target, int index, long currentValue, long lastValue, StringBuilder expression, List<String> result) {

        if (index == num.length()) {
            if (currentValue == target) {
                result.add(expression.toString());
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            if (i != index && num.charAt(index) == '0') {
                break;
            }

            long currentDigitsValue = Long.parseLong(num.substring(index, i + 1));
            int len = expression.length();

            if (index == 0) {
                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression.append(currentDigitsValue), result);
                expression.setLength(len); // This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

            } else {

                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression.append("+").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.
                // Interesting technique with string builder

                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression.append("-").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression.append("*").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.
            }
        }
    }
}
