package LeetCodePremium.Amazon.ArrayAndStrings;

public class IntegerToRoman {
    public String intToRoman(int num) {
        String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" } ; // [1, 9]
        String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX","LXX", "LXXX", "XC" } ; // [10, 90]
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // [100, 900]
        String[] thousands = {"", "M", "MM", "MMM"}; // [1000, 3000]
        StringBuilder string= new StringBuilder();
        return  string.append(thousands[num / 1000]).append( hundreds[num % 1000 / 100]).append( tens[num % 100 / 10] ).append( ones[num % 10]).toString();
    }

    // O(1) time and space, faster using String Builder.
}
