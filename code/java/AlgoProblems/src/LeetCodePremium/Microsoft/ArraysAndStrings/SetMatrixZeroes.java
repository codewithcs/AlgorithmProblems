package LeetCodePremium.Microsoft.ArraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution ?

Constraints:
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class SetMatrixZeroes {

    // O(m+n) space and O(mn) time
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        for(int i=0; i < matrix.length ; i++){
            for(int j=0; j< matrix[0].length ; j++){
                if(matrix[i][j] == 0){
                    rows.add(i); columns.add(j);
                }
            }
        }

        for(int row: rows){
            for(int i=0; i< matrix[0].length; i++){
                matrix[row][i] = 0;
            }
        }

        for(int column: columns){
            for(int i=0; i< matrix.length; i++){
                matrix[i][column] = 0;
            }
        }
    }

    // We can also optimize it to O(min(m, n)) space using first half of Approach 2.
    //

    // O(1) space and O(mn) time.
    public void setZeroes2(int[][] matrix) {
        boolean isCol = false; // will be true if first column contains atleast 1 zero in the initial matrix.
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {
            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        // We use first row and column to mark elements as 0 in other rows and columns.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
