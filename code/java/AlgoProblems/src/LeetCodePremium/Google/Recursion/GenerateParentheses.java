package LeetCodePremium.Google.Recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all
combinations of well-formed parentheses.

 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        traverse(answer, "(", 1, 0, n);
        return answer;
    }

    public void traverse(List<String> answer, String current, int leftCount, int rightCount, int n){
        if(leftCount == rightCount){
            if(leftCount < n){
                traverse(answer, current + "(", leftCount+1, rightCount, n);
                traverse(answer, current+")" , leftCount, rightCount+1 , n);
            } else if(leftCount == n){
                answer.add(current);
            }
        } else if(leftCount == n){
            traverse(answer, current+ ")", leftCount, rightCount+1, n);
        } else if(leftCount > rightCount){
            traverse(answer, current + "(", leftCount+1, rightCount, n);
            traverse(answer, current+")" , leftCount, rightCount+1 , n);
        }
    }

    // f(n) = (f(0))f(n-1) + (f(1))f(n-2) + ... + (f(n-2))f(1) + (f(n-1))f(0)
    public List<String> generateParenthesis2(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }

            lists.add(list);
        }

        return lists.get(lists.size() - 1);
    }
}
