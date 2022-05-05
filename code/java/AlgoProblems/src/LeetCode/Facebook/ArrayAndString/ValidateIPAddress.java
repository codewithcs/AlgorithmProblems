package LeetCode.Facebook.ArrayAndString;

import java.util.regex.Pattern;

public class ValidateIPAddress {
    // Different from "Restore IP Address" question
    //Constraints:
    //IP consists only of English letters, digits and the characters '.' and ':'.

    // Divide and Conquer approach.
    // The address is valid if and only if each of the chunks is valid.
    // O(n) time because to count number of dots requires to parse the entire input string.
    // O(1) space.
    public String validIPAddress(String IP) {
        String[] IPv4 = IP.split("\\.", -1);
        String[] IPv6 = IP.split("\\:", -1);

        if(IPv4.length != 4 && IPv6.length != 8){
            return "Neither";
        }

        if(IPv4.length == 4 ) {
            for(String current: IPv4){
                if( current.length() == 0 || (current.charAt(0) == '0' && current.length()!= 1 ) || !current.matches("[0-9]\\d*") || current.length() > 4 || Integer.parseInt(current) > 255 ) {
                    return "Neither";
                }
            }

            return "IPv4";
        } else  {
            for(String current: IPv6){
                if(!current.matches("^[0-9A-Fa-f]+$") || current.length() > 4 ){
                    return "Neither";
                }
            }
            return "IPv6";
        }
    }

    public static void main(String[] args) {
        System.out.println("a.b.c.d.".split("\\.").length);
    }

    // Solution 2: O(1) time because the patterns to match have constant length and O(1) space.
    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    Pattern pattenIPv4 =
            Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

    String chunkIPv6 = "([0-9a-fA-F]{1,4})";
    Pattern pattenIPv6 =
            Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");

    public String validIPAddress2(String IP) {
        if (pattenIPv4.matcher(IP).matches()) return "IPv4";
        return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
    }

    // Solution 3:
    public String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String x : nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (! Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    }

    public String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }

    public String validIPAddress3(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(IP);
        }
        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        }
        else return "Neither";
    }
}
