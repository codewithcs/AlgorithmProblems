package LeetCodePremium.Amazon.Others;

/*
Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the
squares of its digits, and repeat the process until the number equals 1 (where it will stay),
or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int num = n;
        while(n>0){
            num = calculateSquareOfDigits(num);
            if(set.contains(num)) return false;
            if(num == 1){
                return true;
            }
            set.add(num);
            n--;
        }

        return false;
    }

    public int calculateSquareOfDigits(int number){
        List<Integer> list = new ArrayList<>();

        while(number > 0){
            list.add(number%10);
            number = number/10;
        }

        int square = 0;

        for(int digit: list){
            square += digit*digit;
        }

        return square;
    }

    // Approach 2: Compare with Approach 1 and see how cleaner this is.
    // Write Clean Code.
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
