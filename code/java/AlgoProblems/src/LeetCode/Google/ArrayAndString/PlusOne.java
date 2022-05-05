package LeetCode.Google.ArrayAndString;

import java.util.ArrayList;
import java.util.List;

/*
Given a non-empty array of decimal digits representing a non-negative integer,
increment one to the integer.

The digits are stored such that the most significant digit is at the
head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except
the number 0 itself.

Constraints:
1 <= digits.length <= 100
0 <= digits[i] <= 9
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] answer = plusOne(new int[]{ 9, 9, 9 });
    }

    // O(n) time and O(n) space.
    public static int[] plusOne(int[] digits) {
        int carry = 0;
        int value = carry + ++digits[digits.length-1];
        carry = value /10;
        if(carry == 0){
            return digits;
        }

        List<Integer> answer = new ArrayList<>(); answer.add(0); // dummy node.
        answer.add(0, value%10);

        for( int i=digits.length-2; i>=0 ; i--){
            value = carry + digits[i];
            carry = value /10;
            answer.add(0, value%10);
        }

        if(carry == 1){
            answer.add(0, 1);
        }

        answer.remove(answer.size()-1); // remove the dummy node as we don't need it in the final answer.
        int[] output = new int[answer.size()];
        for(int j=0 ; j<output.length; j++){
            output[j] = answer.get(j);
        }

        return output;
    }

    // We can do this in O(1) space.
    public int[] plusOne2(int[] digits) {
        int n = digits.length;

        // move along the input array starting from the end
        for (int idx = n - 1; idx >= 0; --idx) {
            // set all the nines at the end of array to zeros
            if (digits[idx] == 9) {
                digits[idx] = 0;
            }
            // here we have the rightmost not-nine
            else {
                // increase this rightmost not-nine by 1
                digits[idx]++;
                // and the job is done
                return digits;
            }
        }

        // We are here because all the digits are nines
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

}
