package LeetCodePremium.Twitter;

import java.util.List;

public class q2 {
    public static int fountainActivation(List<Integer> a) {
        int n = a.size();

        int interval[] = new int[n];

        for (int i = 1; i <= n; i++) {
            int left = Math.max(i - a.get(i-1), 1);
            int right = Math.min(i + a.get(i-1), n);
            interval[left - 1] = Math.max(interval[left - 1], right);
        }

        int right = interval[0];
        int nextGreaterRight = right;
        int fountainsActivate = 1;

        for (int i = 1; i < n; i++) {
            nextGreaterRight = Math.max(nextGreaterRight, interval[i]);
            if (i == right) {
                fountainsActivate++;
                right = nextGreaterRight;
            }
        }
        return fountainsActivate;
    }


}
