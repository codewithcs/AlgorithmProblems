package LeetCodePremium.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given an array of integers A, a move consists of choosing any A[i],
and incrementing it by 1.

Return the least number of moves to make every value in A unique.

Twitter, Uber.

Note:
0 <= A.length <= 40000
0 <= A[i] < 40000
 */
public class MinimumIncrementToMakeArrayUnique {
    // Time Limit Exceeded. 
    public int minIncrementForUnique(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        Set<Integer> unique = new HashSet<>();
        Arrays.sort(A);
        int minIncrement = 0;
        unique.add(A[0]);
        for (int i = 1; i < A.length; i++) {
            while (unique.contains(A[i])) {
                A[i]++;
                minIncrement++;
            }
            unique.add(A[i]);
        }

        return minIncrement;
    }

    public int minIncrementForUnique2(int[] A) {
        return 0;
    }
}
