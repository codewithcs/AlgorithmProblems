package LeetCodePremium.Google.ArrayAndString;

import javafx.util.Pair;

/*
Given two non-negative integers num1 and num2 represented as string,
return the sum of num1 and num2.

Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to
integer directly.
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        if(num1.length() < num2.length()){
            int diff = num2.length() - num1.length();
            for(int i=1; i<=diff; i++){
                num1 = "0" + num1;
            }
        }

        if(num1.length() > num2.length()){
            int diff = num1.length() - num2.length();
            for(int i=1; i<=diff; i++){
                num2 = "0" + num2;
            }
        }
        String answer = "" ;
        int lastIndex = num1.length()-1;
        int carry = 0;

        while(lastIndex >= 0){
            int first = num1.charAt(lastIndex) - '0';
            int second = num2.charAt(lastIndex) - '0';
            int third = (first+second+carry)%10;
            if(first+second+carry >9){
                carry = 1;
            } else {
                carry = 0;
            }

            answer = third + answer;
        }


        return carry == 0 ? answer : "1" + answer;
    }

    // Better: Using String Builder in these situations.
    public String addStrings2(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;
        }

        if (carry != 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }
}
