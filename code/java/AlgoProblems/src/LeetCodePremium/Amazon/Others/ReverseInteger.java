package LeetCodePremium.Amazon.Others;

/*
Given a 32-bit signed integer, reverse digits of an integer.
Note:
Assume we are dealing with an environment that could only store
integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Do not use long since we  are only allowed to use 32-bit int.
 */

public class ReverseInteger {

    //best solution.
    public int reverse(int x) {
        boolean negativeFlag = false;
        int num = x;

        if (num < 0) {
            negativeFlag = true;
            num = -num ;
        }

        int prev_reverse = 0;
        int reverse = 0;

        while (num != 0){
            int current = num % 10;
            reverse = (reverse*10) + current;

            // checking if the reverse overflowed or not.
            // The values of (rev_num - curr_digit)/10 and
            // prev_rev_num must be same if there was no
            // problem.
            if ((reverse - current)/10 != prev_reverse) {
                return 0;
            }

            prev_reverse = reverse;
            num = num/10;
        }

        return (negativeFlag == true)? -reverse : reverse;
    }

    // O(log(x)), Roughly log10(x) digits in x.
    // Space: O(1)
    // 2147483647
    // -2147483648
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
