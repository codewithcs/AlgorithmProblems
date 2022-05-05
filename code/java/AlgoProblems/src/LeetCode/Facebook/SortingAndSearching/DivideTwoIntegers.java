package LeetCode.Facebook.SortingAndSearching;

import java.util.ArrayList;

/*
Given two integers dividend and divisor, divide two integers without
using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part.
For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note:
Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
For this problem, assume that your function returns 231 − 1 when the division result overflows.

Constraints:
-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE/-1);
        System.out.println(1 << 31);
        System.out.println(1 << 32);
        System.out.println(1 << 31 << 1);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE/2);
    }

    // Time Limit Exceeded.
    public int divide(int dividend, int divisor) {
        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives
         * for the reasons explained above.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        /* Count how many times the divisor has to be added
         * to get the dividend. This is the quotient. */
        int quotient = 0;
        while (dividend - divisor <= 0) {
            quotient--;
            dividend -= divisor;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }

    // O(logN^2) time
    public int divide2(int A, int B) {
        if (A == 1 << 31 && B == -1) { // 1<<31 gives Integer.MIN_VALUE in Java.
            return (1 << 31) - 1;
        }
        int a = Math.abs(A);
        int b = Math.abs(B);
        int res = 0;
        int x = 0;

        while (a - b >= 0) {
            x = 0;
            while(a - (b << x << 1) >= 0){ // << is solved Left To Right.
                x++;
            }
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }

    // Best solution. O(32) time.
    public int divide3(int A, int B) {
        if (A == 1 << 31 && B == -1) {
            return (1 << 31) - 1; // Integer.MIN_VALUE-1 gives Integer.MAX_VALUE.
        }
        int a = Math.abs(A); // Here using positive numbers work as we are doing any negation operations.
        int b = Math.abs(B);
        int res = 0;

        for (int x = 31; x >= 0; x--) {
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        }
        return (A > 0) == (B > 0) ? res : -res;
    }


    // Repeated Exponential Searches. O(logN^2) time and O(1) space.
    private static int HALF_INT_MIN = -1073741824;

    public int divide4(int dividend, int divisor) {
        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while (divisor >= dividend) {
            /* We know it'll fit at least once as dividend >= divisor.
             * Note: We use a negative powerOfTwo as it's possible we might have
             * the case divide(INT_MIN, -1). */
            int powerOfTwo = -1;
            int value = divisor;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }
            // We have been able to subtract divisor another powerOfTwo times.
            quotient += powerOfTwo;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= value;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }

    // Adding Powers of Two
    //private static int HALF_INT_MIN = -1073741824;// -2**30;
    // O(logN) time and O(logN) space.
    public int divide5(int dividend, int divisor) {
        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        ArrayList<Integer> doubles = new ArrayList<>();
        ArrayList<Integer> powersOfTwo = new ArrayList<>();

        /* Nothing too exciting here, we're just making a list of doubles of 1 and
         * the divisor. This is pretty much the same as Approach 2, except we're
         * actually storing the values this time. */
        int powerOfTwo = -1;
        while (divisor >= dividend) {
            doubles.add(divisor);
            powersOfTwo.add(powerOfTwo);
            // Prevent needless overflows from occurring...
            if (divisor < HALF_INT_MIN) {
                break;
            }
            divisor += divisor;
            powerOfTwo += powerOfTwo;
        }

        int quotient = 0;
        /* Go from largest double to smallest, checking if the current double fits.
         * into the remainder of the dividend. */
        for (int i = doubles.size() - 1; i >= 0; i--) {
            if (doubles.get(i) >= dividend) {
                // If it does fit, add the current powerOfTwo to the quotient.
                quotient += powersOfTwo.get(i);
                // Update dividend to take into account the bit we've now removed.
                dividend -= doubles.get(i);
            }
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }
}
