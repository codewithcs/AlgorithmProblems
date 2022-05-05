package LeetCode.Medium.JumpGame;

import java.util.*;

/*
You are given a 0-indexed binary string s and
two integers minJump and maxJump.
In the beginning, you are standing at index 0,
which is equal to '0'.

You can move from index i to index j
if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.

Return true if you can reach index s.length - 1 in s, or false otherwise

https://leetcode.com/problems/jump-game-vii/discuss/1224667/Java-2-Solutions-TreeSet-O(nlogn)-and-Deque-O(n)
 */
public class JumpGame7 {

    // TreeSet solution.
    /*
    TreeSet solution can be categorized under the tag "greedy" where we are looking for first
    zero which is atleast minJump apart from the current index.
    Same can be done using for loop (which will take O(n) for searching) but to improve the
    complexity I have used TreeSet (O(logn) for searching).
     */

    /*
    Explanation
    dp[i] = true if we can reach s[i].
    pre means the number of previous position that we can jump from.

    Complexity
    Time O(n)
    Space O(n)
     */

    //  DP + Sliding Window
    /*
        Here comes the explanation!
        It's a bottom-up DP implementation. The boolean value represents whether this position is reachable from start.
        So the first step is to generate the table.
        Here the table was pre-labeled True or False, thus '1's are already labeled False.
        To determine the state of dp[i], one need to check the states in window dp[i-maxJ : i-minJ],
        because any one of them can reach i if it's labeled True.

        Then you need to check if there is a True in this window. Notice that this is a sliding window problem,
        so you don't need to calculate it every time. You only need to remove the effect from dp[i-maxJ-1] and
        add the dp[i-minJ]. This is done by these two lines of code pre += dp[i - minJ] and pre -= dp[i - maxJ - 1]
        The if statements if i >= minJ: and if i > maxJ: are dealing with the initial boundary.
        The brilliance of this algorithm is combining the sliding window to DP, hope you enjoy it.
     */

    // BFS solution.
    // We use the farthest variable so that we don't add same index twice in the queue.

    // Constraints:
    // 2 <= s.length <= 10^5
    // s[i] is either '0' or '1'.
    // s[0] == '0'
    // 1 <= minJump <= maxJump < s.length

    // O(n) space and time complexity.
    public boolean canReach(String s, int minJump, int maxJump) {
        if(s.charAt(s.length()-1) == '1'){
            return false;
        }

        int n = s.length();
        int farthest = 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);

        while(!queue.isEmpty()){
            int currentIndex = queue.poll();

            if(visited[currentIndex]) continue;

            visited[currentIndex] = true;

            int min = currentIndex + minJump;
            int max = currentIndex + maxJump;

            if(min > s.length()-1) continue;

            min = Math.max(min, farthest+1);
            max = Math.min(max, s.length()-1);

            for(int i = min; i <= max ; i++){
                if(i>=n) break;
                if(s.charAt(i) == '0'){
                    queue.add(i);
                }
            }

            farthest = max;
        }

        return visited[n-1];
    }

    public boolean canReachBFS2(String s, int minJump, int maxJump) {
        int n = s.length();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int farthest = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int start = Math.max(curr + minJump, farthest + 1);

            for (int j = start; j < curr + maxJump + 1; j++) {
                if (j >= n) {
                    break;
                }

                if (s.charAt(j) == '0') {
                    if (j == n - 1){
                        return true;
                    }
                    queue.offer(j);
                }
            }

            farthest = curr + maxJump;
        }

        return false;
    }



    public boolean canReach1(String s, int minJ, int maxJ) {
        int n = s.length(), pre = 0;

        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; ++i) {
            if (i >= minJ && dp[i - minJ]) {
                pre++;
            }
            if (i > maxJ && dp[i - maxJ - 1]) {
                pre--;
            }

            dp[i] = pre > 0 && s.charAt(i) == '0';
        }

        return dp[n - 1];
    }


    public boolean canReach2(String s, int minJump, int maxJump) {
        int i = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);

        while(++i < s.length()){
            if(s.charAt(i) == '0'){
                Integer lower = ts.floor(i-minJump);
                if(lower != null && lower+maxJump >= i){
                    ts.add(i);
                }
            }
        }

        return ts.last() == s.length()-1;
    }

    public boolean canReach3(String s, int minJump, int maxJump) {
        int i = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        while(++i < s.length()){

            while(!q.isEmpty() && q.peek() < i-maxJump){
                q.poll();
            }
            if(s.charAt(i) == '0' && !q.isEmpty() && q.peek() <= i-minJump){
                q.add(i);
            }
        }

        return !q.isEmpty() && q.peekLast() == s.length()-1;
    }



    // Wrong Answer:
    /*
        "011001110001000"
        3
        5
     */
    // Wrong Solution
    public boolean canReach4(String s, int minJump, int maxJump) {
        int newStartIndex = 0;
        //  boolean[] reach = new boolean[s.length()];
        // reach[0] = true;

        if(s.charAt(s.length()-1) == '1') {
            return false;
        }

        for(int i=0; i< s.length(); ){
            if(s.charAt(i) == '1'){
                i++;
                continue;
            }

            if(i + minJump > s.length()-1){
                break;
            }

            int min = Math.min( i + minJump, s.length() - 1 ) ;
            int max = Math.min( i + maxJump, s.length() - 1 ) ;

            System.out.println("Min is : " + min);
            System.out.println("Max is : " + max);
            System.out.println();

            for(int k=max; k>= min ; k--){
                if(s.charAt(k) == '0'){
                    if(k == s.length() - 1){
                        return true;
                    }

                    newStartIndex = k ;
                }
            }

            if(i == newStartIndex) break;

            i = newStartIndex;
        }

        return false;
    }
}
