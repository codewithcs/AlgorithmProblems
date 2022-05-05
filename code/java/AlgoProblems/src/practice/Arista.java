package practice;

public class Arista {
    /*
    Given a string to reformat. The string consists of N characters of letters, digits, spaces, and/or dashes.
    The string always contains at least 2 alphanumeric characters.
    "Spaces and dashes in the string should be ignored."
    We want to reformat the string so that the characters
    are grouped in blocks of three, separated by single spaces.
    If necessary, the final block or the last 2 blocks can be of length 2.
 */

    public static void main(String[] args) {
        // Pre-processing: Remove the dashes and spaces.
        String one = "ABCDEF";
        String two = "ABCDE";
        String three = "ABCD";

        System.out.println(convertString(one));
        System.out.println(convertString(two));
        System.out.println(convertString(three));

        String four = "     A 234B 4  56C DEFG1  23---    ";
        four = four.replaceAll("[\\s\\-()]", "");
        four = four.replaceAll("\\s+","");
        System.out.println("four is : " + four);
        System.out.println(convertString(four));
    }

    public static String convertString(String input){
        if(input.length() == 2 || input.length() == 3) return input;

        int length = input.length(); StringBuilder output = new StringBuilder();

        if(length %3 == 0 ){
            for(int i=0 ; i<length; i=i+3){
                output.append(input, i, i + 3).append(" ");
            }

        }else if(length % 3 == 2){
            String x = input.substring(length-2, length) ;

            for(int i=0; i<=length-3 ; i=i+3){
                output.append(input, i, i + 3).append(" ");
            }

            output.append(x);

        } else if(length % 3 == 1){
            String x = input.substring(length-4, length-2)+ " " +input.substring(length-2, length) ;

            for(int i=0; i<=length-5 ; i=i+3){
                System.out.println("i is : " + i);
                output.append(input, i, i + 3).append(" ");
            }

            output.append(x);
        }

        return output.toString().trim();
    }
}
