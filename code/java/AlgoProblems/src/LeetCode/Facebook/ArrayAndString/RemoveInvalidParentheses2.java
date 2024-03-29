package LeetCode.Facebook.ArrayAndString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// Building the string by taking or not taking the current character.
// Not taking the character is same as deleting the character.
public class RemoveInvalidParentheses2 {

    // Approach 3: Limited Backtracking
    private Set<String> validExpressions = new HashSet<String>();

    private void recurse(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder expression) {
        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }
        } else {
            char character = s.charAt(index);

            // Take this character.
            expression.append(character);

            if (character != '(' && character != ')') { // Have to take and recurse for this character always.
                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
            } else if (character == '(') {
                // Consider an opening bracket.
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);
            } else if (rightCount < leftCount) {
                // Consider a closing bracket.
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }

            // Delete for backtracking.
            expression.deleteCharAt(expression.length()-1);

            // Don't take the character. We only delete the character if it is '(' or ')'.
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse( s, index + 1, leftCount, rightCount, leftRem - (character == '(' ? 1 : 0), rightRem - (character == ')' ? 1 : 0),
                        expression);
            }


        }
    }

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {
            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;
                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }
}
