package LeetCodePremium.Medium;

import java.util.Stack;

/*
Given a list of daily temperatures T, return a list such that,
for each day in the input,
tells you how many days you would have to wait until a warmer temperature.
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30,000].
Each temperature
will be an integer in the range [30, 100].
 */
public class DailyTemperatures {

    // Brute Force: O(n^2) time
    public int[] dailyTemperatures(int[] T) {
        int[] output = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            if (T[i] < T[i + 1]) {
                output[i] = 1;
            } else {
                for (int j = i + 1; j < T.length; j++) {
                    if (T[j] > T[i]) {
                        output[i] = j - i;
                        break;
                    }
                }
            }
        }

        return output;
    }

    // Iterate Backwards.
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    // Thinking about jumps. Nice use of keeping global maximum value.
    public int[] dailyTemperatures4(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        res[n - 1] = 0;

        int max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; --i) {
            if (T[i] >= max) { // just set max and by default the answer for this index will be 0.
                max = T[i];
            } else { // If my current element is less than the max, then it is guaranteed that there will be a solution for this element.
                int index = i + 1;
                while (T[i] >= T[index]) {
                    index += res[index];
                }
                res[i] = index - i;
            }
        }
        return res;
    }

    // Iterate forwards.
    public int[] dailyTemperatures3(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>(); // Make it a stack of indices.
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }
}
