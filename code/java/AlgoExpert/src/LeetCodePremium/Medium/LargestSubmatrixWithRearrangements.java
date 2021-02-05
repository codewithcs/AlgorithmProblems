package LeetCodePremium.Medium;


import java.util.Arrays;

/*
You are given a binary matrix of size m x n, and you are allowed to rearrange
the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the
submatrix is 1 after reordering the columns optimally.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m * n <= 10^5
matrix[i][j] is 0 or 1.
 */
public class LargestSubmatrixWithRearrangements {
    public int largestSubmatrix(int[][] matrix) {

        for(int i=0; i< matrix.length; i++){
            Arrays.sort(matrix[i]);
        }

        return 0;
    }
}
