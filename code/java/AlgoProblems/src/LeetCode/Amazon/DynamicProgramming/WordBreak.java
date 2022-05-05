package LeetCode.Amazon.DynamicProgramming;

import java.util.*;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence
of one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate word
 */
public class WordBreak {
    // Base Case: Single length characters.
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[][] answer = new boolean[s.length()][s.length()];

        // Base Case
        for(int i=0; i<s.length(); i++){
            if(wordDict.contains(s.substring(i, i+1))){
                answer[i][i] = true;
            }
        }

        int length = 2; int stringLength = s.length()-1;
        int i=0; int j= length-1;

        while(length <= s.length()){

            i=0; j=length-1;

            while(i<stringLength && j<s.length()){

                if(wordDict.contains(s.substring(i, j+1))){
                    answer[i][j] = true;
                } else {
                    for(int k=1; k<=j; k++){
                        if(!answer[i][j]){
                            answer[i][j] = answer[i][k-1] && answer[k][j];
                        } else {
                            break;
                        }
                    }
                }
                i++ ; j++;
            }

            length++; stringLength--;
        }

        return answer[0][answer.length-1];
    }

    // DP - method 2.
public boolean workdBreak2(String s, List<String> wordDict){
    Set<String> wordDictSet=new HashSet(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}

    // Naive Approach.
    public boolean wordBreak3(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

    // Recursion With Memoization
    public boolean wordBreak4(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    // Using Breadth First Search
    public boolean workdBreak5(String s, List<String> wordDict){
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }




}
