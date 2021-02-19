package LeetCodePremium.Medium;

import java.util.*;

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
    // Time Limit Exceeded. Do not need to sort as it does not help.
    public int minIncrementForUnique(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        Set<Integer> unique = new HashSet<>();
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

    // A: [3, 2, 1, 2, 1, 7].
    // this solution is same as previous, using a map does not do anything better.
    public int minIncrementForUnique2(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        Set<Integer> unique = new HashSet<>();
        Map<Integer, Integer> count = new HashMap<>();
        int minIncrements = 0;
        for (int num : A) {
            if (!count.containsKey(num)) {
                count.put(num, 1);
            } else {
                count.put(num, count.get(num) + 1);
            }
        }

        for (int num : A) {
            if (count.get(num) == 1) {
                unique.add(num);
            }
        }

        // unique: {3, 7}.
        for (int i = 0; i < A.length; i++) {
            if (count.get(A[i]) > 1) { // checking on previous value.
                while (unique.contains(A[i])) {
                    A[i]++;
                    minIncrements++;
                }
            }
            unique.add(A[i]); // In the end A[i] is a unique element of the set.
        }

        return minIncrements;
    }

    public int minIncrementForUnique3(int[] A) {
        return 0; 
    }
}
