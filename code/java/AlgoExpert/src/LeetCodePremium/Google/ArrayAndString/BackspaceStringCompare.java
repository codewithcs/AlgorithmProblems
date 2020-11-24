package LeetCodePremium.Google.ArrayAndString;

import java.util.Stack;

/*
Given two strings S and T, return if they are equal when both are typed
into empty text editors. # means a backspace character.

Note that after backspacing an empty text,
the text will continue empty.

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

Follow up:
Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
    }

    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for(char c: S.toCharArray()){
            if(c== '#' && !stack1.isEmpty()){
                stack1.pop();
            } else if (c!= '#'){
                stack1.push(c);
            }
        }

        for(char c: T.toCharArray()){
            if(c== '#' && !stack2.isEmpty()){
                stack2.pop();
            } else if (c!= '#'){
                stack2.push(c);
            }
        }

        String s1 = ""; String s2 = "";
        while(!stack1.isEmpty()){
            s1 += stack1.pop();
        }
        while(!stack2.isEmpty()){
            s2 += stack2.pop();
        }

        System.out.println("s1 is : " + s1);
        System.out.println("s2 is : " + s2);

        return s1.equals(s2);
    }
}
