package LeetCode.Contest261;

/*
The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet,
so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.

The numeric value of a string consisting of lowercase characters is defined as the
sum of its characters' numeric values. For example, the numeric value of the string "abe"
is equal to 1 + 2 + 5 = 8.

You are given two integers n and k. Return the lexicographically smallest string with length equal
to n and numeric value equal to k.

Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order,
that is, either x is a prefix of y, or if i is the
first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order

Constraints:
1 <= n <= 105
n <= k <= 26 * n

Input: n = 3, k = 27
Output: "aay"

n=5, k=73 => "aaszz"

 */

public class String1 {
    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int value = k;
        int length = 0;

        while(length < n){

            int remainingValue = k - 26;
            if(remainingValue == 1 ){

            }

        }


        while(value > 26 ){
            sb.append("z");
            length++;
            value -= 26;
        }

        if(value > 1){
            char current = (char) ( (char)(value-1 + 48) + '0');
            sb.append(current);
        }

        sb.append("a");

        return sb.reverse().toString();
    }
}
