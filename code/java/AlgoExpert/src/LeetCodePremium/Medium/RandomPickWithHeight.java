package LeetCodePremium.Medium;

import java.util.Random;

/*
You are given an array of positive integers w where w[i]
describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns
an integer in the range [0, w.length - 1].
pickIndex() should return the integer proportional to its weight in the w array.

For example, for w = [1, 3], the probability of picking the
index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking
the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

Constraints:
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
 */
public class RandomPickWithHeight {

    int[] cumulativeSum;

    public RandomPickWithHeight(int[] w) {
        cumulativeSum = new int[w.length];
        cumulativeSum[0] = w[0];
        for (int i = 1; i < cumulativeSum.length; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        // Random random = new Random();
        double randomNumber = Math.random() * cumulativeSum[cumulativeSum.length - 1];
        int low = 0;
        int high = cumulativeSum.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (randomNumber > cumulativeSum[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
