package LeetCodePremium.Amazon;

/*
Convert a non-negative integer to its english words representation.
Given input is guaranteed to be less than 231 - 1.

 */
public class IntegerToEnglishWords {

    // O(n) time | O(1) space
    public static void main(String[] args) {
        System.out.println("Word is : " + numberToWords(123));
    }

   static String[] lessthan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve"
            , "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
   static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
   static String[] thousand = { "" , "Thousand", "Million", "Billion" };

    public static String numberToWords(int num) {
        if(num == 0) return "Zero";
        String s = "";
        int i=0;

        while(num >0) {
            if(num %1000 != 0) // to handle edge case of 1,000,000 : We want to print 1 Million and not 1 Million Thousand.
                s = helper(num%1000) +  thousand[i] + " "  + s; // the space before s is to separate 2 different 3 digits.
            num = num/1000;
            i++;
        }
        // concatentation above won't be expensive as we dealing with string array of constant size.
        // Difficult to optimize with String Builder.
        // String Builder, insert() takes O(N)

        return s.trim(); // For the edge Case 123.
    }

    // Decompose 3 digit number. Think in mind about separating space for different 3 digit numbers, then things will be clear.
    public static String helper(int num){ /// 2 base cases.
        if(num == 0){ // For the edge case, 50868.
            return ""; // base case
        } else if(num < 20){
            return lessthan20[num] + " "; // base case
        } else if(num <100 ){
            return tens[num/10] + " " + helper(num%10) ;
        } else {
            return lessthan20[num/100] + " Hundred " + helper(num%100);
        }
    }
}
