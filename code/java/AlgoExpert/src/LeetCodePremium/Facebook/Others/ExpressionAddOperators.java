package LeetCodePremium.Facebook.Others;

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
        System.out.println(addOperators("1234", 1));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        String current = "";
        String[] operators = { "", "+", "-", "*" };
        generateString(0, num, current, operators);
        return result;
    }

    public static void generateString(int index, String num, String current, String[] operators){
        if(index == num.length() -1){
            current += (num.charAt(num.length()-1));
            System.out.println("current is : " + current);
            return;
        }

        for(int i=index ; i< num.length()-1; i++){
            String original = current;
            for(String operator: operators){
                current += (num.charAt(i) + operator);
                generateString(i+1, num, current, operators);
                current = new String(original);
            }
        }
    }

    public boolean checkExpression(String expression){
        return true;
    }
}
