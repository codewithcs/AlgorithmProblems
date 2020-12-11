package LeetCodePremium.Facebook.Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid.
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).
 */
/*
Do a pre-processing to find the number of invalid parentheses.
 */
public class RemoveInvalidParentheses {

    // Struggle, Failed Strategy, Can I improve this ?
    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses(")((((((((")); // "))))ff"
    }

    public static List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();
        int invalidParentheses = invalidParenthesesCount(s);

        if(invalidParentheses == 0){
            List<String> list = new ArrayList<>(); list.add(s);
            return list;
        } else if(invalidParentheses == s.length()){
            List<String> list = new ArrayList<>(); list.add("");
            return list;
        }

        traverse(result, new StringBuilder(s), s, 0, invalidParentheses);

        if(result.size() == 0){
            List<String> list = new ArrayList<>(); list.add("");
            return list;
        }

        return new ArrayList<String>(result);
    }

    public static int invalidParenthesesCount(String s){
        int left=0; int right = 0;
        for(int i=0; i<s.length() ; i++){
            if(s.charAt(i) == '('){
                left++;
            } else if(s.charAt(i) == ')'){
                if(left > 0){
                    left--;
                } else {
                    right++;
                }
            }
        }
        return left+right;
    }

    public static void traverse(Set<String> result, StringBuilder s, String original, int removed, int invalidParenthesesCount){
        int invalidParenthesesCurrent = invalidParenthesesCount(s.toString());
        if(invalidParenthesesCount == s.length()){
            return;
        }

        if(removed > invalidParenthesesCount) return;

        if(removed == invalidParenthesesCount && invalidParenthesesCurrent == 0){
            result.add(s.toString());
            return;
        }

        // Remove first character
        for(int i=0; i<s.length() ; i++){
            System.out.println("[BEFORE RECURSION] s is : " + s);
            char currentChar = s.charAt(i);
            if(currentChar != '(' && currentChar != ')'){
                continue;
            }
            StringBuilder beforeDeletion = new StringBuilder(s);
            s.deleteCharAt(i);
            traverse(result,  s, original, removed+1, invalidParenthesesCount);
            s = beforeDeletion;
            System.out.println("[AFTER RECURSION] s is : " + s);
            System.out.println();
        }
    }
}
