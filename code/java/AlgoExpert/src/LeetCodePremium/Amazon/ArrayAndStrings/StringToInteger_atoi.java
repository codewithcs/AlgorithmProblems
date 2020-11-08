package LeetCodePremium.Amazon.ArrayAndStrings;

public class StringToInteger_atoi {

    // O(N) time : 1 iteration over the string and O(1) space.
    public int myAtoi(String str) {
        int i = 0;
        int sign = 1; // +ve
        int result = 0;
        if (str.length() == 0) {
            return 0;
        }

        //Discard whitespaces in the beginning
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        // Check if optional sign if it exists
        if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-')){
            sign = (str.charAt(i++) == '-') ? -1 : 1;
        }

        // Build the result and check for overflow/underflow condition
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {

            // Check Overflow
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + (str.charAt(i++) - '0'); // logic similar to reversing a number.
        }

        return result * sign;

    }
}
