package LeetCode.Google.ArrayAndString;

import java.util.ArrayList;
import java.util.List;

/*
Given two non-negative integers num1 and num2 represented as strings,
return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs
to integer directly.

Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
       // System.out.println(multiply("123456789", "987654321"));
        System.out.println(multiply2("999", "0"));
    }

    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";

        int length = num2.length();
        List<List<Integer>> list = new ArrayList<>();

        for(int i=1; i<=length ; i++){
            List<Integer> newList = new ArrayList<>();
            newList.add(0);
            list.add(newList);
        }
        int listIndex = 0;

        for(int i=num2.length()-1; i >=0 ; i--, listIndex++){
            if(listIndex > 0){
                int amount = listIndex;
                for(int a = 1; a<=amount ; a++){
                    list.get(listIndex).add(0);
                }
            }
            int carry = 0;
            for(int j= num1.length()-1; j>=0 ; j--){
                int first = num2.charAt(i) - '0';
                int second = num1.charAt(j) - '0';
                int third = (first * second + carry)%10;
                list.get(listIndex).add(0, third);
                carry = (first * second + carry)/10;
            }

            if(carry > 0){
                list.get(listIndex).add(0, carry);
            }
        }

        int maxLength = -1;
        for(List<Integer> current : list){
            maxLength = Math.max(current.size(), maxLength);
        }

        for(List<Integer> current: list){
            int diff = maxLength - current.size();
            for(int i=1; i<= diff ; i++){
                current.add(0,  0);
            }
        }

        int finalCarry = 0;  StringBuilder sb = new StringBuilder();
        for(int i=list.get(0).size() - 1; i>=0 ; i--){
            int total = 0;
            for(List<Integer> current : list){
                total += current.get(i);
            }
            total += finalCarry;
            sb.append(total%10);
            finalCarry = total/10;
        }

        if(finalCarry != 0){
            sb.append(1);
        }

        return sb.reverse().substring(0, sb.length()-1).toString();
    }

    // Interesting solution: We can separate multiplication into additions.
    public static String multiply2(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }

        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : products) {
            sb.append(num);
        }

        while (sb.length() != 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
