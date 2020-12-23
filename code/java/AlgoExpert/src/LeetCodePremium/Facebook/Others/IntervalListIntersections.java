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
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int i=0; int j=0;

        while(i < A.length && j < B.length ){
            if(A[i][0] < B[j][0]){
                list.add(A[i]);
                i++;
            } else {
                list.add(B[j]);
                j++;
            }
        }

        while(i < A.length){
            list.add(A[i]);
            i++;
        }

        while(j < B.length){
            list.add(B[j]);
            j++;
        }


        List<int[]> result = new ArrayList<>();

        for(int k=0; k< list.size()-1; ){
            System.out.print(" [" + list.get(k)[0] + ", " + list.get(k)[1] + "]  ");
            int[] first = list.get(k);
            int[] second = list.get(k+1);

            if(first[0] <= second[0] && first[1] >= second[1]){
                result.add(new int[]{second[0], second[1]});
            } else if(first[1] >= second[0]){
                result.add(new int[]{second[0], first[1]}); k++;
            }

        }

        return result.toArray(new int[result.size()][]);
    }
}
