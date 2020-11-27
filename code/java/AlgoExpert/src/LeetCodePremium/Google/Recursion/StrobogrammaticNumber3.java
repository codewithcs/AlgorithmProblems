package LeetCodePremium.Google.Recursion;

import java.util.HashMap;
import java.util.Map;

/*
A strobogrammatic number is a number that looks the same when
rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers
that exist in the range of low <= num <= high.

Note:
Because the range might be a large number, the low and high
numbers are represented as string.
 */
public class StrobogrammaticNumber3 {
    private static char[][] PAIRS = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
        int cnt = 0;
        for (int i = low.length(); i <= high.length(); ++i) {
            cnt += dfs(low, high, new char[i], 0, i - 1);
        }
        return cnt;
    }

    private int dfs(String low, String high, char[] c, int l, int r) {
        if (l > r) {
            String s = new String(c);
            if (c.length == low.length() && s.compareTo(low) < 0 ||
                    c.length == high.length() && s.compareTo(high) > 0) {
                return 0;
            } else {
                return 1;
            }
        }
        int cnt = 0;
        for (char[] pair : PAIRS) {
            c[l] = pair[0];
            c[r] = pair[1];
            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (l == r && pair[0] != pair[1]) {
                continue;
            }
            cnt += dfs(low, high, c, l + 1, r - 1);
        }
        return cnt;
    }

    // Approach 2: O(logN) ?
    public int strobogrammaticInRange2(String low, String high) {
        if (Long.parseLong(low) > Long.parseLong(high)) {
            return 0;
        }
        return strobogrammaticSmallerThan(String.valueOf(Long.parseLong(high) + 1)) - strobogrammaticSmallerThan(low);
    }

    int[] five = new int[]{0, 1, 6, 8, 9};
    int[] three = new int[]{0, 1, 8};
    int[] four = new int[]{1, 6, 8, 9};

    private int strobogrammaticSmallerThan(String upper) {
        if (upper.length() == 1) {
            return countNumSmallerThanCurDigits(upper, 0, five);
        }
        return strobogrammaticSmallerThanLenOfUpper(upper) + strobogrammaticEqualToLenOfUpper(upper);
    }

    // O(M)
    private int strobogrammaticSmallerThanLenOfUpper(String upper) {
        int[] count = new int[upper.length()];
        count[0] = 1;
        count[1] = 3;
        int smallerLenSum = 0;
        // Case 1: length == 1: 0 1 8
        if (upper.length() > 1) {
            smallerLenSum += 3;
        }
        // Case 2: length > 1:
        // We construct the strobogrammatic number from smaller length to upper.length() - 1
        for (int i = 2; i < upper.length(); i++) {
            // the middle digits of smallerLenSum can be [0, 1, 6, 8, 9]
            count[i] = 5 * count[i - 2];
            // The leading digits of smallerLenSum can only be [1, 6, 8, 9]
            smallerLenSum += 4 * count[i - 2];
        }
        return smallerLenSum;
    }


    /*

  O(M)
  e.g. We have upper = 9 6 1 7 4 5 6 7

  1. for the first digit we can use: [0, 1, 6, 8] to make sure the number generated is strictly less than upper
  2. for the second digit we have:
    case 1: [0, 1, 6, 8] we can choose all 5 number as second digit so 4 * 5
    case 2: 9 we can use [0, 1] to make sure the number generated is strictly less than upper
    --
    so for second digit, we have 4 * 5 + 2 that strictly less than and 1 choice(96) could equal to upper
  3. for the third digit we have
    case 1: for the above 4*5+2 choices of first two digits, we can choose all number as third digit so (4*5+2) *5
    case 2: for the only one choice: 96 of first two digits, we can choose 0 to make sure that the generated number is less than upper, and still we have 961 that may equal to upper
    --
    so for third digit, we have (4 * 5 + 2) * 5 + 1 that strictly less than and 1 choice(961) could equal to upper

  4. for the forth, we can use [0, 1, 6] and cannot keep the equal case again

  What if the forth element is one of [0, 1, 6, 8, 9]?
  We need to still consider the equal case and we need to add 1 if upper(9710XXXX) is larger than 96100196

    */
    private int strobogrammaticEqualToLenOfUpper(String upper) {
        // we construct it from first digit(significant bit) to the mid digit

        // the first digit can only be choosen from [1, 6, 8, 9]
        int equalLenSum = countNumSmallerThanCurDigits(upper, 0, four);
        // check if the current digit is not 0, 1, 6, 8 or 9
        boolean same = upper.charAt(0) > '1' && upper.charAt(0) < '8' && upper.charAt(0) != '6' ? false : true;

        // the [1, upper.length()/2) can be choosen from [0, 1, 6, 8, 9]
        for (int i = 1; i < upper.length() / 2; i++) {
            equalLenSum *= 5;
            if (!same) {
                continue;
            }
            equalLenSum += countNumSmallerThanCurDigits(upper, i, five);
            if (upper.charAt(i) > '1' && upper.charAt(i) < '8' && upper.charAt(i) != '6') {
                same = false;
            }
        }

        // the mid digit can be choosen from [0, 1, 8]
        if (upper.length() % 2 == 1) {
            equalLenSum *= 3;
            if (same) {
                equalLenSum += countNumSmallerThanCurDigits(upper, upper.length() / 2, three);
                if (upper.charAt(upper.length() / 2) > '1' && upper.charAt(upper.length() / 2) != '8') {
                    same = false;
                }
            }
        }

        if (same) {
            if (upper.compareTo(getRotation(upper)) > 0) {
                equalLenSum++;
            }
        }
        return equalLenSum;
    }


    // get how many numbers in nums is smaller than ith digit in upper
    private int countNumSmallerThanCurDigits(String upper, int i, int[] nums) {
        int count = 0;
        for (int n : nums) {
            if (upper.charAt(i) - '0' <= n) {
                break;
            }
            count++;
        }
        return count;
    }

    // replace the second half of str with the rotation of the first half of str
    private String getRotation(String str) {
        int n = str.length();
        StringBuilder sb = new StringBuilder(str.substring(0, (n + 1)/2));
        for (int i = n/2 - 1; i >= 0; --i) {
            if (str.charAt(i) == '6') sb.append('9');
            else if (str.charAt(i) == '9') sb.append('6');
            else sb.append(str.charAt(i));
        }
        return sb.toString();
    }


}
