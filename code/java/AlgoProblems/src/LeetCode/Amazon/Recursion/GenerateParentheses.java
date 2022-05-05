package LeetCode.Amazon.Recursion;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
1 <= n <= 8
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        int L = 1;
        int R = 0;
        List<String> list = new ArrayList<>();
        return helper(list, L, R, n, "(");
    }

    // We only generate valid parentheses and never go for a invalid combination.
    public List<String> helper(List<String> list, int L, int R, int n, String s){
        if(L > R && L < n ) {
            helper(list, L+1, R, n, s + '(');
            helper(list, L, R+1, n, s + ')');
        }  else if(L < n && L == R){
            helper(list, L+1, R, n, s + '(' );
        } else if(L == n && L > R){
            helper(list, L, R+1, n, s + ')');
        } else if( L ==n && L== R){
            list.add(s);
        }
        return list;
    }

    // Brute Force Approach: Generating all possible combinations
    public List<String> generateParenthesis2(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

    // Approach 3: Another Backtracking Approach:
    public List<String> generateParenthesis3(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }

    // Approach 4: Closure Number
    public List<String> generateParenthesis4(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis4(c))
                    for (String right: generateParenthesis4(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
