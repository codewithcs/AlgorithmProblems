package LeetCodePremium.Facebook.DP;

import java.util.Arrays;

public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("123"));
    }

    public static int numDecodings(String s) {
        if(s.charAt(0) == '0') return 0;

        int decodings = 0;
        int index = 0;
        int[] cache = new int[s.length()]; // cache[index] represents decodings for the substring starting at the index.
        Arrays.fill(cache, -1);

        decodings += helper(index, s, cache) ;

        return decodings;
    }

    public static int helper(int index, String s, int[] cache){
        if(index >= s.length()){
            return 1;
        }

        if(cache[index] != -1){
            return cache[index];
        }

        int decodings = 0;

        for(int i=1; i<=2; i++){
            if(index + i<= s.length()){
                if(isValidString(s.substring(index, index+i))){
                    decodings += helper(index+i, s, cache);
                }
            }
        }

        cache[index] = decodings;

        return decodings;
    }
    public static boolean isValidString(String s){
        if(s.charAt(0) == '0' || s.isEmpty()) { // To handle "0" and "0k"
            return false;
        }

        int value = Integer.parseInt(s);
        if(value <= 0 || value >= 27){
            return false;
        }

        return true;
    }
}
