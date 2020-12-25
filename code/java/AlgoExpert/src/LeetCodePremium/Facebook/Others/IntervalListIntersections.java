package LeetCodePremium.Facebook.Others;

import java.util.ArrayList;
import java.util.List;

/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Note:
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 */
public class IntervalListIntersections {

    // O(m+n) time and space. 
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i=0; int j=0;
        while(i < A.length && j < B.length ){
            int[] first = A[i];
            int[] second = B[j];
            int low = Math.max(first[0], second[0]);
            int high = Math.min(first[1], second[1]);

            if(high >= low){
                result.add(new int[]{low, high});
            }

            // Remove the interval with a smaller endpoint.
            if(first[1] < second[1]){
                i++;
            } else{
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
